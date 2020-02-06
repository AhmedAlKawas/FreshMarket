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
import com.example.freshmarket.interfaces.OnBoardingListener;
import com.example.freshmarket.model.OnBoardingItem;

import java.util.List;

public class OnBoadringAdapter extends PagerAdapter {

    private Context context;
    private List<OnBoardingItem> onBoardingItems;
    private OnBoardingListener listener;

    public OnBoadringAdapter(Context context, List<OnBoardingItem> onBoardingItems,
                             OnBoardingListener listener) {
        this.context = context;
        this.onBoardingItems = onBoardingItems;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return onBoardingItems.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemOnboardingBinding itemOnboardingBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_onboarding, container, false);

        itemOnboardingBinding.setOnboardingItem(onBoardingItems.get(position));

        if (position == onBoardingItems.size() - 1)
            itemOnboardingBinding.finishTv.setVisibility(View.VISIBLE);

        itemOnboardingBinding.finishTv.setOnClickListener(view -> {
            listener.onFinishClicked();
        });

        container.addView(itemOnboardingBinding.getRoot());
        return itemOnboardingBinding.getRoot();
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
