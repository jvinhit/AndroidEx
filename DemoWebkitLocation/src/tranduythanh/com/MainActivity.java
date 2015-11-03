package tranduythanh.com;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends Activity {

	MyLocater myloc=null;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myloc=new MyLocater(this);
		WebView browser=(WebView) findViewById(R.id.webView1);
		browser.getSettings().setJavaScriptEnabled(true);
		browser.addJavascriptInterface(myloc, "locater");
		String html="file:///android_asset/mylocation.html";
		browser.loadUrl(html);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
