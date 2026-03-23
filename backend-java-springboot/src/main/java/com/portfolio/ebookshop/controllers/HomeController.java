package com.portfolio.ebookshop.controllers;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("/home")
    public Map<String, String> home() {
        System.out.println("Spring boot!");
        return Map.of(
            "status","online",
            "message","bookshop api"
        );
    }
}
