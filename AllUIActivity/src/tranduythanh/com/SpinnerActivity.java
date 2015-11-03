package tranduythanh.com;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends Activity {

	String arr[]={"Hàng điện tử", 
				  "Hàng hóa chất",
				  "Hàng gia dụng"};
	TextView selection;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);
		selection =(TextView) findViewById(R.id.selection);
		Spinner spin=(Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>
		(
		 this,
		 android.R.layout.simple_spinner_item,
		 arr
		);
		adapter.setDropDownViewResource
			(android.R.layout.simple_list_item_single_choice);
		spin.setAdapter(adapter);
		spin.setOnItemSelectedListener(new MyProcessEvent());
	}
	private class MyProcessEvent implements
			OnItemSelectedListener
	{

		public void onItemSelected(AdapterView<?> arg0,
				View arg1, 
				int arg2,
				long arg3) {
			selection.setText(arr[arg2]);
		}
		public void onNothingSelected(AdapterView<?> arg0) {
			selection.setText("");
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_spinner, menu);
		return true;
	}

}
