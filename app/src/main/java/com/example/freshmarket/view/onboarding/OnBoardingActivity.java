package com.example.freshmarket.view.onboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.freshmarket.R;
import com.example.freshmarket.adapter.OnBoadringAdapter;
import com.example.freshmarket.databinding.ActivityOnBoardingBinding;
import com.example.freshmarket.model.OnBoardingItem;
import com.example.freshmarket.utils.PrefManager;
import com.example.freshmarket.view.home_page.HomepageActivity;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {

    private List<OnBoardingItem> onBoardingItems;
    private ActivityOnBoardingBinding onBoardingBinding;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBoardingBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_on_boarding);
        prefManager = new PrefManager(this);
        setViewPagerAdapter();
    }

    private void setViewPagerAdapter() {
        createItemsList();
        OnBoadringAdapter adapter = new OnBoadringAdapter(this, onBoardingItems, () -> {
//            Stop on boarding activity from being viewed
            prefManager.stop_First_LAUNCH();

            startActivity(new Intent(OnBoardingActivity.this, HomepageActivity.class));
            finish();
        });
        onBoardingBinding.viewpagerOnboarding.setAdapter(adapter);
        onBoardingBinding.circleIndicatorOnboarding.setViewPager(onBoardingBinding.viewpagerOnboarding);
    }

    private void createItemsList() {
        onBoardingItems = new ArrayList<>();
        onBoardingItems.add(new OnBoardingItem(getString(R.string.lorem_title),
                getString(R.string.lorem), R.drawable.farm));
        onBoardingItems.add(new OnBoardingItem(getString(R.string.lorem_title),
                getString(R.string.lorem), R.drawable.fruit_bascket));
        onBoardingItems.add(new OnBoardingItem(getString(R.string.lorem_title),
                getString(R.string.lorem), R.drawable.truck));
    }
}
