package tranduythanh.com;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnstart,btncancel;
	PendingIntent pintent=null;
	AlarmManager alarm=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnstart=(Button) findViewById(R.id.btnStartAlarm);
		btnstart.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				/*Intent intent=new Intent(MainActivity.this, 
						MyAlarmService.class);*/
				Intent intent=new NewIntent(MainActivity.this, MyAlarmService.class);
				pintent=PendingIntent.getService
							(MainActivity.this, 0, intent,
								PendingIntent.FLAG_CANCEL_CURRENT);
				alarm=(AlarmManager) getSystemService(ALARM_SERVICE);
				Calendar cal=Calendar.getInstance();
				cal.setTimeInMillis(System.currentTimeMillis());
				cal.add(Calendar.SECOND, 5);
				alarm.set(AlarmManager.RTC_WAKEUP, 
						cal.getTimeInMillis(), pintent);
				Toast.makeText(MainActivity.this, 
						"Start Alarm", 
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
							"Stop Alarm", 
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

}
