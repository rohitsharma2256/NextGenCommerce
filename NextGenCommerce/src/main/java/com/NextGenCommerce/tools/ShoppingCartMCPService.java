package com.NextGenCommerce.tools;


import com.NextGenCommerce.entity.CartItem;
import com.NextGenCommerce.repository.CartItemRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ShoppingCartMCPService {
    //Here is the Agentic code
    //Whatever we prompt will hit this layer

    @Autowired
    private CartItemRepository cartItemRepo;

    private static final Map<String, Double> PRODUCTS = Map.ofEntries(

            Map.entry("iPhone", 90000.00),
            Map.entry("Mac Book", 130000.00),
            Map.entry("Samsung S26", 150000.00),
            Map.entry("HP Laptop 15S", 100000.00),
            Map.entry("Dell Inspiron 14", 75000.00),
            Map.entry("Lenovo ThinkPad X1", 145000.00),
            Map.entry("iPad Pro", 85000.00),
            Map.entry("AirPods Pro", 25000.00),
            Map.entry("Sony WH-1000XM5 Headphones", 30000.00),
            Map.entry("Apple Watch Series 9", 45000.00),
            Map.entry("Samsung Galaxy Tab S9", 70000.00),
            Map.entry("Asus ROG Gaming Laptop", 180000.00),
            Map.entry("Logitech MX Master 3 Mouse", 9000.00),
            Map.entry("Mechanical Keyboard RGB", 6500.00),
            Map.entry("External SSD 1TB", 12000.00),
            Map.entry("Gaming Monitor 27 Inch", 28000.00),
            Map.entry("Amazon Echo Dot", 5000.00),
            Map.entry("Wireless Charger", 2000.00),
            Map.entry("Bluetooth Speaker JBL", 8000.00),
            Map.entry("Webcam Full HD", 3500.00)
    );

    //this is the tool like(api)
    @Tool(name="addToCart", description = "Add a product to shopping cart. If the product is already exists, it updates the quantity")
    public String addToCart(String productName, int qty) {
        //Bussiness logic
        if (!PRODUCTS.containsKey(productName)) {
            return "Product Not Found";     //edge case
        }
      Double price = PRODUCTS.get(productName);
        CartItem cartItem = cartItemRepo.findByProductId(productName);

        if(cartItem == null){
            cartItem = new CartItem();
            cartItem.setProductId(productName);
            cartItem.setProductName(productName);
            cartItem.setQuantity(qty);
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity() + qty);
        }
        cartItem.setPrice(cartItem.getQuantity() * price);
        cartItemRepo.save(cartItem);
        return qty + " " +productName + " " + " added to cart. Total Price is :" + (cartItem.getPrice());
    }

    @Tool(name="getCartTotal", description = "Calculate the total price of items in the repository")
    public  double getCartTotal(){
        return cartItemRepo.findAll().stream().mapToDouble(CartItem::getPrice).sum();
    }
}
