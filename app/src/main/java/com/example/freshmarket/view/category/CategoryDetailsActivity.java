package com.example.freshmarket.view.category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.freshmarket.R;
import com.example.freshmarket.adapter.ProductsAdapter;
import com.example.freshmarket.databinding.ActivityCategoryDetailsBinding;
import com.example.freshmarket.network.model.response.Category;
import com.example.freshmarket.view.product.ProductDetailsActivity;

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
        setSupportActionBar(categoryDetailsBinding.appbar.toolbar);
        assert (null != category.getName() && null != getSupportActionBar());
        getSupportActionBar().setTitle(category.getName());
        categoryDetailsBinding.appbar.setCategoryItem(category);
//        Show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setProductsAdapter(){

        ProductsAdapter adapter = new ProductsAdapter(this, category.getProducts(), product -> {
            Intent intent = new Intent(CategoryDetailsActivity.this,
                    ProductDetailsActivity.class);
            intent.putExtra(getResources().getString(R.string.product), product);
            startActivity(intent);
        });

        categoryDetailsBinding.productsRv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar_white, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        Action bar button clicked
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
