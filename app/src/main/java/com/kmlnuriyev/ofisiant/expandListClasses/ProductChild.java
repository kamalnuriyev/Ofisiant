package com.kmlnuriyev.ofisiant.expandListClasses;

/**
 * Created by Kamal on 11.11.2016.
 */

public class ProductChild {
    private String name = null;
    private Double price = null;
    private int categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
