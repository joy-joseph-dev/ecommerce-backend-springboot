package com.joy_joseph.ecommerce_backend.Service;

import com.joy_joseph.ecommerce_backend.Model.Cart;
import com.joy_joseph.ecommerce_backend.Model.CartItem;
import com.joy_joseph.ecommerce_backend.Model.Product;
import com.joy_joseph.ecommerce_backend.Model.User;
import com.joy_joseph.ecommerce_backend.Repository.CartItemRepository;
import com.joy_joseph.ecommerce_backend.Repository.CartRepository;
import com.joy_joseph.ecommerce_backend.Repository.ProductRepo;
import com.joy_joseph.ecommerce_backend.Repository.UserRepository;
import com.joy_joseph.ecommerce_backend.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private  UserRepository userRepository;
    private  ProductRepo productRepository;
    private  CartRepository cartRepository;
    private  CartItemRepository cartItemRepository;

    public CartService(UserRepository userRepository,
                       ProductRepo productRepository,
                       CartRepository cartRepository,
                       CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart addProductToCart(Long userId, Long productId, int quantity) {

        // 1) Get User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 2) Get or create Cart
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setTotalPrice(0);
            cart = cartRepository.save(cart);
        }

        // 3) Get Product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // 4) Get or create CartItem
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        // 5) Update CartItem price (total for this item)
        cartItem.setPrice(cartItem.getQuantity() * product.getPrice());
        cartItemRepository.save(cartItem);

        // 6) Recalculate Cart total (DB-level fetch)
        int total = cartItemRepository.findByCart(cart)
                .stream()
                .mapToInt(CartItem::getPrice)
                .sum();

        cart.setTotalPrice(total);

        // 7) Save and return Cart
        return cartRepository.save(cart);
    }

    public Cart getCartByUser(long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Cart cart = cartRepository.findByUser(user);
        if(cart == null){
            return null;
        }
        List<CartItem>cartItem = cartItemRepository.findByCart(cart);

        return cart;
    }

    public Cart updateCartItem(long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() ->
                            new ResourceNotFoundException("items not found"));
        Cart cart = cartItem.getCart();
        if(quantity==0){
            cartItemRepository.delete(cartItem);
        }
        else{
            cartItem.setQuantity(quantity);
            cartItem.setPrice(quantity*cartItem.getProduct().getPrice());
            cartItemRepository.save(cartItem);
        }
        int total = cartItemRepository.findByCart(cart)
                                      .stream()
                                      .mapToInt(CartItem ::getPrice).sum();
                cart.setTotalPrice(total);
                return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() ->
                            new ResourceNotFoundException("item not found"));
        Cart cart = cartItem.getCart();
        cartItemRepository.delete(cartItem);
        int total = cartItemRepository.findByCart(cart)
                .stream()
                .mapToInt(CartItem :: getPrice)
                .sum();
        cart.setTotalPrice(total);
        return cartRepository.save(cart);
    }
}


