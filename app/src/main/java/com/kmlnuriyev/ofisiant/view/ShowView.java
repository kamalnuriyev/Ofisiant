package com.kmlnuriyev.ofisiant.view;

import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kmlnuriyev.ofisiant.MainActivity;

import java.util.List;

/**
 * Created by Kamal on 30.10.2016.
 */

public class ShowView {
    public void showTables(ViewGroup flowContainer, List<String> tableNameList) {
        Log.i("Count", tableNameList.size()+"");
        Log.i("tableNameList", tableNameList+"");
        for (int i=0; i<tableNameList.size(); i++) {
            Button tableButton = new Button(MainActivity.getContextOfApplication());
            tableButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                                                        LinearLayout.LayoutParams.WRAP_CONTENT));
            tableButton.setText(tableNameList.get(i).toString());
            tableButton.setHeight(300);
            tableButton.setWidth(300);
            tableButton.setTextSize(50);
            tableButton.setTextColor(Color.WHITE);

            flowContainer.addView(tableButton);
        }
    }
}
