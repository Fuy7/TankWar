package com.fuy.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertiesMgr {
    private static Properties property;
    static {
        property = new Properties();
        try {
            property.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTankCount(String key){
        return (String) property.get(key);
    }

}
