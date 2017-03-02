package com.example.yow.easystock.BottomBar.News;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.yow.easystock.R;

/**
 * Created by 12205 on 2016/7/25.
 */
public class NewsFragment extends Fragment {

    View rootView;
    WebView webView;
    ProgressBar pbar;

    public static NewsFragment newInstance() {

        Bundle args = new Bundle();

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_news, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        pbar = (ProgressBar)rootView.findViewById(R.id.progressBar1);
        webView = (WebView) rootView.findViewById(R.id.news_web);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new myWebViewClient());
        webView.loadUrl("http://business.sohu.com/");

    }

    public boolean canGoBack() {

        return webView.canGoBack();
    }

    public void goBack() {

        webView.goBack();
    }

    //implement progressbar before the website completely loaded
    public class myWebViewClient extends android.webkit.WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub

            super.onPageFinished(view, url);
            //progressbar set to invisible
            pbar.setVisibility(View.GONE);

        }


    }
}
