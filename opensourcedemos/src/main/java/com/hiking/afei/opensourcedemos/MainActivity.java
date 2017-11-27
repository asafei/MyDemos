package com.hiking.afei.opensourcedemos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hiking.afei.opensourcedemos.guide.GuideActivity;
import com.hiking.afei.opensourcedemos.toasty.ToastyActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toasty的使用
        findViewById(R.id.demo_toasty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ToastyActivity.class);
                startActivity(intent);
            }
        });


        // 引导页的使用
        findViewById(R.id.demo_guide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, GuideActivity.class);
                startActivity(intent);
            }
        });


    }
}
