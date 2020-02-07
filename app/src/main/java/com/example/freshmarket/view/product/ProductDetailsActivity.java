package com.example.freshmarket.view.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.freshmarket.R;
import com.example.freshmarket.databinding.ActivityProductDetailsBinding;
import com.example.freshmarket.network.model.response.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    private ActivityProductDetailsBinding productDetailsBinding;
    private Product productItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productDetailsBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_product_details);

        productItem = (Product) getIntent().getSerializableExtra(getResources()
                .getString(R.string.product));

        productDetailsBinding.setProductItem(productItem);

        productDetailsBinding.backBtn.setOnClickListener(view -> finish());
    }
}
