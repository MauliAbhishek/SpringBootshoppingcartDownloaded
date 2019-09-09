package com.reljicd.controller;

import com.reljicd.exception.NotEnoughProductsInStockException;
import com.reljicd.service.ProductService;
import com.reljicd.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);
    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        logger.info("in ShoppingCartController Control to view  shoppingCart ");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        return modelAndView;
    }
   
    //End of my code 
	
	  @GetMapping("/shoppingCart/addProduct/{productId}") public ModelAndView
	  addProductToCart(@PathVariable("productId") Long productId) {
	  productService.findById(productId).ifPresent(shoppingCartService::addProduct)
	  ; logger.
	  info("in ShoppingCartController Control to to add product in shopping cat by id  "
	  + productId); return shoppingCart(); }
	 

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        logger.info("in ShoppingCartController Control to to remove product in shopping cat by id  "+ productId);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/checkout")
    public ModelAndView checkout() {
        try {
            shoppingCartService.checkout();
            logger.info("in ShoppingCartController Control to check out product ");
        } catch (NotEnoughProductsInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        return shoppingCart();
    }
}
