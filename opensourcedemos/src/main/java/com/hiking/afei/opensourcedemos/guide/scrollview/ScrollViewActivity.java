package com.hiking.afei.opensourcedemos.guide.scrollview;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hiking.afei.opensourcedemos.R;
import com.hiking.afei.opensourcedemos.guide.main.GuideMainActivity;

public class ScrollViewActivity extends AppCompatActivity {
    private LinearLayout llAnim;
    private MyScrollView scrollView;
    private Button btn_scroll_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);

        llAnim= (LinearLayout) findViewById(R.id.ll_scrollview_anim);
        scrollView= (MyScrollView) findViewById(R.id.scrollView);
        btn_scroll_enter= (Button) findViewById(R.id.btn_scrollview_enter);

        btn_scroll_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScrollViewActivity.this, GuideMainActivity.class));
                finish();
            }
        });

        //设置动画效果
        scrollView.setOnScrollChangedListener(new MyScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChange(int top, int oldTop) {
                if(top>oldTop){//上滑
                    Animation animation= AnimationUtils.loadAnimation(ScrollViewActivity.this,R.anim.show);
                    llAnim.setVisibility(View.VISIBLE);
                    llAnim.startAnimation(animation);
                }else{//下滑
                    Animation animation= AnimationUtils.loadAnimation(ScrollViewActivity.this,R.anim.close);
                    llAnim.setVisibility(View.INVISIBLE);
                    llAnim.startAnimation(animation);
                }
            }
        });


    }
}
