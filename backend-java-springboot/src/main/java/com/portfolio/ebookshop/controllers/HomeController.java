package com.portfolio.ebookshop.controllers;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @GetMapping
    public Map<String, String> home() {
        System.out.println("Spring boot!");
        return Map.of(
            "status","online",
            "message","bookshop api"
        );
    }
}
