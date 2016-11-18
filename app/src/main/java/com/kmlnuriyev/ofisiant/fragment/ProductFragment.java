package com.kmlnuriyev.ofisiant.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.kmlnuriyev.ofisiant.MainActivity;
import com.kmlnuriyev.ofisiant.R;
import com.kmlnuriyev.ofisiant.adapter.ProductListAdapter;
import com.kmlnuriyev.ofisiant.expandListClasses.ProductChild;
import com.kmlnuriyev.ofisiant.expandListClasses.ProductGroup;
import com.kmlnuriyev.ofisiant.model.CategoryDao;
import com.kmlnuriyev.ofisiant.model.ProductDao;
import com.kmlnuriyev.ofisiant.table.Categories;
import com.kmlnuriyev.ofisiant.table.Products;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner categorySpinner;
    Button addProductButton;
    EditText productNameText, productPriceText;
//    ArrayAdapter<String> arrayAdapter;
//    ListView productListView;

    private ProductListAdapter productListAdapter;
    private ArrayList<ProductGroup> productGroupList;
    private ExpandableListView productExpandableListView;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_product, container, false);
        final CategoryDao categoryDao = new CategoryDao();
        final ProductDao productDao = new ProductDao();
        List<String> categoryList = categoryDao.getCategoryNameList();


        //Spinner element
        categorySpinner = (Spinner) view.findViewById(R.id.categorySpinner);
        //Spinner click listener
        categorySpinner.setOnItemSelectedListener(this);
        //creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.getContextOfApplication(), R.layout.my_spinner_layout, categoryList);
        //attaching adapter to spinner
        categorySpinner.setAdapter(dataAdapter);
        categorySpinner.setSelection(0);
        dataAdapter.notifyDataSetChanged();


        productNameText = (EditText) view.findViewById(R.id.productNameText);
        productPriceText = (EditText) view.findViewById(R.id.productPriceText);
        categorySpinner = (Spinner) view.findViewById(R.id.categorySpinner);

        addProductButton = (Button) view.findViewById(R.id.createProductButton);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int categoryId = categoryDao.getCategoryHashMap().get(categorySpinner.getSelectedItem());
                //Create product with appropriate parameters
                String resultMessage = productDao.createProduct(productNameText.getText().toString(), Double.parseDouble(productPriceText.getText().toString()), categoryId);
                Toast.makeText(MainActivity.getContextOfApplication(), resultMessage, Toast.LENGTH_LONG).show();
            }
        });

        // Tabhost activity
        TabHost tabHost = (TabHost) view.findViewById(R.id.tabHostProduct);
        tabHost.setup();
        //Tab for adding product
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Add product");
        tabSpec.setContent(R.id.addingProductTab);
        tabSpec.setIndicator("Əlavə et");
        tabHost.addTab(tabSpec);
        //Tab for control product list
        tabSpec = tabHost.newTabSpec("Product List");
        tabSpec.setContent(R.id.productListTab);
        tabSpec.setIndicator("Yemək/İçki/Salat");
        tabHost.addTab(tabSpec);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //Table list view
//                arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.product_listview, productDao.getProductNameList());
//                productListView = (ListView) view.findViewById(R.id.productListView);
//                productListView.setAdapter(arrayAdapter);
                //End table list view implementation

                if (tabId.equals("Product List")) {
                    //Expandible List View
                    productExpandableListView = (ExpandableListView) view.findViewById(R.id.productExpandableListView);
                    productExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                        @Override
                        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                            Log.i("clicked child", "aaa");
                            return true;
                        }
                    });
                    productGroupList = setGroupItems();
                    productListAdapter = new ProductListAdapter(view.getContext(), productGroupList);
                    productExpandableListView.setAdapter(productListAdapter);
                }
                //END Expandible List View implementation
            }
        });
        //End TableHost implementation

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    public ArrayList<ProductGroup> setGroupItems() {
        ProductDao productDao = new ProductDao();
        CategoryDao categoryDao = new CategoryDao();
        ArrayList<ProductGroup> productGroupList = new ArrayList<>();
        ArrayList<ProductChild> productChildList;
        ProductGroup productGroup;
        ProductChild productChild;
        List<String> categoryNameList = categoryDao.getCategoryNameList();
        List<Categories> categoryList = categoryDao.getCategoryList();
        List<Products> productList = null;

        for (Categories category : categoryList) {
            productChildList = new ArrayList<>();
            productGroup = new ProductGroup();
            productGroup.setCategory(category);

            productList = productDao.getProductListByCategoryId(category.getId());

            for (Products product:productList) {
                productChild = new ProductChild();
                productChild.setName(product.getName());
                productChild.setPrice(product.getPrice());
                productChild.setCategoryId(product.getCategoryId());

                productChildList.add(productChild);
            }
            productGroup.setProductChildList(productChildList);
            productGroupList.add(productGroup);
        }

        return productGroupList;
    }

    /*public ArrayList<ProductGroup> setStandardGroups() {
        ArrayList<ProductGroup> productGroupList = new ArrayList<>();
        ArrayList<ProductChild> productChildList = new ArrayList<>();

        ProductGroup productGroup = new ProductGroup();
        ProductChild productChild = null;

        ///////////////////////////////////////////
        productGroup.setCategoryName("Salatlar");

        productChild = new ProductChild();
        productChild.setName("Coban");
        productChildList.add(productChild);

        productChild = new ProductChild();
        productChild.setName("Suba");
        productChildList.add(productChild);

        productChild = new ProductChild();
        productChild.setName("Mimoza");
        productChildList.add(productChild);

        productGroup.setProductChildList(productChildList);
        productChildList = new ArrayList<>();
        /////////
        productGroupList.add(productGroup);

        Log.i("Product List 1", productGroupList.size()+"");

        //////////////////////////////
        productGroup = new ProductGroup();
        productGroup.setCategoryName("Suplar");

        productChild = new ProductChild();
        productChild.setName("Merci");
        productChildList.add(productChild);

        productChild = new ProductChild();
        productChild.setName("Toyuq");
        productChildList.add(productChild);

        productChild = new ProductChild();
        productChild.setName("Dovga");
        productChildList.add(productChild);

        productGroup.setProductChildList(productChildList);

        ///////////////
        productGroupList.add(productGroup);
        Log.i("Product List 2", productGroupList.size()+"");

        return productGroupList;
    }*/
}
