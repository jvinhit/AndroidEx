package tranduythanh.com;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.view.Menu;

public class MainActivity extends Activity
		implements OnSharedPreferenceChangeListener {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Context context=getApplicationContext();
		SharedPreferences pre=PreferenceManager
				.getDefaultSharedPreferences(context);
		pre.registerOnSharedPreferenceChangeListener(this);
	}
	public void onSharedPreferenceChanged
			(SharedPreferences pre, String key) {
		//process here
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
