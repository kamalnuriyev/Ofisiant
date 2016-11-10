package com.kmlnuriyev.ofisiant.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kmlnuriyev.ofisiant.R;
import com.kmlnuriyev.ofisiant.model.TableDao;
import com.kmlnuriyev.ofisiant.view.ShowView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        //Show buttons for select appropriate table
        ViewGroup flowContainer = (ViewGroup) view.findViewById(R.id.tablesFlowLayout);
        //Shows tables in the screen
        TableDao tableDao = new TableDao();
        List<String> tableNumberList = tableDao.getTableNumbers();
        ShowView showView = new ShowView();
        showView.showTables(flowContainer, tableNumberList);

        return view;
    }

}
