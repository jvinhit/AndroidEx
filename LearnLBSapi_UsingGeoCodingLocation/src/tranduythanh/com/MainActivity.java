package tranduythanh.com;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		doGeoCodingFromLocationName("ĐH Công Nghiệp");
	}
	public void doGeoCodingFromLocation()
	{
		//You should get location from onLocationChanged()
		Location location=new Location("Fake location");
		location.setLatitude(-113.115);
		location.setLongitude(100.114);
		if (Geocoder.isPresent()) {
			Geocoder coder = new Geocoder(this);
			try {
				List<Address> addresses = coder.getFromLocation(
						location.getLatitude(), 
						location.getLongitude(), 3);
				if (addresses != null) {
					String locInfo="";
					for (Address namedLoc : addresses) {
						String placeName = namedLoc.getLocality();
						String featureName = namedLoc.getFeatureName();
						String country = namedLoc.getCountryName();
						String road = namedLoc.getThoroughfare();
						locInfo+=(String.format("[%s][%s][%s][%s]\n",
								placeName, featureName, road, country));
						int addIdx = namedLoc.getMaxAddressLineIndex();
						for (int idx = 0; idx <= addIdx; idx++) {
							String addLine = namedLoc.getAddressLine(idx);
							locInfo+=(String.format("Line %d: %s\n", idx,
									addLine));
						}
					}
					Toast.makeText(this, locInfo, Toast.LENGTH_LONG).show();
				}
			} catch (IOException e) {
				Log.e("GPS", "Failed to get address", e);
			}
		} else {
			Toast.makeText(MainActivity.this, "No geocoding available",
					Toast.LENGTH_LONG).show();
		}
	}
	public void doGeoCodingFromLocationName(String locName)
	{
		if (Geocoder.isPresent()) {
			Geocoder coder = new Geocoder(this);
			try {
				List<Address> addresses = coder.getFromLocationName(locName, 3);
				if (addresses != null) {
					StringBuilder locInfo = new StringBuilder("Results:\n");
					double lat = 0f;
					double lon = 0f;
					for (Address namedLoc : addresses) {
						lat = namedLoc.getLatitude();
						lon = namedLoc.getLongitude();
						locInfo.append("Location: ").append(lat)
						.append(", ").append(lon).append("\n");
					}
					Toast.makeText(this, locInfo, Toast.LENGTH_LONG).show();
				}
			} catch (IOException e) {
				Log.e("GPS", "Failed to get address", e);
			}
		} else {
			Toast.makeText(MainActivity.this, "No geocoding available",
					Toast.LENGTH_LONG).show();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
