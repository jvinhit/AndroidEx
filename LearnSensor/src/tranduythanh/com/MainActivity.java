package tranduythanh.com;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnsensor,btnsensorTemperature,btnsensorlight,
	btnsensorpressure,btnsensorproximity,btnaccelerometer,
	btngyroscope,btnorientation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnsensor=(Button) findViewById(R.id.button1);
		btnsensor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				doSensors();
			}
		});
		btnsensorTemperature=(Button) findViewById(R.id.btntemperature);
		btnsensorTemperature.setOnClickListener(new  View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doSensorTemperature();
			}
		});
		btnsensorlight=(Button) findViewById(R.id.btnsensorlight);
		btnsensorlight.setOnClickListener(new  View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doSensorLight();
			}
		});
		btnsensorpressure=(Button) findViewById(R.id.btnsensorpressure);
		btnsensorpressure.setOnClickListener(new  View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doSensorPressure();
			}
		});
		btnsensorproximity=(Button) findViewById(R.id.btnproximity);
		btnsensorproximity.setOnClickListener(new  View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doSensorProximity();
			}
		});
		btnaccelerometer=(Button) findViewById(R.id.btnaccelerometer);
		btnaccelerometer.setOnClickListener(new  View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doSensorAccelerometor();
			}
		});
		btngyroscope=(Button) findViewById(R.id.btngyroscope);
		btngyroscope.setOnClickListener(new  View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doSensorGyroscope();
			}
		});
		btnorientation=(Button) findViewById(R.id.btnorientationsensor);
		btnorientation.setOnClickListener(new  View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doSensorOrientation();
			}
		});
	}
	public void doSensorOrientation()
	{
		Intent in=new Intent(this, OrientationSensorActivity.class);
		startActivity(in);
	}
	public void doSensorGyroscope()
	{
		Intent in=new Intent(this, GyroscopeSensorActivity.class);
		startActivity(in);
	}
	public void doSensorAccelerometor()
	{
		Intent in=new Intent(this, AccelerometerSensorActivity.class);
		startActivity(in);
	}
	public void doSensorProximity()
	{
		Intent in=new Intent(this, ProximitySensorActivity.class);
		startActivity(in);
	}
	public void doSensorPressure()
	{
		Intent in=new Intent(this, PressureSensorActivity.class);
		startActivity(in);
	}
	public void doSensors()
	{
		SensorManager sManager=(SensorManager) 
				getSystemService(SENSOR_SERVICE);
		Sensor aSensor=sManager.getDefaultSensor
				(Sensor.TYPE_ACCELEROMETER);
		Toast.makeText(this, aSensor.getName(), 
				Toast.LENGTH_LONG).show();
		List<Sensor> listSensor=sManager.getSensorList
				(Sensor.TYPE_ALL);
		String msg="";
		for(Sensor s: listSensor)
		{
			msg+=s.getName()+"\n";
		}
		Toast.makeText(this, msg, 
				Toast.LENGTH_LONG).show();
		
		SensorEventListener listener=new 
				SensorEventListener() {
			@Override
			public void onSensorChanged
			(SensorEvent event) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onAccuracyChanged
			(Sensor sensor, int accuracy) {
				// TODO Auto-generated method stub
				
			}
		};
		sManager.registerListener(listener, aSensor, 
				SensorManager.SENSOR_DELAY_UI);
		for(Sensor s: listSensor)
		{
			sManager.registerListener(listener, s, 
					SensorManager.SENSOR_DELAY_UI);
		}
		//Unregisters a listener for all sensors.
		sManager.unregisterListener(listener);
		//Unregisters a listener for the sensors 
		//with which it is registered.
		sManager.unregisterListener(listener, aSensor);
		
		
	}
	public void doSensorTemperature()
	{
		Intent in=new Intent(this, TemperatureActivity.class);
		startActivity(in);
	}
	public void doSensorLight()
	{
		Intent in=new Intent(this, LightSensorActivity.class);
		startActivity(in);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
