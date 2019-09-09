package com.reljicd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @GetMapping("/login")
    public String login(Principal principal) {
    	logger.info("in LoginController in login method ");
        if (principal != null) {
            return "redirect:/home";
        }
        return "/login";
    }

}
