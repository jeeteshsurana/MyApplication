package com.jeeteshsurana.template.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.jeeteshsurana.template.R;


public class WebViewFragment extends Fragment {
    public WebView webView;
    public String web_url;
    private ProgressBar progressBar;
    public View viewLayout;
    private String TAG="WebFragment";
    ActionBar actionBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewLayout = inflater.inflate(R.layout.webview_fragment, container, false);

        try {
            web_url = getArguments().getString("webUrl");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return viewLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView =  view.findViewById(R.id.webView);
        progressBar = view.findViewById(R.id.progressbar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webload(web_url);
        progressBar.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void webload(String Url) {
        try {
            WebSettings webSettings = webView.getSettings();
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webView.getSettings().setAppCacheEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webSettings.setDomStorageEnabled(true);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            webSettings.setUseWideViewPort(true);
            webView.setWebChromeClient(new MyChromeClient());
            webView.setWebViewClient(new MyWebViewClient());
            webView.loadUrl(Url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyChromeClient extends WebChromeClient
    {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
            if (newProgress==100)
            {
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    private class  MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
