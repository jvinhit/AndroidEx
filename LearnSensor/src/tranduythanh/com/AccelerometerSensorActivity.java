package tranduythanh.com;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.widget.TextView;

public class AccelerometerSensorActivity extends Activity 
		implements SensorEventListener {
	SensorManager sensorManager;
	Sensor sensor;
	TextView txtmsg;
	TextView txtxyz;
	long lastUpdate=System.currentTimeMillis();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accelerometer_sensor);
		txtmsg=(TextView) findViewById(R.id.txtmsg);
		txtxyz=(TextView) findViewById(R.id.txtxyz);
		setRequestedOrientation(ActivityInfo.
				SCREEN_ORIENTATION_LANDSCAPE);
	}
	protected void onResume() {
		super.onResume();
		sensorManager =(SensorManager) 
				getSystemService(SENSOR_SERVICE);
		sensor=sensorManager.getDefaultSensor
				(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}
	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}
	@Override
	public void onAccuracyChanged
	(Sensor arg0, int arg1) {
		
	}
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			float x=event.values[0];
			float y=event.values[1];
			float z=event.values[2];
			txtxyz.setText("x:"+x+"\ny:"+y+"\nz:"+z);
			float accelerationSquareRoot=(x*x+y*y+z*z)/
					(SensorManager.GRAVITY_EARTH*
							SensorManager.GRAVITY_EARTH);
			long actualTime=System.currentTimeMillis();
			if(accelerationSquareRoot>=2){
				if(actualTime-lastUpdate<=200)return;
				lastUpdate=actualTime;
				String msg="Device Was Shaken";
				txtmsg.setText(msg);
			}
		}
	}
}
