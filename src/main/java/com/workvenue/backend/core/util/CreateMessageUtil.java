package com.workvenue.backend.core.util;

public class CreateMessageUtil {
    public static String getMessage(String className,String baseMessage){
        return String.format("%s %s",className,baseMessage);
    }
}
