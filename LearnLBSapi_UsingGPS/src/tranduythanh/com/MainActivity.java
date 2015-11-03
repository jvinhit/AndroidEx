package tranduythanh.com;

import java.util.Date;
import java.util.Locale;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
		implements LocationListener {
	Button btnusinggps;
	EditText editlatitude,editlongtitude;
	TextView txtmsg;
	LocationManager locationManager=null;
	Location lastlocation=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnusinggps=(Button) findViewById(R.id.btnusinggps);
		btnusinggps.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				doUsingGPS();
			}
		});
		editlatitude=(EditText) findViewById(R.id.editlatitude);
		editlongtitude=(EditText) findViewById(R.id.editlongtitude);
		txtmsg=(TextView) findViewById(R.id.txtmsg);
	}
	public void doUsingGPS()
	{
		try
		{
			locationManager =
					(LocationManager)getSystemService(LOCATION_SERVICE);
			Criteria criteria = new Criteria();
			criteria.setAccuracy(Criteria.NO_REQUIREMENT);
			criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
			String bestProvider = locationManager.getBestProvider(criteria, true);
			Location loc = locationManager.getLastKnownLocation(bestProvider);
			if(loc==null)
			{
				Toast.makeText(this, "Location is NULL", Toast.LENGTH_LONG).show();
			}
			else
			{
				if (loc.getTime() > (new Date().getTime() - 1000*2*60)) //2Minutes back
				{
					Toast.makeText(this, "LastKnownLocation is good enough, using this location!", Toast.LENGTH_LONG).show();
					Log.d("demo", "LastKnownLocation is good enough, using this location!");
				}
				else
				{
					Toast.makeText(this, "Requesting network location updates...", Toast.LENGTH_LONG).show();
					Log.d("demo", "Requesting network location updates...");
					locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
				}
			}
		}
		catch(Exception e)
		{
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	@Override
	public void onLocationChanged(Location location) {
		String locInfo = String.
				format("Current loc = (%f, %f) @ (%f meters up)",
						location.getLatitude(), 
						location.getLongitude(),
						location.getAltitude() );
		if (lastlocation != null) 
		{
			float distance = location.distanceTo(lastlocation);
			locInfo += String.
					format("\n Distance from last = %f meters", 
							distance);
		}
		lastlocation = location;
		txtmsg.setText(locInfo);
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}
