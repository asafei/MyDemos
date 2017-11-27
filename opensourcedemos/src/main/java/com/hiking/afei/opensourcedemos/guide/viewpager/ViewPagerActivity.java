package com.hiking.afei.opensourcedemos.guide.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.hiking.afei.opensourcedemos.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout llIndocator;

    private PagerAdapter adapter;
    private List<Fragment> fragments=new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        viewPager= (ViewPager) findViewById(R.id.viewPager);
        llIndocator= (LinearLayout) findViewById(R.id.ll_viewPager_indicator);


        //创建fragment
        for(int i=0;i<4;i++){
            ContentFragment fragment=new ContentFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("index",i);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        adapter=new MyViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i=0;i<fragments.size();i++){
                    llIndocator.getChildAt(i).setBackgroundResource(position==i?
                        R.drawable.dot_focus:R.drawable.dot_normal);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initIndicator();

    }

    /**
     * 初始化指示器
     */
    private void initIndicator() {

        int width= (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
             ,10f,getResources().getDisplayMetrics());

        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(width,width);
        lp.rightMargin=2*width;
        for (int i=0;i<fragments.size();i++){
            View view=new View(this);
            view.setBackgroundResource(i==0?R.drawable.dot_focus:R.drawable.dot_normal);

            view.setLayoutParams(lp);
            llIndocator.addView(view);
        }
    }
}
