package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView txtmsg1,txtmsg2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtmsg1=(TextView) findViewById(R.id.txtmsg1);
		txtmsg2=(TextView) findViewById(R.id.txtmsg2);
		View v=findViewById(R.id.mylayout);
		v.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch
				(View arg0, MotionEvent arg1) {
				handleTouch(arg1);
				return false;
			}
		});
	}
	public void handleTouch(MotionEvent m)
	{
    	int pointerCount = m.getPointerCount();
    	for (int i = 0; i < pointerCount; i++)
    	{
    		int x = (int) m.getX(i);
    		int y = (int) m.getY(i);    		
    		int id = m.getPointerId(i);
    		int action = m.getActionMasked();
    		int actionIndex = m.getActionIndex();
    		String actionString;
    		switch (action)
    		{
    			case MotionEvent.ACTION_DOWN:
    				actionString = "DOWN";
    				break;
    			case MotionEvent.ACTION_UP:
    				actionString = "UP";
    				break;	
    			case MotionEvent.ACTION_POINTER_DOWN:
    				actionString = "PNTR DOWN";
    				break;
    			case MotionEvent.ACTION_POINTER_UP:
        			actionString = "PNTR UP";
        			break;
    			case MotionEvent.ACTION_MOVE:
    				actionString = "MOVE";
    				break;
    			default:
    				actionString = "";
    		}
    		String touchStatus = "Action: " + actionString + 
    				" Index: " + actionIndex + " ID: " + id +
    				" X: " + x + " Y: " + y;
    		if(id==0)
    			txtmsg1.setText(touchStatus);
    		else
    			txtmsg2.setText(touchStatus);
    	}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
