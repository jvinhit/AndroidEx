package tranduythanh.com;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnstart,btncancel;
	PendingIntent pintent=null;
	AlarmManager alarm=null;
	TextView txtmsg;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtmsg=(TextView) findViewById(R.id.txtmsg);
		btnstart=(Button) findViewById(R.id.btnStartAlarm);
		btnstart.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent=new Intent(MainActivity.this, 
						MyBroadcastReceiver.class);
				intent.putExtra("msg_alarm", 
						"Message from Main: Hello MybroadcastReceiver");
				pintent=PendingIntent.getBroadcast
							(MainActivity.this, 0, intent,
								PendingIntent.FLAG_CANCEL_CURRENT);
				alarm=(AlarmManager) getSystemService(ALARM_SERVICE);
				Calendar cal=Calendar.getInstance();
				cal.setTimeInMillis(System.currentTimeMillis());
				cal.add(Calendar.SECOND, 5);
				alarm.set(AlarmManager.RTC_WAKEUP, 
						cal.getTimeInMillis(), pintent);
				Toast.makeText(MainActivity.this, 
						"Start for broadcast", 
						Toast.LENGTH_LONG).show();
			}
		});
		btncancel=(Button) findViewById(R.id.btnCancelAlarm);
		btncancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(alarm!=null)
				{
					alarm.cancel(pintent);
					Toast.makeText(MainActivity.this, 
							"Stop for broadcast", 
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	protected void onResume() {
		super.onResume();
		Intent intent=getIntent();
		if(intent!=null){
			Bundle bundle= intent.getExtras();
			if(bundle!=null){
				String msg=bundle.getString("msg_alarm");
				txtmsg.setText(msg);
			}
		}
	}
}//end MainActivity
