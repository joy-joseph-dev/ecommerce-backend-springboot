package com.joy_joseph.ecommerce_backend.Repository;

import com.joy_joseph.ecommerce_backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
