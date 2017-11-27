package com.hiking.afei.opensourcedemos.guide.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hiking.afei.opensourcedemos.R;
import com.hiking.afei.opensourcedemos.guide.main.GuideMainActivity;

public class SplashActivity extends AppCompatActivity {
    //设置延迟时间为3秒
    private static final long DELAY_TIME=3000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //三秒之后进入主界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, GuideMainActivity.class));
                //引导界面销毁
                finish();
            }
        },DELAY_TIME);
    }
}
