package com.NextGenCommerce.repository;


import com.NextGenCommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, String> {

     CartItem findByProductId(String productId);
     void deleteByProductId(String productId);

}
