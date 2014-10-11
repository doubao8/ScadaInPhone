package com.hollysys.scada.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hollysys.scada.R;
import com.hollysys.scada.basic.ExitApplication;

@SuppressLint("SetJavaScriptEnabled")
public class SvgActivity extends Activity {

	private WebView webview = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);

		Bundle bundle = this.getIntent().getBundleExtra("svgInfo");

		webview = new WebView(this);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setBuiltInZoomControls(true);

		webview.getSettings().setUseWideViewPort(true);
		webview.getSettings().setLoadWithOverviewMode(true);

		webview.setHorizontalScrollBarEnabled(true);
		webview.setVerticalScrollBarEnabled(true);

		webview.loadUrl("file:///android_asset/svg/" + bundle.getString("svg")
				+ "/index.html");
		webview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return false;
			}
		});

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(webview);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
