package com.blog.blogex.refactoring;

public class Refactoring {

    public int getOrderPrice(int arg1, int arg2){
        return arg1 * arg2;
    }

    public int getOrderTotalPrice(int price , int quantity){
        return price * quantity;
    }

    public int getOrderTotalPrice1(int price, int quantity,double discount){
        return (int) ((price * quantity) * (discount/100));
    }
    public int getOrderTotalPrice2(int price, int quantity,double discount){
        return (int) calculationOrderTotalPrice(price,quantity,discount);
    }

    private double calculationOrderTotalPrice(int price, int quantity, double discount) {
        return ((price * quantity) * (discount/100));
    }

    public int getOrderPrice(int price, int quantity,double discount){
        return (int) ((price * quantity) * (discount/100));
    }

}
