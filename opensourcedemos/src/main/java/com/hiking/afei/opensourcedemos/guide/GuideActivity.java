package com.hiking.afei.opensourcedemos.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hiking.afei.opensourcedemos.R;
import com.hiking.afei.opensourcedemos.guide.carousel.CarouselActivity;
import com.hiking.afei.opensourcedemos.guide.scrollview.ScrollViewActivity;
import com.hiking.afei.opensourcedemos.guide.splash.SplashActivity;
import com.hiking.afei.opensourcedemos.guide.viewflipper.ViewFlipperActivity;
import com.hiking.afei.opensourcedemos.guide.viewpager.ViewPagerActivity;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);


        //进入Splash引导页的demo
        findViewById(R.id.guide_splash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this, SplashActivity.class));
            }
        });

        findViewById(R.id.guide_viewPager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this,ViewPagerActivity.class));
            }
        });

        findViewById(R.id.guide_viewFlipper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this, ViewFlipperActivity.class));
            }
        });

        findViewById(R.id.guide_scrollView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this, ScrollViewActivity.class));
            }
        });

        findViewById(R.id.guide_carousel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this, CarouselActivity.class));
            }
        });

    }

}
