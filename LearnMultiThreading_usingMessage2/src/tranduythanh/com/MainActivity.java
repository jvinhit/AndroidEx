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
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	Button btndrawbutton;
	EditText editdrawbutton;
	LinearLayout llbutton;
	Handler handler;
	AtomicBoolean isrunning=new AtomicBoolean(false);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btndrawbutton=(Button) findViewById(R.id.btndrawbutton);
		editdrawbutton=(EditText) findViewById(R.id.editdrawbutton);
		llbutton=(LinearLayout) findViewById(R.id.llbutton);
		btndrawbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				doDrawButton();
			}
		});
		handler=new Handler()
		{
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				drawButtonOnLayout(msg);
			}
		};
	}
	public void drawButtonOnLayout(Message msg)
	{
		Button btn=new Button(this);
		btn.setText("Button "+msg.arg1);
		btn.setWidth(100);
		btn.setHeight(30);
		btn.setId(113+msg.arg1);
		/*if(msg.arg1%2==0)
			btn.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
		else
			btn.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
*/		llbutton.addView(btn);
	}
	public void doDrawButton()
	{
		isrunning.set(false);
		llbutton.removeAllViews();
		final int n=Integer.parseInt(editdrawbutton.getText().toString());
		Thread th=new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<=n && isrunning.get();i++)
				{
					SystemClock.sleep(1000);
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
