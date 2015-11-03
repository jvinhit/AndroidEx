package tranduythanh.com;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnactiondial,btnactioncall;
	EditText numberEntry;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		numberEntry=(EditText) findViewById(R.id.editphonenumber);
		btnactiondial= (Button) findViewById(R.id.btnactiondial);
		btnactiondial.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try
				{
				Uri number = Uri.parse("tel:" +
						numberEntry.getText().toString());
				Intent dial = new Intent(
						Intent.ACTION_DIAL, number);
				startActivity(dial);
				}
				catch(Exception e)
				{
					Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
				}
			}
		});
		
		btnactioncall= (Button) findViewById(R.id.btnactioncall);
		btnactioncall.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Uri number = Uri.parse("tel:" +
						numberEntry.getText().toString());
				Intent dial = new Intent(
						Intent.ACTION_CALL, number);
				startActivity(dial);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
