package com.example.freshmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshmarket.R;
import com.example.freshmarket.databinding.ItemCategoryBinding;
import com.example.freshmarket.interfaces.CategoryClickListener;
import com.example.freshmarket.network.model.response.Category;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryHolder> {

    Context context;
    List<Category> categoryList;
    CategoryClickListener listener;

    public CategoriesAdapter(Context context, List<Category> categoryList, CategoryClickListener listener) {
        this.context = context;
        this.categoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemCategoryBinding categoryBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_category, parent, false);
        return new CategoryHolder(categoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.bind(categoryList.get(position));
        holder.categoryBinding.categoriesItemCl.setOnClickListener(view ->
                listener.onCategoryClicked(categoryList.get(position)));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {

        ItemCategoryBinding categoryBinding;

        public CategoryHolder(@NonNull ItemCategoryBinding categoryBinding) {
            super(categoryBinding.getRoot());
            this.categoryBinding = categoryBinding;
        }

        public void bind(Category categoryItem) {
            categoryBinding.setCategoryItem(categoryItem);
            categoryBinding.executePendingBindings();
        }
    }

}
