package tranduythanh.com;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText editdata;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editdata=(EditText) findViewById(R.id.editText1);
		Button btn=(Button) findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent=new Intent(Intent.ACTION_DIAL,
						Uri.parse("tel:5556"));
				startActivity(intent);
			}
		});
		
		if(savedInstanceState!=null &&
				savedInstanceState.containsKey("KEY"))
		{
			String data=savedInstanceState.getString("KEY");
			editdata.setText(data);
		}
		if(savedInstanceState==null)
		{
			Toast.makeText(this, "savedInstanceState null", Toast.LENGTH_LONG).show();
		}
		//if(savedInstanceState.containsKey("KEY")==false)
		//	Toast.makeText(this, "ko chua key", Toast.LENGTH_LONG).show();
	}
	protected void onRestoreInstanceState
					(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if(savedInstanceState!=null &&
				savedInstanceState.containsKey("KEY"))
		{
			String data=savedInstanceState.getString("KEY");
			editdata.setText(data);
		}
	}
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("KEY", editdata.getText()+"");
		super.onSaveInstanceState(outState);
	}
	public void saveActivityPreference()
	{
		// Create or retrieve the activity 
		//preference object.
		SharedPreferences activityPreferences = 
				getPreferences(Activity.MODE_PRIVATE);
		// Retrieve an editor to modify the
		// shared preferences.
		SharedPreferences.Editor editor =
				activityPreferences.edit();
		// Store new primitive types in the shared 
		//preferences object.
		editor.putString("currentTextValue", 
				editdata.getText().toString());
		// Commit changes.
		editor.commit();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
}
