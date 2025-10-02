package com.kolotree.task1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Test {

    @GetMapping("/test")
    public String test(){
        Date date = new Date();
       return "Works correctly at:" + date ;
    }

}
