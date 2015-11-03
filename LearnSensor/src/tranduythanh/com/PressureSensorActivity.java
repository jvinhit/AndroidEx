package tranduythanh.com;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class PressureSensorActivity extends Activity 
		implements SensorEventListener	{
	SensorManager sensorManager;
	Sensor sensor;
	TextView txtmsg;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pressure_sensor);
		txtmsg=(TextView) findViewById(R.id.txtmsg);
	}
	protected void onResume() {
		super.onResume();
		sensorManager =(SensorManager) 
				getSystemService(SENSOR_SERVICE);
		sensor=sensorManager.
				getDefaultSensor(Sensor.TYPE_PRESSURE);
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_FASTEST);
	}
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}
	@Override
	public void onAccuracyChanged
	(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType()==Sensor.TYPE_PRESSURE)
		{
			txtmsg.setText("Pressured: "+event.values[0]);
		}
	}
}
