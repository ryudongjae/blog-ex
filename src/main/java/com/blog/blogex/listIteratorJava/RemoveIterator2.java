package com.blog.blogex.listIteratorJava;

import java.util.ArrayList;
import java.util.List;

public class RemoveIterator2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            System.out.println("Iterating: " + item);
            if (item.equals("1") || item.equals("2")) {
                list.remove(item);
            }
        }

        System.out.println(list);
    }
}
