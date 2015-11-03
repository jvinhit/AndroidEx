package tranduythanh.com;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnStatus;
	TextView txtmsg;
	protected void onCreate(Bundle 
			savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnStatus=(Button) findViewById(R.id.button1);
		txtmsg=(TextView) findViewById(R.id.textView1);
		btnStatus.setOnClickListener(new 
				View.OnClickListener() {
			public void onClick(View arg0) {
				new MyStatusTask().execute();
			}
		});
	}
	public String doGetStatus()
	{
		String msg="";
		try{
			ConnectivityManager cm = (ConnectivityManager)
					getSystemService(CONNECTIVITY_SERVICE);
			NetworkInfo ni =
					cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			boolean isWifiAvail = ni.isAvailable();
			boolean isWifiConn = ni.isConnected();
			ni = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			boolean isMobileAvail = ni.isAvailable();
			boolean isMobileConn = ni.isConnected();
			msg="WiFi\nAvail = "+ isWifiAvail +
					"\nConn = " + isWifiConn +
					"\nMobile\nAvail = "+ isMobileAvail +
					"\nConn = " + isMobileConn;
		}
		catch(Exception e)	{}
		return msg;
	}
	private class MyStatusTask extends 
				AsyncTask<Void, Void, String>
	{
		@Override
		protected String doInBackground(Void... params) {
			return doGetStatus();
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			txtmsg.setText(result);
		}
	}
}
