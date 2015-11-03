package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button btnstop;
	Intent intentMyService;
	TextView txtmsg;
	ComponentName service;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtmsg=(TextView) findViewById(R.id.textView1);
		intentMyService=new Intent(this, MyService1.class);
		service=startService(intentMyService);
		txtmsg.setText("Begin: "+service.getClassName());
		btnstop=(Button) findViewById(R.id.btnstop);
		btnstop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stopService(intentMyService);
				txtmsg.setText("After Stop: "+
						service.getClassName() );
			}
		});
	}
}

