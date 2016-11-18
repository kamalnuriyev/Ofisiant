package com.kmlnuriyev.ofisiant.table;

import android.net.Uri;

import static com.kmlnuriyev.ofisiant.MainActivity.PROVIDER_NAME;

/**
 * Created by Kamal on 03.11.2016.
 */

public class Categories {
    private int id;
    private String name = null;

    //Table name
    public static final String TABLE_NAME = "categories";

    //Column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    //Content URI
    public static final String URL = "content://" + PROVIDER_NAME + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
