package com.kmlnuriyev.ofisiant.table;

import android.net.Uri;

import static com.kmlnuriyev.ofisiant.MainActivity.PROVIDER_NAME;

/**
 * Created by Kamal on 04.11.2016.
 */

public class Products {
    //Table name
    public static final String TABLE_NAME = "products";

    //Column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_CATEGORY_ID = "category_id";

    //Content URI
    public static final String URL = "content://" + PROVIDER_NAME + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = Uri.parse(URL);
}
