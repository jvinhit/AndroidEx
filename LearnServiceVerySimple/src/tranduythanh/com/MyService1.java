package tranduythanh.com;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class MyService1 extends Service {
	public IBinder onBind(Intent arg0) {
		return null;
	}
	public void onCreate() {
		super.onCreate();
		Log.i("<MyServices1 - onCreate>", " I am alive 1");
	}
	public int onStartCommand
		(Intent intent, int flags, int startId) {
		Log.i("<MyServices1 - onStartCommand>", 
				" I am onStartCommand");
		return super.onStartCommand
				(intent, flags, startId);
	}
	public void onStart(Intent intent, int startId) {
		Log.i("<MyServices1 - onStart>", " I am onStart");
	}
	public void onDestroy() {
		super.onDestroy();
		Log.i("<MyServices1 - onDestroy>", " I am onDestroy");
	}
}
