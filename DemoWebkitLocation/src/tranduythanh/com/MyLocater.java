package tranduythanh.com;

import java.util.Random;

import android.app.Activity;
import android.location.Location;
import android.widget.Toast;

public class MyLocater {
	private String commondata="New location!";
	private Location locFake; 
	private Activity context;
	public MyLocater(Activity context)
	{
		this.context=context;
		fakeLocation();
	}
	public void fakeLocation()
	{
		locFake=new Location("fake");
		Random rd=new Random();
		locFake.setLatitude(rd.nextDouble()*199);
		locFake.setLongitude(rd.nextDouble()*199);
		commondata="New location!";
	}
	public double getLatitude()
	{
		return locFake.getLatitude();
	}
	public double getLongtitude()
	{
		return locFake.getLongitude();
	}
	public void htmlPassing2Android(String data)
	{
		Toast.makeText(context,"1.\n"+ 
					commondata, 
					Toast.LENGTH_LONG).show();
		commondata=data;
		Toast.makeText(context,"2.\n"+ 
					commondata, 
					Toast.LENGTH_LONG).show();
	}
	public void setData(String data)
	{
		this.commondata=data;
	}
	public String getData()
	{
		return this.commondata;
	}
}
