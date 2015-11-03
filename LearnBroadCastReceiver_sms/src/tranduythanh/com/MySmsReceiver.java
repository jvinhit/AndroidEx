package tranduythanh.com;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySmsReceiver extends BroadcastReceiver {
	public static final String SMS_EXTRA="pdus";
	public static final String SMS_URI="content://sms/inbox";
	public static final String BODY = "body";
	public static final String ADDRESS = "address";
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();
		String messages = "";
		if ( extras != null )
		{
			Object[] smsExtra = (Object[]) extras.get( SMS_EXTRA );
			for ( int i = 0; i < smsExtra.length; ++i ){
				SmsMessage sms = SmsMessage.
						createFromPdu((byte[])smsExtra[i]);
				String body = sms.getMessageBody().toString();
				String address = sms.getOriginatingAddress();
				messages += "SMS from " + address + " :\n";                    
				messages += body + "\n";
			}
			Toast.makeText( context, messages, 
					Toast.LENGTH_SHORT ).show();
		}
	}
}
