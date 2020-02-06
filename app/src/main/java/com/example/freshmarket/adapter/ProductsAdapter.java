package com.example.freshmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshmarket.R;
import com.example.freshmarket.databinding.ItemCategoryBinding;
import com.example.freshmarket.databinding.ItemProductBinding;
import com.example.freshmarket.interfaces.CategoryClickListener;
import com.example.freshmarket.interfaces.ProductClickListener;
import com.example.freshmarket.network.model.response.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductHolder> {

    Context context;
    List<Product> productsList;
    ProductClickListener listener;

    public ProductsAdapter(Context context, List<Product> productsList, ProductClickListener listener) {
        this.context = context;
        this.productsList = productsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemProductBinding productBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_product, parent, false);
        return new ProductHolder(productBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.bind(productsList.get(position));
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

        ItemProductBinding productBinding;

        public ProductHolder(@NonNull ItemProductBinding productBinding) {
            super(productBinding.getRoot());
            this.productBinding = productBinding;
        }

        public void bind(Product productItem) {
            productBinding.setProductItem(productItem);
            productBinding.executePendingBindings();
        }
    }

}
