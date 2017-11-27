package com.hiking.afei.opensourcedemos.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.hiking.afei.opensourcedemos.R;

import java.net.URL;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        WebView webView= (WebView) findViewById(R.id.show_webview);
        webView.loadUrl(url);

    }
}
