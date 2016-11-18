package com.kmlnuriyev.ofisiant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.kmlnuriyev.ofisiant.R;
import com.kmlnuriyev.ofisiant.expandListClasses.ProductChild;
import com.kmlnuriyev.ofisiant.expandListClasses.ProductGroup;

import java.util.ArrayList;

/**
 * Created by Kamal on 11.11.2016.
 */

public class ProductListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<ProductGroup> productGroupList;
    public ProductListAdapter(Context context, ArrayList<ProductGroup> productGroupList) {
        this.context = context;
        this.productGroupList = productGroupList;
    }

    public void addItem(ProductChild productChild, ProductGroup productGroup) {
        if (!productGroupList.contains(productGroup)) {
            productGroupList.add(productGroup);
        }
        int index = productGroupList.indexOf(productGroup);
        ArrayList<ProductChild> productChildList = productGroupList.get(index).getProductChildList();
        productChildList.add(productChild);
        productGroupList.get(index).setProductChildList(productChildList);
    }

    @Override
    public int getGroupCount() {
        return productGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<ProductChild> productChildList = productGroupList.get(groupPosition).getProductChildList();
        return productChildList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return productGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ProductChild> productChildList = productGroupList.get(groupPosition).getProductChildList();
        return productChildList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ProductGroup productGroup = (ProductGroup) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.product_expandlist_group_item, null);
        }
        TextView productGroupText = (TextView) convertView.findViewById(R.id.productGroupText);
        productGroupText.setText(productGroup.getCategory().getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ProductChild productChild = (ProductChild) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.product_expandlist_child_item, null);
        }
        TextView productChildText = (TextView) convertView.findViewById(R.id.productChildText);
        productChildText.setText(productChild.getName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
