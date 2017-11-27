package com.hiking.afei.opensourcedemos.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.hiking.afei.opensourcedemos.toasty.ToastyActivity;
import com.hiking.afei.opensourcedemos.webview.ShowActivity;

/**
 * Created by Afei on 2017/10/16.
 */

public class IntentUtil {
    private IntentUtil(){
        throw new UnsupportedOperationException("这个类不能被实例化, 它的方法只能被直接调用");
    }

    public static void openUrl(Context context,String url){
        Uri uri=Uri.parse(url);

        Intent intent=new Intent();
        intent.putExtra("url","https://github.com/GrenderG/Toasty");
        intent.setClass(context, ShowActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}
