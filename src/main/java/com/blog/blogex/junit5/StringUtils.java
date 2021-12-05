package com.blog.blogex.junit5;

public class StringUtils {
    public static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }
}
