package com.blog.blogex.listIteratorJava;

import java.util.ArrayList;
import java.util.List;

public class RemoveIterator1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        for (String item : list) {
            if (item.equals("2")) {
                list.remove(item);
            }
        }
    }
}
