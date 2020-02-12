package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView=(WebView)findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());//important to do this else link will be opened in phone's browser
        webView.loadUrl("https://www.xvideos2.com");
        //webView.loadData("<html><body><h1>Hi there</h1><p>This is my Website</p></body></html>","text/html","UTF-8");
    }
}
