package com.kmlnuriyev.ofisiant.expandListClasses;

import com.kmlnuriyev.ofisiant.table.Categories;

import java.util.ArrayList;

/**
 * Created by Kamal on 11.11.2016.
 */

public class ProductGroup {
    private Categories category = null;
    private ArrayList<ProductChild> productChildList;

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public ArrayList<ProductChild> getProductChildList() {
        return productChildList;
    }

    public void setProductChildList(ArrayList<ProductChild> productChildList) {
        this.productChildList = productChildList;
    }
}
