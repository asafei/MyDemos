package com.hiking.afei.opensourcedemos.guide.carousel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.hiking.afei.opensourcedemos.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class CarouselActivity extends AppCompatActivity implements ImageBannerFramLayout.ImageClickListener{
    //private ImageBarnnerViewGroup viewGroup;

    private ImageBannerFramLayout imageBannerFramLayout;
    private int[] vRes={
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);


        //计算出当前手机的宽度
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //int width=dm.widthPixels;
        C.WIDTH=dm.widthPixels;

//        viewGroup= (ImageBarnnerViewGroup) findViewById(R.id.carousel_img_group);
//
//
//
//        for (int i=0;i<vRes.length;i++){
//            ImageView iv=new ImageView(this);
//
//            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            iv.setLayoutParams(new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
//            iv.setImageResource(vRes[i]);
//            viewGroup.addView(iv);
//        }
//
//        viewGroup.setListener(this);

        List<Bitmap> list=new ArrayList<Bitmap>();
        imageBannerFramLayout= (ImageBannerFramLayout) findViewById(R.id.carousel_img_layout);
        for (int i=0;i<vRes.length;i++){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),vRes[i]);
            list.add(bitmap);
        }
        imageBannerFramLayout.setImgListener(this);
        imageBannerFramLayout.addBitmaps(list);


    }

    @Override
    public void clickImage(int pos) {
        Toast.makeText(this,"pos="+pos, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void clickImageIndex(int pos) {
//        Toast.makeText(this,"pos="+pos, Toast.LENGTH_SHORT).show();
//    }
}
