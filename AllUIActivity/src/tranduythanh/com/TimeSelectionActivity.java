package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimeSelectionActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_selection);
		doWork();
	}
	public void doWork()
	{
		Button b1= (Button) findViewById(R.id.btnAnalogClocl);
		b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AnalogClock analog=new AnalogClock
						(TimeSelectionActivity.this);
				((LinearLayout)findViewById(R.id.mylayout))
					.addView(analog);
			}
		});
		Button b2= (Button) findViewById(R.id.btnChronometer);
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Chronometer cro=new Chronometer
						(TimeSelectionActivity.this);
				((LinearLayout)findViewById(R.id.mylayout))
					.addView(cro);
			}
		});
		Button b3= (Button) findViewById(R.id.btnTimePicker);
		b3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				OnTimeSetListener callback=new OnTimeSetListener() {
					public void onTimeSet(TimePicker view, 
							int hourOfDay, int minute) {
						((TextView)findViewById(R.id.txtdate))
						.setText(hourOfDay +" - "+
								 minute +"@@@"+
								 view.getCurrentHour() +" - " +
								 view.getCurrentMinute());
					}
				};
				TimePickerDialog time=new TimePickerDialog(
						TimeSelectionActivity.this, 
						callback, 11, 30, true);
				time.show();
			}
		});
		Button b4= (Button) findViewById(R.id.btnDatePickerDialog);
		b4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				OnDateSetListener callback=new OnDateSetListener() {
					public void onDateSet(DatePicker view, int year,
							int monthOfYear,
							int dayOfMonth) {
						((TextView)findViewById(R.id.txtdate))
							.setText((dayOfMonth+1) +"/"+
									(monthOfYear+1)+"/"+year);
					}
				};
				DatePickerDialog pic=new DatePickerDialog(
						TimeSelectionActivity.this, 
							callback, 2012, 11, 30);
				pic.setTitle("My Datetime picker");
				pic.show();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_time_selection, menu);
		return true;
	}

}
