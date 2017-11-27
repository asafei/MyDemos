package com.hiking.afei.opensourcedemos.guide.viewflipper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.hiking.afei.opensourcedemos.R;
import com.hiking.afei.opensourcedemos.guide.main.GuideMainActivity;

public class ViewFlipperActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private ViewFlipper viewFlipper;
    private Button btn_viewFlipper;
    private LinearLayout llIndicator;

    private GestureDetector gestureDetector;
    private int index=0;//当前是第几屏

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewflipper);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        btn_viewFlipper= (Button) findViewById(R.id.btn_flipper_enter);
        llIndicator= (LinearLayout) findViewById(R.id.ll_flipper_indicator);


        btn_viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), GuideMainActivity.class));
                finish();
            }
        });
        initIndicator();
    }

    private void initIndicator() {
        int width= (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                10f,getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(width,width);
        lp.rightMargin=2*width;

        for (int i=0;i<viewFlipper.getChildCount();i++){
            View view=new View(this);
            view.setId(i);
            view.setBackgroundResource(i==0?R.drawable.dot_focus:R.drawable.dot_normal);

            view.setLayoutParams(lp);
            llIndicator.addView(view);
        }

        gestureDetector=new GestureDetector(this);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    //监听滑动动作
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
        if (e1.getX()>e2.getX()){
            viewFlipper.showNext();
            index=index<3?index+1:0;
        }else if(e1.getX()<e2.getX()){
            viewFlipper.showPrevious();
            index=index>0?index-1:3;
        }else{
            return false;
        }

        changeIndicator();
        //处理完
        return true;
    }

    private void changeIndicator() {
        for (int i=0;i<viewFlipper.getChildCount();i++){
            llIndicator.getChildAt(i).setBackgroundResource(index==i?
            R.drawable.dot_focus:R.drawable.dot_normal);
        }
    }

    //监听触摸动作
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
