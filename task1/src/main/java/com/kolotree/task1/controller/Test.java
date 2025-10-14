package com.kolotree.task1.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Test {

    private static final Logger logger = LogManager.getLogger(Test.class);

    @GetMapping("/test")
    public String test() {
        Date date = new Date();
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.warn("Warn log message");
        logger.error("Error log message");
        logger.log(Level.forName("DIAG", 350), "Ovo je DIAG");
        logger.log(Level.forName("CALL", 250), "Call");
        logger.info("After");
        return "Works correctly at:" + date;
    }

}
