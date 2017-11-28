package com.hiking.afei.opensourcedemos.guide.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Afei on 2017/11/28.
 */

public class MyScrollView extends ScrollView {
    private OnScrollChangedListener onScrollChangedListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public OnScrollChangedListener getOnScrollChangedListener() {
        return onScrollChangedListener;
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollChangedListener!=null){
            onScrollChangedListener.onScrollChange(t,oldt);
        }
    }


    public interface OnScrollChangedListener{
        void onScrollChange(int top , int oldTop);
    }
}
