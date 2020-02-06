package com.example.freshmarket.view.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.freshmarket.R;
import com.example.freshmarket.databinding.ActivitySplashBinding;
import com.example.freshmarket.utils.PrefManager;
import com.example.freshmarket.view.home_page.HomepageActivity;
import com.example.freshmarket.view.onboarding.OnBoardingActivity;

public class SplashActivity extends AppCompatActivity {

    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding splashBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_splash);
        prefManager = new PrefManager(this);
        splashScreen();
    }

    private void splashScreen() {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                if (prefManager.Is_First_LAUNCH())
                    goToOnBoarding();
                else
                    goToHomePage();
            }
        };
        handler.postDelayed(r, 2000);
    }

    private void goToOnBoarding() {
        startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
        finish();
    }

    private void goToHomePage() {
        startActivity(new Intent(SplashActivity.this, HomepageActivity.class));
        finish();
    }

}
