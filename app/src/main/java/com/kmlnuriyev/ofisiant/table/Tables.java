package com.kmlnuriyev.ofisiant.table;

import android.net.Uri;

import static com.kmlnuriyev.ofisiant.MainActivity.PROVIDER_NAME;

/**
 * Created by Kamal on 03.11.2016.
 */

public class Tables {
    //Table name
    public static final String TABLE_NAME = "tables";

    //Column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    //Content URI
    public static final String URL = "content://" + PROVIDER_NAME + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = Uri.parse(URL);
}
