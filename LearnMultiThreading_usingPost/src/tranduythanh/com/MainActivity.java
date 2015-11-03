package tranduythanh.com;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ProgressBar bar;
	Handler handler=new Handler();
	Button btnstart;
	TextView lblmsg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bar=(ProgressBar) findViewById(R.id.progressBar1);
		btnstart=(Button) findViewById(R.id.btnstart);
		btnstart.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				doStart();
			}
		});
		lblmsg=(TextView) findViewById(R.id.textView1);
	}
	public void doStart()
	{
		bar.setProgress(0);
		Thread th=new Thread(new Runnable() {
			public void run() {
				for(int i=1;i<=100;i++)
				{
					SystemClock.sleep(100);
					handler.post(new Runnable() {
						public void run() {
							doUpdateUI();
						}
					});
				}
				handler.post(new Runnable() {
					public void run() {
						doStopUpdateUI();
					}
				});
			}
		});
		th.start();
	}
	public void doUpdateUI()
	{
		bar.incrementProgressBy(1);
		lblmsg.setText(bar.getProgress()+"%");
	}
	public void doStopUpdateUI()
	{
		Toast.makeText(this, "Finish", 
				Toast.LENGTH_LONG).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
