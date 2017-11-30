package com.hiking.afei.opensourcedemos.guide.carousel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hiking.afei.opensourcedemos.R;

import java.util.List;

import static android.os.Build.VERSION.SDK_INT;

/**
 * Created by Afei on 2017/11/30.
 */

public class ImageBannerFramLayout extends FrameLayout implements ImageBarnnerViewGroup.ImageBannerViewGroupLinsnner,ImageBarnnerViewGroup.ImageBannerListener{

    private ImageBarnnerViewGroup imageBarnnerViewGroup;
    private LinearLayout linearLayout;
    public ImageBannerFramLayout(@NonNull Context context) {
        super(context);
        
        initImageBannerViewGroup();
        initDotLinearLayout();
    }

    public ImageBannerFramLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initImageBannerViewGroup();
        initDotLinearLayout();
    }

    public ImageBannerFramLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initImageBannerViewGroup();
        initDotLinearLayout();
    }

    public void addBitmaps(List<Bitmap> list){
        for (int i=0;i<list.size();i++){
            Bitmap bitmap=list.get(i);
            addBitmapToImageBannerViewGroup(bitmap);
            addDotToLinearLayout();
        }
    }

    private void addDotToLinearLayout() {
        ImageView iv=new ImageView(getContext());
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5,5,5,5);
        iv.setLayoutParams(lp);
        iv.setImageResource(R.drawable.dot_normal_1);
        linearLayout.addView(iv);


    }

    private void addBitmapToImageBannerViewGroup(Bitmap bitmap) {
        ImageView iv=new ImageView(getContext());
//
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setLayoutParams(new ViewGroup.LayoutParams(C.WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT));
        iv.setImageBitmap(bitmap);
        imageBarnnerViewGroup.addView(iv);
    }

    /**
     *  初始化底部圆点布局
     */
    private void initDotLinearLayout() {
        linearLayout=new LinearLayout(getContext());
        FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40);

        linearLayout.setLayoutParams(lp);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(Color.RED);
        addView(linearLayout);

        FrameLayout.LayoutParams layoutParams= (LayoutParams) linearLayout.getLayoutParams();
        layoutParams.gravity=Gravity.BOTTOM;
        linearLayout.setLayoutParams(layoutParams);


        //底部圆点透明度
        //这里有一个知识点，3.0以后和以前使用setAlphs()方法的方式不同
        if (SDK_INT> Build.VERSION_CODES.HONEYCOMB){
            linearLayout.setAlpha(0.5f);
        }else {
            linearLayout.getBackground().setAlpha(100);
        }

    }

    /**
     * 初始化 自定义的图片轮播核心类
     */
    private void initImageBannerViewGroup() {
        imageBarnnerViewGroup=new ImageBarnnerViewGroup(getContext());
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageBarnnerViewGroup.setLayoutParams(lp);

        imageBarnnerViewGroup.setImageBannerViewGroupLinsnner(this);//将listener传递给layout
        imageBarnnerViewGroup.setListener(this);
        addView(imageBarnnerViewGroup);
    }

    @Override
    public void selectImage(int index) {
        int count=linearLayout.getChildCount();
        for (int i=0;i<count;i++){
            ImageView iv= (ImageView) linearLayout.getChildAt(i);
            if (i==index){
                iv.setImageResource(R.drawable.dot_focus_1);
            }else {
                iv.setImageResource(R.drawable.dot_normal_1);
            }
        }
    }

    @Override
    public void clickImageIndex(int pos) {
        imgListener.clickImage(pos);
    }


    private ImageClickListener imgListener;

    public ImageClickListener getImgListener() {
        return imgListener;
    }

    public void setImgListener(ImageClickListener imgListener) {
        this.imgListener = imgListener;
    }

    public interface ImageClickListener{
        void clickImage(int pos);
    }
}
