package com.reljicd.service;

import com.reljicd.exception.NotEnoughProductsInStockException;
import com.reljicd.model.Cart;
import com.reljicd.model.Product;


import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();
    //Start of my code 
    Cart saveCart(Cart cart);
    //End of my code
    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}
