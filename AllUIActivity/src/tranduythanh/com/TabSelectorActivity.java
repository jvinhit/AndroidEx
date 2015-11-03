package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.Toast;

public class TabSelectorActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_selector);
		final TabHost tab=(TabHost) findViewById
				(android.R.id.tabhost);
		tab.setup();
		TabHost.TabSpec spec;
		spec=tab.newTabSpec("t1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("1-Login");
		tab.addTab(spec);
		spec=tab.newTabSpec("t2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("2-Help");
		tab.addTab(spec);
		tab.setCurrentTab(0);
		tab.setOnTabChangedListener(new 
				TabHost.OnTabChangeListener() {
			public void onTabChanged(String arg0) {
				String s="Tab tag ="+arg0 +"; index ="+
							tab.getCurrentTab();
				Toast.makeText(getApplicationContext(), 
						s, Toast.LENGTH_LONG).show();}
		});
	}

}
