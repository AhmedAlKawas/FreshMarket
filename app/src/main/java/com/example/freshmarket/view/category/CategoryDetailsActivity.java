package com.example.freshmarket.view.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;

import com.example.freshmarket.R;
import com.example.freshmarket.adapter.ProductsAdapter;
import com.example.freshmarket.databinding.ActivityCategoryDetailsBinding;
import com.example.freshmarket.network.model.response.Category;

public class CategoryDetailsActivity extends AppCompatActivity {

    private  Category category;
    private ActivityCategoryDetailsBinding categoryDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryDetailsBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_category_details);
        category = (Category) getIntent().getSerializableExtra(getString(R.string.categoty));
        initView();
        setProductsAdapter();
    }

    private void initView() {
        setSupportActionBar(categoryDetailsBinding.toolbar);
        assert (null != category.getName() && null != getSupportActionBar());
        getSupportActionBar().setTitle(category.getName());
    }

    private void setProductsAdapter(){

        ProductsAdapter adapter = new ProductsAdapter(this, category.getProducts(), product -> {

        });

        categoryDetailsBinding.productsRv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

}