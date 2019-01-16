package com.clover.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Clover
 */
public class RegxUtil {
    private static String URL_REGEX = "[a-zA-z]+://[^\\s]*";

    /**
     * 验证URL
     * @param url
     * @return
     */
    public static boolean url(String url){
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }
}
