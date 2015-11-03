package tranduythanh.com;

import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	BroadcastReceiver battery=null;
	TextView txtmsg;
	ImageView icon;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtmsg=(TextView) findViewById(R.id.txtmsg);
		icon=(ImageView) findViewById(R.id.imageView1);
	}
	protected void onResume() {
		super.onResume();
		battery=new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				int level =
						intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
				int maxValue =
						intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
				int batteryStatus =
						intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
				int batteryHealth =
						intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
				int batteryPlugged =
						intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
				String batteryTech =
						intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
				int batteryIcon =
						intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, -1);
				float batteryVoltage =
						(float) intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,
								-1) / 1000;
				boolean battery =
						intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT,
								false);
				float batteryTemp =
						(float) intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10;
				int chargedPct = (level * 100)/maxValue ;
				String batteryInfo = "Battery Info:\nHealth=" +batteryHealth+"\n" +
						"Status="+batteryStatus+"\n" +
						"Charged % = "+chargedPct+"%\n"+
						"Plugged = " + batteryPlugged + "\n" +
						"Type = " + batteryTech + "\n" +
						"Voltage = " + batteryVoltage + " volts\n" +
						"Temperature = " + batteryTemp + "°C\n"+
						"Battery present = " + battery + "\n";
				txtmsg.setText(batteryInfo);
				icon.setImageResource(batteryIcon);
				Toast.makeText(MainActivity.this, "Battery state changed",
						Toast.LENGTH_LONG).show();
			}
		};
		registerReceiver(battery, new IntentFilter
				(Intent.ACTION_BATTERY_CHANGED));
	}
	protected void onPause() {
		super.onPause();
		if(battery!=null)
		{
			unregisterReceiver(battery);
			battery=null;
		}
	}
}
