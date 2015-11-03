package tranduythanh.com;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class TemperatureActivity extends Activity
	implements SensorEventListener{
	private SensorManager sensorManager;
	private Sensor sensorTemperature;
	private TextView txtmsg;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temperature);
		txtmsg=(TextView) findViewById(R.id.txtmsg);

		sensorManager=(SensorManager) 
				getSystemService(SENSOR_SERVICE);
		sensorTemperature=sensorManager.getDefaultSensor
					(Sensor.TYPE_AMBIENT_TEMPERATURE);
	}
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, 
				sensorTemperature, 
				SensorManager.SENSOR_DELAY_NORMAL);
	}
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType()!=
				Sensor.TYPE_AMBIENT_TEMPERATURE)
			return;
		txtmsg.setText("Power:"+sensorTemperature.getPower());
	}
	@Override
	public void onAccuracyChanged
	(Sensor sensor, int accuracy) {
	}
}
