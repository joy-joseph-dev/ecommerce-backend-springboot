package com.joy_joseph.ecommerce_backend.Controller;

import com.joy_joseph.ecommerce_backend.Model.Cart;
import com.joy_joseph.ecommerce_backend.Service.CartService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/cart")
public class CartController {
    CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart addProductToCart(@RequestParam long userId,
                                 @RequestParam long productId,
                                 @RequestParam int quantity) {
        return cartService.addProductToCart(userId, productId, quantity);
    }
    @GetMapping("/{userId}")
   public Cart getCartByUser(@PathVariable long userId){
        return cartService.getCartByUser(userId);
    }
    @PutMapping("/item/{cartItemId}")
    public Cart updateCartItem(@PathVariable long cartItemId,@RequestParam int quantity){

        return cartService.updateCartItem(cartItemId,quantity);
    }
    @DeleteMapping("/item/{cartItemId}")
    public Cart removeItemFromCart(@PathVariable long cartItemId){
        return cartService.removeItemFromCart(cartItemId);
    }
}
