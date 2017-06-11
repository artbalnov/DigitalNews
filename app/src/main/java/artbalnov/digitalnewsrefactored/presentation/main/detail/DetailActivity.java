package artbalnov.digitalnewsrefactored.presentation.main.detail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import artbalnov.digitalnewsrefactored.R;
import artbalnov.digitalnewsrefactored.constants.Constants;

public class DetailActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        String postUrl = getIntent().getStringExtra(Constants.KEY_POST_URL);

        mWebView = (WebView) findViewById(R.id.wvPost);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.loadUrl(postUrl);


    }

    public static void startActivity(Context context, String postUrl) {
        Intent starter = new Intent(context, DetailActivity.class);
        starter.putExtra(Constants.KEY_POST_URL, postUrl);
        context.startActivity(starter);
    }
}
