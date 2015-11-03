package tranduythanh.com;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn=(Button) findViewById(R.id.btnCall);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				doCall();
			}
		});
	}
	public void doCall()
	{
		EditText edt=(EditText) findViewById(R.id.txtPhone);
		String snum=edt.getText().toString();
		Intent intent=new Intent(Intent.ACTION_CALL, 
				Uri.parse("tel:"+snum));
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
