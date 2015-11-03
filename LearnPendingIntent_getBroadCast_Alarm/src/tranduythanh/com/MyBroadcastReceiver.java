package tranduythanh.com;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyBroadcastReceiver 
	extends BroadcastReceiver {
	public void onReceive(Context arg0, Intent arg1) {
		Bundle bundle=arg1.getExtras();
		String msg=bundle.getString("msg_alarm");
		msg+="\n-----------------------\n";
		msg+="Message from MybroadcastReceiver: Hello MainActivity";
		Intent newIntent=new Intent(arg0, MainActivity.class);
		newIntent.putExtra("msg_alarm", msg);
		newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		arg0.startActivity(newIntent);
	}
}
