package tranduythanh.com;

import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity  {

	Button btnmappingintent,btnmappingview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnmappingintent=(Button) findViewById(R.id.btnmappingintent);
		btnmappingview=(Button) findViewById(R.id.btnmappingview);
		btnmappingintent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				doMappingIntent();				
			}
		});
		btnmappingview.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				doMappingView();				
			}
		});
	}
	public void doMappingIntent()
	{
		try
		{
			Location locFake=new Location("FAKE Location");
			locFake.setLatitude(-118.344);
			locFake.setLongitude(34.945);
			String geoURI = String.format("geo:%f,%f",
					locFake.getLatitude(),
					locFake.getLongitude());
			Uri geo = Uri.parse(geoURI);
			Intent geoMap = new Intent(Intent.ACTION_VIEW, geo);
			startActivity(geoMap);
		}
		catch(Exception e)
		{
			Toast.makeText(this, e.toString(),
					Toast.LENGTH_LONG).show();
		}
	}
	public void doMappingView()
	{

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
