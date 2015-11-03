package tranduythanh.com;

import tranduythanh.com.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	BroadcastReceiver receiver=null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		IntentFilter filter=new IntentFilter
					("android.provider.Telephony.SMS_RECEIVED");
		receiver=new BroadcastReceiver() {
			public void onReceive(Context arg0, Intent arg1) {
				processReceive(arg0, arg1);
			}
		};
		registerReceiver(receiver, filter);
	}
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}
	public void processReceive(Context context, Intent intent)
	{
		Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show();
		TextView lbl=(TextView) findViewById(R.id.textView1);
		String sms_extra="pdus";
		Bundle bundle=intent.getExtras();
		Object []objArr= (Object[]) bundle.get(sms_extra);
		String sms="";
		for(int i=0;i<objArr.length;i++)
		{
			SmsMessage smsMsg=SmsMessage.createFromPdu((byte[]) objArr[i]);
			String body=smsMsg.getMessageBody();
			String address=smsMsg.getDisplayOriginatingAddress();
			sms+=address+":\n"+body+"\n";
		}
		lbl.setText(sms);
	}
}
