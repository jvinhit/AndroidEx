package tranduythanh.com;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class MyService3 extends Service {
	boolean isRunning=true;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	public void onDestroy() {
		super.onDestroy();isRunning=false;	}
	public void onStart(Intent intent, int startId) {
		Thread th=new Thread(new Runnable() {
			long startingtime=SystemClock.currentThreadTimeMillis();
			long tics=0;
			public void run() {
				for(int i=0;i<120 && isRunning;i++){
					tics=SystemClock.currentThreadTimeMillis()-startingtime;
					Intent myfilter=new Intent("matos.action.GOSERVICE3");
					String msg=i+" values: "+tics;
					myfilter.putExtra("servicedata", msg);
					sendBroadcast(myfilter);
					SystemClock.sleep(1000);
				}
			}
		});
		th.start();
	}
}
