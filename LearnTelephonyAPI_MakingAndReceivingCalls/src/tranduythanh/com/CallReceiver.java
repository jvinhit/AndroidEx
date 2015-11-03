package tranduythanh.com;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		String state = intent.getStringExtra
						(TelephonyManager.EXTRA_STATE);
	    if (state.equals
	    		(TelephonyManager.EXTRA_STATE_RINGING)) {
	    	Bundle bundle = intent.getExtras();
	        String phoneNr= bundle
	        				.getString("incoming_number");
	        if(phoneNr.equals("0977113114"))
	        	{
	        		//process to Blacklist
	        	}
	        Toast.makeText(context, phoneNr,
	        		Toast.LENGTH_LONG).show();
	    }
	}
}

