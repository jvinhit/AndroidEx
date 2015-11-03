package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnsending,btnreceiv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnsending=(Button) findViewById(R.id.btnsendingsms);
		btnreceiv=(Button) findViewById(R.id.btnreceivingsms);
		btnsending.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				doSending();
			}
		});
		btnreceiv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				doReceiving();
			}
		});
	}
	public void doSending()
	{
		final SmsManager sms = SmsManager.getDefault();
		Intent msgSent = new Intent("ACTION_MSG_SENT");
		final PendingIntent pendingMsgSent =
				PendingIntent.getBroadcast(this, 0, msgSent, 0);
		registerReceiver(new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				int result = getResultCode();
				String msg="Send OK";
				if (result != Activity.RESULT_OK) {
					msg="Send failed";
				} 
				Toast.makeText(MainActivity.this, msg, 
						Toast.LENGTH_LONG).show();
			}
		}, new IntentFilter("ACTION_MSG_SENT"));
		sms.sendTextMessage("0987773061", null, "Hello", 
				pendingMsgSent, null);
	}
	public void doReceiving()
	{
		BroadcastReceiver rcvIncoming;
		rcvIncoming = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
		  Bundle data = intent.getExtras();
			if (data != null) {
			  Object pdus[] =(Object[]) data.get("pdus");
			  String message = "New message:\n";
			  String sender = null;
			  for (Object pdu : pdus) {
				SmsMessage part = SmsMessage.
					createFromPdu((byte[])pdu);
					message += part.getDisplayMessageBody();
					sender = part.getDisplayOriginatingAddress();
				}
				Toast.makeText(MainActivity.this, 
				message + "\nFrom: "+sender,Toast.LENGTH_LONG).show();
			}
		  }
		};
		registerReceiver(rcvIncoming, new IntentFilter(
				"android.provider.Telephony.SMS_RECEIVED"));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
