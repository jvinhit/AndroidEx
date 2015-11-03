package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RadioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio);
		
		RadioGroup group=(RadioGroup) findViewById(R.id.radioGroup1);
		int idChecked=group.getCheckedRadioButtonId();
		switch(idChecked)
		{
		case R.id.radrathailong:
			break;
		case R.id.radhailong:
			break;
		case R.id.radtamchapnhan:
			break;
		case R.id.radthayghe:
			break;
		}
		RadioButton rad=(RadioButton) findViewById(R.id.radrathailong);
		if(rad.getId()==idChecked)
		{
			
		}
		if(rad.isChecked())
		{
			rad.setChecked(false);
		}
		group.clearCheck();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_radio, menu);
		return true;
	}

}
