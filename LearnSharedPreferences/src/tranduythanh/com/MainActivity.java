package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {
	Button btnlogin;
	EditText edituser,editpassword;
	CheckBox chksaveaccount;
	String prefname="my_data";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnlogin=(Button) findViewById(R.id.btnlogin);
		edituser =(EditText) 
				findViewById(R.id.editUser);
		editpassword=(EditText) 
				findViewById(R.id.editPassword);
		chksaveaccount=(CheckBox) 
				findViewById(R.id.chksaveacount);
		restoringPreferences();
		btnlogin.setOnClickListener(
				new View.OnClickListener() {
			public void onClick(View arg0) {
				savingPreferences();
			}
		});
	}
	public void savingPreferences()
	{
		SharedPreferences pre=getSharedPreferences
								(prefname, MODE_PRIVATE);
		SharedPreferences.Editor editor=pre.edit();
		String user=edituser.getText().toString();
		String pwd=editpassword.getText().toString();
		boolean bchk=chksaveaccount.isChecked();
		if(!bchk)
		{
			editor.clear();
		}
		else
		{
			editor.putString("user", user);
			editor.putString("pwd", pwd);
			editor.putBoolean("checked", bchk);
		}
		editor.commit();
	}
	public void restoringPreferences()
	{
		SharedPreferences pre=getSharedPreferences
							(prefname,MODE_PRIVATE);
		boolean bchk=pre.getBoolean("checked", false);
		if(bchk)
		{
			String user=pre.getString("user", "");
			String pwd=pre.getString("pwd", "");
			edituser.setText(user);
			editpassword.setText(pwd);
		}
		chksaveaccount.setChecked(bchk);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
