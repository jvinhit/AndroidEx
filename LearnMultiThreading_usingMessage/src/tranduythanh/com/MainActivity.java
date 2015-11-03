package tranduythanh.com;

import java.util.concurrent.atomic.AtomicBoolean;

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

public class MainActivity extends Activity {
	ProgressBar bar;
	Handler handler;
	AtomicBoolean isrunning=new AtomicBoolean(false);
	Button btnstart;
	TextView lblmsg;
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
		handler=new Handler(){
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				bar.setProgress(msg.arg1);
				lblmsg.setText(msg.arg1+"%");
			}
		};
		lblmsg=(TextView) findViewById(R.id.textView1);
	}
	public void doStart()
	{
		bar.setProgress(0);
		isrunning.set(false);
		Thread th=new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<=100 && isrunning.get();i++)
				{
					SystemClock.sleep(100);
					Message msg=handler.obtainMessage();
					msg.arg1=i;
					handler.sendMessage(msg);
				}
			}
		});
		isrunning.set(true);
		th.start();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
