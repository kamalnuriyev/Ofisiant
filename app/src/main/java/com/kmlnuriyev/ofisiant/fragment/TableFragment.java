package com.kmlnuriyev.ofisiant.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.kmlnuriyev.ofisiant.MainActivity;
import com.kmlnuriyev.ofisiant.R;
import com.kmlnuriyev.ofisiant.model.TableDao;


/**
 * A simple {@link Fragment} subclass.
 */
public class TableFragment extends Fragment {
    Button createTableButton;
    EditText tableNoText;
    ArrayAdapter<String> arrayAdapter;
    ListView tableListView;

    public TableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_table, container, false);
        final TableDao tableDao = new TableDao();

        //Creating table
        createTableButton = (Button) view.findViewById(R.id.createTableButton);
        tableNoText = (EditText) view.findViewById(R.id.categoryNameText);

        createTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultMessage = tableDao.createTable(tableNoText.getText().toString());
                Toast.makeText(MainActivity.getContextOfApplication(), resultMessage, Toast.LENGTH_LONG).show();
            }
        });
        //End creating table implementation

        //Tabhost activity
        TabHost tabHost = (TabHost) view.findViewById(R.id.tabHostTable);
        tabHost.setup();
        //Tab for adding table
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Add table");
        tabSpec.setContent(R.id.addingTableTab);
        tabSpec.setIndicator("Əlavə et");
        tabHost.addTab(tabSpec);
        //Tab for control table list
        tabSpec = tabHost.newTabSpec("Table List");
        tabSpec.setContent(R.id.tableListTab);
        tabSpec.setIndicator("Masalar");
        tabHost.addTab(tabSpec);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //Table list view
                arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.table_listview, tableDao.getTableNumbers());
                tableListView = (ListView) view.findViewById(R.id.tableListView);
                tableListView.setAdapter(arrayAdapter);
                //End table list view implementation
            }
        });
        //End TableHost implementation

        return view;
    }

}
