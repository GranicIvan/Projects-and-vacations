package com.kolotree.task1.utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {


    public static Map<String, Object> createBody(int status, String msg) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status);
        body.put("message", msg);

        return body;
    }
}
