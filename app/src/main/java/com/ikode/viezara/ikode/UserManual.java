package com.ikode.viezara.ikode;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by viezara on 21/04/2016.
 */
public class UserManual extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermanual);


        String url = "http://ikonasoftware.com/EN/user_manual.html";
        WebView webView = (WebView) findViewById(R.id.webUM);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
