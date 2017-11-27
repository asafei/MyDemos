package com.hiking.afei.opensourcedemos.toasty;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Toast;

import com.hiking.afei.opensourcedemos.R;
import com.hiking.afei.opensourcedemos.utils.IntentUtil;
import com.hiking.afei.opensourcedemos.webview.ShowActivity;

import es.dmoral.toasty.Toasty;

import static android.graphics.Typeface.BOLD_ITALIC;

public class ToastyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toasty);
        /*
        可以在这里配置相关的信息
        Toasty.Config.getInstance()
            .setErrorColor(@ColorInt int errorColor) // optional
            .setInfoColor(@ColorInt int infoColor) // optional
            .setSuccessColor(@ColorInt int successColor) // optional
            .setWarningColor(@ColorInt int warningColor) // optional
            .setTextColor(@ColorInt int textColor) // optional
            .tintIcon(boolean tintIcon) // optional (apply textColor also to the icon)
            .setToastTypeface(@NonNull Typeface typeface) // optional
            .setTextSize(int sizeInSp) // optional
            .apply(); // required
         */


        findViewById(R.id.toasty_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.error(getBaseContext(),"这是 error toasty", Toast.LENGTH_SHORT,true).show();
            }
        });
        findViewById(R.id.toasty_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.success(getBaseContext(),"这是一个 success toasty", Toast.LENGTH_SHORT,true).show();
            }
        });
        findViewById(R.id.toasty_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.info(getBaseContext(),"这是一个 info toasty", Toast.LENGTH_SHORT,true).show();
            }
        });
        findViewById(R.id.toasty_warning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.warning(getBaseContext(),"这是一个 waring toasty", Toast.LENGTH_SHORT,true).show();
            }
        });
        findViewById(R.id.toasty_usual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.normal(getBaseContext(), "这是一个 normal Toasty without icon").show();
            }
        });
        findViewById(R.id.toasty_usual_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取相关drawable
                Drawable icon = getResources().getDrawable(R.drawable.users_48);
                Toasty.normal(getBaseContext(), "这是一个 normal Toasty with icon", icon).show();
            }
        });


        findViewById(R.id.toasty_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toasty.custom(getBaseContext(), "这是一个自定义的 Toast",
//                        R.drawable.ic_error_outline_white_48dp,1, 1, true,
//                        false).show();



                Toasty.Config.getInstance()
                        .setTextColor(Color.GREEN)
                        .setToastTypeface(Typeface.createFromAsset(getAssets(), "PCap Terminal.otf"))
                        .apply();
                Toasty.custom(getBaseContext(), "sudo kill -9 everyone", getResources().getDrawable(R.drawable.info_cat_48),
                        Color.BLACK, Toast.LENGTH_SHORT, true, true).show();
                Toasty.Config.reset(); // Use this if you want to use the configuration above only once
            }
        });

        findViewById(R.id.toasty_format_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.info(getBaseContext(), getFormattedMessage()).show();
            }
        });



        findViewById(R.id.toasty_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentUtil.openUrl(getBaseContext(),"https://github.com/GrenderG/Toasty");
            }
        });
    }





    private CharSequence getFormattedMessage() {
        final String prefix = "Formatted ";
        final String highlight = "bold italic";
        final String suffix = " text";
        SpannableStringBuilder ssb = new SpannableStringBuilder(prefix).append(highlight).append(suffix);
        int prefixLen = prefix.length();
        ssb.setSpan(new StyleSpan(BOLD_ITALIC),
                prefixLen, prefixLen + highlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }
}
