package com.naitik.journalapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @RequestMapping("/health-check")
    public String healthcheck(){
        return "OK";
    }
}
