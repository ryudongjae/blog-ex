package com.blog.blogex.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMapAct {
    public static void main(String[] args) {
        System.out.println("----------START----------\n");

        List<Map<String,Object>> list = new ArrayList<>();

        for(int i = 0; i < 10; i++){

            Map<String,Object> map = new HashMap<String,Object>();
            map.put(String.valueOf(i),"value"+i);


            list.add(map);
        }

        System.out.println("List Map Insert : " + list.toString()+"\n");

    }
}
