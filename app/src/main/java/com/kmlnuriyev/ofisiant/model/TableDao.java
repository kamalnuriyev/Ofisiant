package com.kmlnuriyev.ofisiant.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.kmlnuriyev.ofisiant.MainActivity;
import com.kmlnuriyev.ofisiant.table.Tables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamal on 31.10.2016.
 */

public class TableDao {

    //Get table (masa) number list
    public List<String> getTableNumbers() {
        Cursor c = MainActivity.getContextOfApplication().getContentResolver().query(Tables.CONTENT_URI, null, null, null, "name");
        List<String> tableNumberList = new ArrayList<String>();

        try {
            if (c.moveToFirst()) {
                do {
                    tableNumberList.add(c.getString(c.getColumnIndex(Tables.COLUMN_NAME)));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }

        Log.i("Table Number List size", tableNumberList.size()+"");
        return tableNumberList;
    }

    /**
     * @param tableName
     * @return
     */
    public String createTable(String tableName) {
        String resultMessage = null;
        ContentValues values = new ContentValues();
        values.put(Tables.COLUMN_NAME, tableName);

        Uri uri = MainActivity.getContextOfApplication().getContentResolver().insert(Tables.CONTENT_URI, values);
        resultMessage = tableName + " nömrəli masa bazaya əlavə edildi";

        return resultMessage;
    }
}
