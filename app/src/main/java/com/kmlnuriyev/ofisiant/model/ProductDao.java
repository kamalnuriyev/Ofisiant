package com.kmlnuriyev.ofisiant.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.kmlnuriyev.ofisiant.MainActivity;
import com.kmlnuriyev.ofisiant.table.Products;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamal on 04.11.2016.
 */

public class ProductDao {

    /**
     * @param name
     * @param price
     * @param categoryId
     * @return
     */
    public String createProduct(String name, double price, int categoryId) {
        String resultMessage = null;
        ContentValues values = new ContentValues();

        values.put(Products.COLUMN_NAME, name);
        values.put(Products.COLUMN_PRICE, price);
        values.put(Products.COLUMN_CATEGORY_ID, categoryId);

        Uri uri = MainActivity.getContextOfApplication().getContentResolver().insert(Products.CONTENT_URI, values);
        resultMessage = name + " bazaya əlavə edildi";

        return resultMessage;
    }

    public List<String> getProductNameList() {
        Cursor c = MainActivity.getContextOfApplication().getContentResolver().query(Products.CONTENT_URI, null, null, null, "name");
        List<String> productNameList = new ArrayList<String>();

        try {
            if (c.moveToFirst()) {
                do {
                    productNameList.add(c.getString(c.getColumnIndex(Products.COLUMN_NAME)));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }

        return productNameList;
    }

    public List<Products> getProductList() {
        Cursor c = MainActivity.getContextOfApplication().getContentResolver().query(Products.CONTENT_URI, null, null, null, "name");
        List<Products> productList = new ArrayList<>();
        Products product = null;

        try {
            if (c.moveToFirst()) {
                do {
                    product = new Products();
                    product.setName(c.getString(c.getColumnIndex(Products.COLUMN_NAME)));
                    product.setPrice(c.getInt(c.getColumnIndex(Products.COLUMN_PRICE)));
                    product.setCategoryId(c.getInt(c.getColumnIndex(Products.COLUMN_CATEGORY_ID)));

                    productList.add(product);
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }

        return productList;
    }

    public List<Products> getProductListByCategoryId(int categoryId) {
        String selectionClause = Products.COLUMN_CATEGORY_ID + " = ?";
        String[] selectionArgs = {""};
        selectionArgs[0] = String.valueOf(categoryId);
        Cursor c = MainActivity.getContextOfApplication().getContentResolver().query(Products.CONTENT_URI, null, selectionClause, selectionArgs, Products.COLUMN_NAME);
        List<Products> productList = new ArrayList<>();
        Products product;

        try {
            if (c.moveToFirst()) {
                do {
                    product = new Products();
                    product.setName(c.getString(c.getColumnIndex(Products.COLUMN_NAME)));
                    product.setPrice(c.getInt(c.getColumnIndex(Products.COLUMN_PRICE)));
                    product.setCategoryId(c.getInt(c.getColumnIndex(Products.COLUMN_CATEGORY_ID)));

                    productList.add(product);
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }

        return productList;
    }

}
