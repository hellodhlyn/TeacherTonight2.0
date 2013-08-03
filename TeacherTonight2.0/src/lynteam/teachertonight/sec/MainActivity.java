package lynteam.teachertonight.sec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity{
	WebView mWebView;
	ProgressBar progressBar;
	String lurl = "http://lynteam.pusku.com/xe/tcrtn/";
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		
		mWebView = (WebView) findViewById(R.id.web);
		mWebView.loadUrl(lurl);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebViewClient(new LYnWebViewClient());
		
		progressBar = (ProgressBar) findViewById(R.id.progress);
		
		mWebView.setWebChromeClient(new WebChromeClient() 
        {  
               public void onProgressChanged(WebView view, int progress) 
               {  
                   if (progress<100)
                   {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                   }
                   else if (progress==100)
                   {
                    progressBar.setVisibility(ProgressBar.GONE);
                   }
                   progressBar.setProgress(progress);  
               }   
        });
	}
	
    public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.refresh:
			mWebView.reload();
			return false;
		case R.id.lunch:
			Intent Feed = new Intent(MainActivity.this, Feed.class);
			startActivity(Feed);
			return false;
		}
	    return false;
    }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


	private class LYnWebViewClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url){
			view.loadUrl(url);
			return true;
		}
	}
}