package com.blog.blogex.aop;

import org.springframework.stereotype.Component;

@Component
public class SimpleEventService implements EventService{

    @ExecutionTimeChecker
    @Override
    public void createEvent() {
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("create Event");
    }

    @Override
    public void publishEvent() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("publishEvent");
    }

    @ExecutionTimeChecker
    @Override
    public void deleteEvent() {
        System.out.println("deleteService");
    }
}
