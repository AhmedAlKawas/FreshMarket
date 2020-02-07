package com.example.freshmarket.view.home_page;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.freshmarket.R;
import com.example.freshmarket.adapter.CategoriesAdapter;
import com.example.freshmarket.adapter.SliderAdapter;
import com.example.freshmarket.databinding.ActivityHomepageBinding;
import com.example.freshmarket.network.model.response.Category;
import com.example.freshmarket.view.category.CategoryDetailsActivity;
import com.example.freshmarket.view_model.CategoriesViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity {

    private ActivityHomepageBinding homeBinding;
    private CategoriesViewModel categoriesViewModel;
    private List<Integer> imagesResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_homepage);

        initListeners();
        initView();

        categoriesViewModel.getCategories();

    }

    private void initListeners() {
        categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        categoriesViewModel.returnCategoriesList().observe(this, categoriesList -> {
            if (null != categoriesList) {

                homeBinding.appbarLayout.contentLayout.sliderIndicatorCi.setVisibility(View.VISIBLE);
                homeBinding.appbarLayout.contentLayout.sliderVp.setVisibility(View.VISIBLE);
                homeBinding.appbarLayout.contentLayout.progressbar.setVisibility(View.GONE);
                setCategoriesAdapter(categoriesList);

            } else{
                homeBinding.appbarLayout.contentLayout.progressbar.setVisibility(View.GONE);
                Toast.makeText(this, getString(R.string.try_again), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void initView() {
        setSupportActionBar(homeBinding.appbarLayout.toolbar);
        assert (null != getSupportActionBar());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setUpNavDrawer();
        setSliderAdapter();
    }

    private void setCategoriesAdapter(List<Category> categoriesList) {
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this, categoriesList,
                category -> {
                    Intent intent = new Intent(HomepageActivity.this,
                            CategoryDetailsActivity.class);
                    intent.putExtra(getString(R.string.categoty), (Serializable) category);
                    startActivity(intent);
                });
        homeBinding.appbarLayout.contentLayout.categoriesRv.setAdapter(categoriesAdapter);
    }

    private void setUpNavDrawer() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                homeBinding.drawerLayout, homeBinding.appbarLayout.toolbar, R.string.openDrawer,
                R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we don't want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we don't want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        homeBinding.drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    private void setSliderAdapter(){

        imagesResources = new ArrayList<>();
        imagesResources.add(R.drawable.slider1);
        imagesResources.add(R.drawable.slider1);
        imagesResources.add(R.drawable.slider1);
        imagesResources.add(R.drawable.slider1);
        imagesResources.add(R.drawable.slider1);

        SliderAdapter adapter = new SliderAdapter(HomepageActivity.this, imagesResources);
        homeBinding.appbarLayout.contentLayout.sliderVp.setAdapter(adapter);
        homeBinding.appbarLayout.contentLayout.sliderIndicatorCi
                .setViewPager(homeBinding.appbarLayout.contentLayout.sliderVp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
}
