package com.blog.blogex.listIteratorJava;

import java.util.ArrayList;
import java.util.List;

public class RemoveIteratorSol2 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        list.removeIf(item -> item.equals("2"));

        System.out.println("Result: " + list);
    }
}
