package com.example.freshmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.example.freshmarket.R;
import com.example.freshmarket.databinding.ItemOnboardingBinding;
import com.example.freshmarket.databinding.ItemSliderBinding;
import com.example.freshmarket.interfaces.OnBoardingListener;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private List<Integer> imagesResources;

    public SliderAdapter(Context context, List<Integer> imagesResources) {
        this.context = context;
        this.imagesResources = imagesResources;
    }

    @Override
    public int getCount() {
        return imagesResources.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemSliderBinding sliderBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_slider, container, false);

        sliderBinding.setImageResource(imagesResources.get(position));

        container.addView(sliderBinding.getRoot());
        return sliderBinding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
