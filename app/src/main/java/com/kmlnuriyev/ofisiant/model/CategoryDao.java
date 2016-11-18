package com.kmlnuriyev.ofisiant.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.kmlnuriyev.ofisiant.MainActivity;
import com.kmlnuriyev.ofisiant.table.Categories;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Kamal on 02.11.2016.
 */

public class CategoryDao {

    public LinkedHashMap<String, Integer> getCategoryHashMap() {
        Cursor c = MainActivity.getContextOfApplication().getContentResolver().query(Categories.CONTENT_URI, null, null, null, "name");
        LinkedHashMap<String, Integer> categoriesHashMap = new LinkedHashMap<String, Integer>();

        try {
            if (c.moveToFirst()) {
                do {
                    categoriesHashMap.put(c.getString(c.getColumnIndex(Categories.COLUMN_NAME)), c.getInt(c.getColumnIndex(Categories.COLUMN_ID)));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }

        return categoriesHashMap;
    }

    /**
     * @return
     */
    public List<String> getCategoryNameList() {
        Cursor c = MainActivity.getContextOfApplication().getContentResolver().query(Categories.CONTENT_URI, null, null, null, Categories.COLUMN_NAME);
        List<String> categoryList = new ArrayList<String>();

        try {
            if (c.moveToFirst()) {
                do {
                    categoryList.add(c.getString(c.getColumnIndex(Categories.COLUMN_NAME)));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }

        return categoryList;
    }

    public List<Categories> getCategoryList() {
        Cursor c = MainActivity.getContextOfApplication().getContentResolver().query(Categories.CONTENT_URI, null, null, null, Categories.COLUMN_NAME);
        List<Categories> categoryList = new ArrayList<>();
        Categories category;

        try {
            if (c.moveToFirst()) {
                do {
                    category = new Categories();
                    category.setId(Integer.parseInt(c.getString(c.getColumnIndex(Categories.COLUMN_ID))));
                    category.setName(c.getString(c.getColumnIndex(Categories.COLUMN_NAME)));
                    categoryList.add(category);
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }

        return categoryList;
    }

    public String createCategory(String categoryName) {
        String resultMessage = null;
        ContentValues values = new ContentValues();
        values.put(Categories.COLUMN_NAME, categoryName);

        Uri uri = MainActivity.getContextOfApplication().getContentResolver().insert(Categories.CONTENT_URI, values);
        resultMessage = categoryName + " adlı kateqoriya bazaya əlavə edildi";

        return resultMessage;
    }
}
