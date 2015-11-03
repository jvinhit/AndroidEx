package tranduythanh.com;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.widget.EditText;

public class ProximitySensorActivity extends Activity 
		implements SensorEventListener{
	SensorManager sensorManager;
	Sensor sensor;
	EditText txtName,txtMax,txtReading;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proximity_sensor);
		txtName=(EditText) findViewById(R.id.editProximityName);
		txtMax=(EditText) findViewById(R.id.editProximityMaxRange);
		txtReading=(EditText) findViewById(R.id.editProximityReading);
	}
	protected void onResume() {
		super.onResume();
		sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
		sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		if(sensor==null)
			txtName.setText("Device doesn't Promixity Sensor");
		else{
			txtName.setText(sensor.getName());
			txtMax.setText(sensor.getMaximumRange()+"");
			sensorManager.registerListener(this, sensor,
					SensorManager.SENSOR_DELAY_FASTEST);
		}
	}
	@Override
	protected void onPause() {
		super.onPause();
		if(sensor!=null)
			sensorManager.unregisterListener(this);
	}
	@Override
	public void onAccuracyChanged
	(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType()==Sensor.TYPE_PROXIMITY)
		{
			txtReading.setText(event.values[0]+"");
			if(event.values[0]==0.0)
			{
				if(this.getRequestedOrientation()==
						ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
					{this.setRequestedOrientation
					(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
					this.getWindow().setBackgroundDrawableResource(android.R.color.holo_red_light);
					}
				else
				{
					this.setRequestedOrientation
					(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
					this.getWindow().setBackgroundDrawableResource(android.R.color.holo_green_light);
				}
			}
		}
	}
}
