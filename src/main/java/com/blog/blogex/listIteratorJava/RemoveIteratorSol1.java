package com.blog.blogex.listIteratorJava;

import java.util.ArrayList;
import java.util.List;

public class RemoveIteratorSol1 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        for (int i = (list.size() - 1); i > -1; i--) {
            String item = list.get(i);
            System.out.println("Iterating: " + item);
            if (item.equals("2")) {
                list.remove(item);
            }
        }

        System.out.println("Result: " + list);
    }
}
