package tranduythanh.com;

import java.util.Date;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView txtmsg;
	Button btnstop;
	ComponentName service;
	Intent intentMyService;
	MyMainLocalReceiver receiver;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtmsg=(TextView) findViewById(R.id.textView1);
		intentMyService=new Intent(this, MyService3.class);
		service=startService(intentMyService);
		txtmsg.setText("Service is started...");
		IntentFilter mainFilter=new 
				IntentFilter("matos.action.GOSERVICE3");
		receiver=new MyMainLocalReceiver();
		registerReceiver(receiver, mainFilter);
		btnstop=(Button) findViewById(R.id.btnstop);
		btnstop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				stopService(intentMyService);
				txtmsg.append("Service is stopped");
			}
		});
	}
	protected void onDestroy() {
		super.onDestroy();
		stopService(intentMyService);
		unregisterReceiver(receiver);
	}
	public class MyMainLocalReceiver extends BroadcastReceiver {
		public void onReceive(Context arg0, Intent arg1) {
			String servicesData=arg1.getStringExtra("servicedata");
			Log.e("MAIN>>", servicesData +" - receiving data "
					+SystemClock.elapsedRealtime());
			String now="\n"+servicesData+" -- "+new Date().toString();
			txtmsg.append(now);
		}
	}
}
