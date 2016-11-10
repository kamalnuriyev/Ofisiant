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
import com.kmlnuriyev.ofisiant.model.CategoryDao;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    Button createCategoryButton;
    EditText categoryNameText;
    ArrayAdapter<String> arrayAdapter;
    ListView categoryListView;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_category, container, false);
        final CategoryDao categoryDao = new CategoryDao();

        //Creating category
        categoryNameText = (EditText) view.findViewById(R.id.categoryNameText);
        createCategoryButton = (Button) view.findViewById(R.id.createCategoryButton);
        createCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultMessage = categoryDao.createCategory(categoryNameText.getText().toString());
                Toast.makeText(MainActivity.getContextOfApplication(), resultMessage, Toast.LENGTH_LONG).show();
            }
        });
        //End creating category implementation

        //Tabhost activity
        TabHost tabHost = (TabHost) view.findViewById(R.id.tabHostCategory);
        tabHost.setup();
        //Tab for adding category
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Add category");
        tabSpec.setContent(R.id.addingCategoryTab);
        tabSpec.setIndicator("Əlavə et");
        tabHost.addTab(tabSpec);
        //Tab for control category list
        tabSpec = tabHost.newTabSpec("Category List");
        tabSpec.setContent(R.id.categoryListTab);
        tabSpec.setIndicator("Kateqoriyalar");
        tabHost.addTab(tabSpec);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //Table list view
                arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.category_listview, categoryDao.getCategoryNameList());
                categoryListView = (ListView) view.findViewById(R.id.categoryListView);
                categoryListView.setAdapter(arrayAdapter);
                //End table list view implementation
            }
        });
        //End TableHost implementation

        return view;
    }

}
