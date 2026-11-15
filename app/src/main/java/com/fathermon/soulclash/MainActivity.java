package com.fathermon.soulclash;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.view.WindowManager;
public class MainActivity extends Activity {
  private WebView web;
  @Override public void onCreate(Bundle state){super.onCreate(state);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    web=new WebView(this);web.setWebViewClient(new WebViewClient());web.setWebChromeClient(new WebChromeClient());
    WebSettings settings=web.getSettings();settings.setJavaScriptEnabled(true);settings.setDomStorageEnabled(true);
    settings.setAllowFileAccess(true);settings.setMediaPlaybackRequiresUserGesture(false);
    setContentView(web);web.loadUrl("file:///android_asset/index.html");
  }
  @Override public void onBackPressed(){if(web.canGoBack())web.goBack();else super.onBackPressed();}
  @Override protected void onDestroy(){web.destroy();super.onDestroy();}
}