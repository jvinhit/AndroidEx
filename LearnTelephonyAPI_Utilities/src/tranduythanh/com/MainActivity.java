package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button btncallstate,btncallstatelistener
	,btntelephonyservice,btnworkingphonenumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btncallstate=(Button) findViewById(R.id.btnrequestingcallstate);
		btncallstate.setOnClickListener(this);
		btncallstatelistener=(Button) findViewById(R.id.btnstatelistener);
		btncallstatelistener.setOnClickListener(this);
		btntelephonyservice=(Button) findViewById(R.id.btntelephonyservice);
		btntelephonyservice.setOnClickListener(this);
		btnworkingphonenumber=(Button) findViewById(R.id.btntelephonyservice);
		btnworkingphonenumber.setOnClickListener(this);
		EditText edit=(EditText) 
				findViewById(R.id.editText1);
		edit.addTextChangedListener(
				new PhoneNumberFormattingTextWatcher());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		if(arg0==btncallstate)
		{
			doRequestingCallState();
		}
		else if(arg0==btncallstatelistener)
		{
			doRequestingCallState_listener();
		}
		else if(arg0==btntelephonyservice)
		{
			doTelephonyService();
		}
		else if(arg0==btnworkingphonenumber)
		{
			doWorkingWithPhoneNumber();
		}
	}
	public void doRequestingCallState()	{
		TelephonyManager telManager = (TelephonyManager)
				getSystemService(TELEPHONY_SERVICE);
		int callStatus = telManager.getCallState();
		String callState = null;
		switch (callStatus) {
		case TelephonyManager.CALL_STATE_IDLE:
			callState = "Phone is idle.";
			break;
		case TelephonyManager.CALL_STATE_OFFHOOK:
			callState = "Phone is in use.";
			break;
		case TelephonyManager.CALL_STATE_RINGING:
			callState = "Phone is ringing!\n";
			callState+=telManager.getLine1Number();
			break;
		}
		Toast.makeText(this, callState, 
				Toast.LENGTH_LONG).show();
	}
	public String getCallStateString(int state)
	{
		String callState = null;
		switch (state) {
		case TelephonyManager.CALL_STATE_IDLE:
			callState = "Phone is idle.";
			break;
		case TelephonyManager.CALL_STATE_OFFHOOK:
			callState = "Phone is in use.";
			break;
		case TelephonyManager.CALL_STATE_RINGING:
			callState = "Phone is ringing!";
			break;
		}
		return callState;
	}
	public void doRequestingCallState_listener()
	{
		TelephonyManager telManager = (TelephonyManager)
				getSystemService(TELEPHONY_SERVICE);
		telManager.listen(new PhoneStateListener() {
			public void onCallStateChanged(
					int state, String incomingNumber) {
				String newState = getCallStateString(state);
				if (state == 
						TelephonyManager.CALL_STATE_RINGING) {
					newState+="\n"+incomingNumber;
				} 
				Toast.makeText(MainActivity.this, newState, 
						Toast.LENGTH_LONG).show();
			}
		}, PhoneStateListener.LISTEN_CALL_STATE);
	}
	public void doTelephonyService()
	{
		TelephonyManager telMng=(TelephonyManager)
				getSystemService(TELEPHONY_SERVICE);
		telMng.listen(new PhoneStateListener(){
		        public void onServiceStateChanged 
		        (ServiceState serviceState){
		          String phonestate;
		          switch(serviceState.getState()){
		          case ServiceState.STATE_EMERGENCY_ONLY: 
		        	  phonestate ="STATE_EMERGENCY_ONLY";break;
		          case ServiceState.STATE_IN_SERVICE: 
		        	  phonestate ="STATE_IN_SERVICE";break;
		          case ServiceState.STATE_OUT_OF_SERVICE: 
		        	  phonestate ="STATE_OUT_OF_SERVICE";break;
		          case ServiceState.STATE_POWER_OFF: 
		        	  phonestate ="STATE_POWER_OFF";break;
		          default:phonestate = "Unknown";
		          }  
		          Toast.makeText(MainActivity.this, phonestate, 
							Toast.LENGTH_LONG).show();
		    } 
		}, PhoneStateListener.LISTEN_SERVICE_STATE);		
	}
	public void doWorkingWithPhoneNumber()
	{
		String formattedNumber =
			PhoneNumberUtils.formatNumber("9995551212");
		Toast.makeText(MainActivity.this, formattedNumber, 
				Toast.LENGTH_LONG).show();
		if(PhoneNumberUtils
				.isEmergencyNumber(formattedNumber))
		{
			//process here
		}
	}
}
