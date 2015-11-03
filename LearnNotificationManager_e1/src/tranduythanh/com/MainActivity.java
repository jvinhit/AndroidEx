package tranduythanh.com;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btnstart,btnstop;
	NotificationManager notifiManager;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnstart=(Button) findViewById(R.id.btnstart);
		btnstop=(Button) findViewById(R.id.btnstop);
		btnstart.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				startNotification();
			}
		});
		btnstop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(notifiManager!=null)
					notifiManager.cancel(113);
			}
		});
	}
	public void startNotification()
	{
		notifiManager=(NotificationManager) 
				getSystemService(NOTIFICATION_SERVICE);
		Notification notifiDetail=new Notification
					(android.R.drawable.ic_menu_info_details,
							"Có thông báo!", 
							System.currentTimeMillis());
		Context context=getApplicationContext();
		CharSequence contextTitle="Bạn muốn tìm kiếm ư?";
		CharSequence contextText="Bấm vào đây để vào Google";
		Intent notifiIntent=new Intent(Intent.ACTION_VIEW, 
				Uri.parse("http://www.google.com"));
		PendingIntent pIntent=PendingIntent
				.getActivity(context, 0, notifiIntent,
						Intent.FLAG_ACTIVITY_NEW_TASK);
		notifiDetail.setLatestEventInfo
			(context, contextTitle, contextText, pIntent);
		notifiManager.notify(113, notifiDetail);
	}
}//End MainActivity
