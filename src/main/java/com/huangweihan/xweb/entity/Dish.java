package com.huangweihan.xweb.entity;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/11/4 0004
 */
public class Dish {

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Dish(String name, boolean vegetarian, int calories, int price, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.price = price;
        this.type = type;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * 菜名
     */
    private String name;
    /**
     * 是否可口
     */
    private boolean vegetarian;
    /**
     * 热量值
     */
    private int calories;
    /**
     * 价格
     */
    private int price;
    /**
     * 种类
     */
    private Type type;
}
