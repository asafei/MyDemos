package com.hiking.afei.opensourcedemos.guide.carousel;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

/**
 * Created by Afei on 2017/11/29.
 */

public class ImageBarnnerViewGroup extends ViewGroup {
    private int children;//子视图个数
    private int height;//子视图高度
    private int width;//子视图宽度


    private int x;//此时的x的值，代表每一次按下位置的横坐标，每一次移动过程中移动之前的位置横坐标
    private int index=0;//每张图片的索引


    private Scroller scroller;


    /**
     * 要想实现图片的单击事件，可以利用单击变量进行判断；
     * 在离开屏幕的一瞬间去判断变量开关 来判断操作是点击还是移动
     */
    private boolean isClick;//true点击 false不是点击事件

    private ImageBannerListener listener;

    public ImageBannerListener getListener() {
        return listener;
    }

    public void setListener(ImageBannerListener listener) {
        this.listener = listener;
    }

    public interface ImageBannerListener{
        void clickImageIndex(int pos);
    }



    private ImageBannerViewGroupLinsnner imageBannerViewGroupLinsnner;

    public ImageBannerViewGroupLinsnner getImageBannerViewGroupLinsnner() {
        return imageBannerViewGroupLinsnner;
    }

    public void setImageBannerViewGroupLinsnner(ImageBannerViewGroupLinsnner imageBannerViewGroupLinsnner) {
        this.imageBannerViewGroupLinsnner = imageBannerViewGroupLinsnner;
    }

    public interface ImageBannerViewGroupLinsnner{
        void selectImage(int index);
    }





    /**
     * 实现图片轮播底部圆点以及底部圆点切换功能的思路
     *
     * 1、自定义一个继承自FrameLayout布局，利用frameLayout布局的特性（在同一位置放置的不同view最终显示的是最后一个view）
     *  就可以实现底部圆点布局
     * 2、准备素材 底部圆点的素材
     * 3、继承FramLayout，定义一个类，在该类实现过程中，加载刚才自定义的ImaageBannerViewGroup的核心类圆点的布局
     * 用LinearLayout来实现
     */





    /**
     *
     * 自动轮播
     * 采用Timer、TimerTask、Handler三者结合的方式实现轮播
     * 本例使用两种方法来控制，启动自动轮播，称之为startAuto()和stopAuto()
     *
     * 在两个方法的控制过程中，实际上控制的是自动开启轮播图的开关
     * 这就需要一个变量参数来作为自动开启轮播的开关，称之为isAuto（true表示开启，false表示关闭）
     *
     *
     */
    private boolean isAuto=true;//默认开启
    private Timer timer=new Timer();
    private TimerTask task;
    private Handler autoHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    if (++index>=children){//说明此时如果是最后一张图的话，那就就从第一张图从新滑动
                        index=0;
                    }
                    scrollTo(width*index,0);

                    imageBannerViewGroupLinsnner.selectImage(index);
                    break;
            }


            //super.handleMessage(msg);
        }
    };
    private void startAuto(){
        isAuto=true;
    };
    private void stopAuto(){
        isAuto=false;
    }


    public ImageBarnnerViewGroup(Context context) {
        super(context);
        initObj();
    }

    public ImageBarnnerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initObj();
    }

    public ImageBarnnerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initObj();
    }

    private void initObj() {
        scroller=new Scroller(getContext());

        task=new TimerTask() {
            @Override
            public void run() {
                if (isAuto){//开启轮播图
                    autoHandler.sendEmptyMessage(0);
                }else{

                }
            }
        };

        timer.schedule(task,100,3000);

    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){//判断是否滑动完毕
            scrollTo(scroller.getCurrX(),0);
            invalidate();
        }
    }

    /**
     * 在自定义viewgroup中，必须要实现的方法有：测量-》布局-》绘制
     * 对于测量来说就是：onMeature
     * 对于容器的绘制其实就是容器内的子控件的绘制过程，只需要调用系统自带的绘制即可
     * 对于viewgroup绘制过程我们不需要再重写该函数
     *
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 由于要实现的是一个ViewGroup容器，就应该知道该容器中所有的子视图；
         * 要测量viewgroup的宽度和高度，必须先去测量子视图的宽度和高度之和，
         * 才能知道viewgroup的宽度和高度
         */


        //1、求出子视图的个数
        children=getChildCount();
        if (0==children){
            setMeasuredDimension(0,0);
        }else{
            //2、测量子视图的宽度和高度
            measureChildren(widthMeasureSpec,heightMeasureSpec);
            //此时以第一个视图为基准，就是说ViewGroup的高度就是第一个子视图的高度，宽度是第一个子视图的宽度*子视图的个数
            View view=getChildAt(0);

            //3、根据子视图的宽度和高度，求该viewgroup的宽度和高度
            height=view.getMeasuredHeight();//获取子视图的测量高度
            width=view.getMeasuredWidth();//获取子视图的测量宽度
            setMeasuredDimension(width,height);
        }

    }

    /**
     * 事件传递过程中调用方法：需要调用容器的拦截方法 onInterceptTouchEvent
     * 针对该方法，如果该方法返回值为true，自定义的viewgroup就会处理此次拦截事件；
     * 如果返回值为false，自定义的viewgroup就不会接受此次事件的处理过程，就会继续向下传递该事件；
     *
     * 针对自定义的viewgroup，当然希望viewgroup容器处理接受事件，那么返回值就是true
     * 如果返回true的话，真正处理该事件的方法：ontouchEvent
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //return super.onInterceptTouchEvent(ev);
        return true;
    }

    /**
     * 用两种方式事件轮播图的手动轮播
     * 1、利用scrollTo、scrollBy完成轮播图的手动轮播
     * 2、利用Scroller对象，完成轮播图的手动轮播
     *
     * 第一、滑动屏幕图片时，其实就是viewgroup的子视图移动的过程，
     * 只需要知道滑动前后横坐标的值，可以求出此过程中移动的距离，利用scrollBy方法实现图片的滑动
     * 座椅需要两个值：移动之前和移动之后的横坐标的值
     *
     * 第二、在第一个按下的瞬间，移动前和移动后的值是相等的，也就是说按下的一瞬间的那个点的横坐标的值
     *
     * 第三、在不断滑动过程中，会不断的调用ACTION_MOVE方法，此时可以保存移动之前和移动之后的值，
     * 从而计算出滑动的距离
     *
     * 第四、在抬起的一瞬间，需要计算出要滑动到哪张图片的位置上
     *
     * 需要得出将要滑动的哪张图片的索引值
     * （当前viewgroup的滑动位置+每一张图片的宽度／2）／每一张图片的宽度值
     *
     *
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://表示在屏幕上按下的一瞬间
                stopAuto();//按下时禁止自动轮播功能

                if (!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                isClick=true;

                x= (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE://表示按下之后在屏幕上移动的过程
                int  moveX= (int) event.getX();
                int distance=moveX-x;
                scrollBy(-distance,0);
                x=moveX;

                isClick=false;
                break;
            case MotionEvent.ACTION_UP://表示抬起时的一瞬间
                int scrollX=getScrollX();
                index=(scrollX+width/2)/width;
                if (index<0){//此时已经滑动到第一张图片
                    index=0;
                }else if(index>children-1){//此时已经滑动到最后一张图片
                    index=children-1;
                }

                if (isClick){
                    listener.clickImageIndex(index);
                }else{
                    //scrollTo(index*width,0);//第一种方式

                    int dx=index*width-scrollX;
                    scroller.startScroll(scrollX,0,dx,0);
                    postInvalidate();

                    imageBannerViewGroupLinsnner.selectImage(index);
                }




                startAuto();//抬起手指的一瞬间开启图片轮播

                break;
            default:
                break;
        }
        return true;//告诉该viewgroup的父view，我们已经处理好了该事件
    }

    /**
     *
     * @param change  布局位置发生改变时为true，没有发生改变时为false
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean change, int l, int t, int r, int b) {
        if (change){
            int leftMargin=0;
            for (int i=0;i<children;i++){
                View v =getChildAt(i);
                v.layout(leftMargin,0,leftMargin+width,height);
                leftMargin+=width;

            }
        }
    }
}
