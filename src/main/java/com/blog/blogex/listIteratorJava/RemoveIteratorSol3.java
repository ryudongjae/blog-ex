package com.blog.blogex.listIteratorJava;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RemoveIteratorSol3 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.println("Iterating: " + item);
            if (item.equals("2")) {
                it.remove();
            }
        }

        System.out.println("Result: " + list);
    }
}
