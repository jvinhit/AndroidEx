package tranduythanh.com;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class OrientationSensorActivity extends Activity
		implements SensorEventListener{
	SensorManager sensorManager;
	Sensor sensor;
	TextView txtmsg;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orientation_sensor);
		txtmsg=(TextView) findViewById(R.id.txtmsg);
	}
	protected void onResume() {
		super.onResume();
		sensorManager=(SensorManager) 
				getSystemService(SENSOR_SERVICE);
		sensor=sensorManager.getDefaultSensor
				(Sensor.TYPE_ORIENTATION);
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}
	@Override
	public void onAccuracyChanged
(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType()==
				Sensor.TYPE_ORIENTATION)
		{
			String msg="Azimuth :"+
						event.values[0]+"\n";
			msg+="Pitch :"+
						event.values[1]+"\n";
			msg+="Roll :"+
						event.values[2]+"\n";
			txtmsg.setText(msg);
		}
	}
}
