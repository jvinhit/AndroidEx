package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends Activity {

	WebView webbrowser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webbrowser=(WebView) 
				findViewById(R.id.webView1);
		//webbrowser.loadUrl("http://tranduythanh.com");
		webbrowser.getSettings()
			.setJavaScriptEnabled(true);
		loadNguyenVanBao();
	}
	public void loadNguyenVanBao()
	{
		String locate="<iframe width=\"425\" height=\"350\" " +
				"frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" " +
				"marginwidth=\"0\" src=\"https://maps.google.com/maps?"+
				"f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=12+"+
				"Nguy%E1%BB%85n+V%C4%83n+B%E1%BA%A3o,+ph%C6%B0%E1%BB%9Dng+4,+Ho+Chi+Minh+City,+Vietnam&amp;aq=0&amp;oq=12+nguyen+van+bao&amp;sll=38.410558,-95.712891&amp;sspn=34.634589,86.572266&amp;ie=UTF8&amp;hq=&amp;hnear=12+Nguy%E1%BB%85n+V%C4%83n+B%E1%BA%A3o,+ph%C6%B0%E1%BB%9Dng+4,+G%C3%B2+V%E1%BA%A5p,+Ho+Chi+Minh+City,+Vietnam&amp;t=m&amp;z=14&amp;iwloc=A&amp;output=embed\"></iframe><br /><small><a href=\"https://maps.google.com/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=12+Nguy%E1%BB%85n+V%C4%83n+B%E1%BA%A3o,+ph%C6%B0%E1%BB%9Dng+4,+Ho+Chi+Minh+City,+Vietnam&amp;aq=0&amp;oq=12+nguyen+van+bao&amp;sll=38.410558,-95.712891&amp;sspn=34.634589,86.572266&amp;ie=UTF8&amp;hq=&amp;hnear=12+Nguy%E1%BB%85n+V%C4%83n+B%E1%BA%A3o,+ph%C6%B0%E1%BB%9Dng+4,+G%C3%B2+V%E1%BA%A5p,+Ho+Chi+Minh+City,+Vietnam&amp;t=m&amp;z=14&amp;iwloc=A\" style=\"color:#0000FF;text-align:left\">View Larger Map</a></small>";
		String html="<html>" +
					"<body>"+
					"<br/>"+
						"ĐHCN location:"+
					"<br/>"+
						locate+
					"</body>"+
					"</html>";
		webbrowser.loadData(html, "text/html", "utf-8");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
