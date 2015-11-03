package tranduythanh.com;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyAlarmService extends Service {

	public IBinder onBind(Intent arg0) {
		return null;
	}
	public void onCreate() {
		super.onCreate();
		Toast.makeText(getApplicationContext(), 
				"MyAlarmService.onCreate()",
				Toast.LENGTH_LONG).show();
		this.stopSelf();
	}
	public void onDestroy() {
		super.onDestroy();
	}
	public void onStart(Intent intent, int startId) {
		Toast.makeText(this, "MyAlarmService.onStart()", 
				Toast.LENGTH_LONG).show();
		
	}
}
