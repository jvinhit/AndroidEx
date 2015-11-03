package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends Activity {
	EditText txtid,txtname;
	Button btnsave;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		final Intent intent=getIntent();
		Bundle bundle=intent.getBundleExtra("DATA");
		final Person p=(Person) bundle.getSerializable("per");
		btnsave =(Button) findViewById(R.id.btnluu);
		txtid=(EditText) findViewById(R.id.txtma);
		txtname=(EditText) findViewById(R.id.txtten);
		txtid.setText(p.getId()+"");
		txtname.setText(p.getName());
		txtid.setEnabled(false);
		btnsave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				p.setName(txtname.getText()+"");
				setResult(MainActivity.SAVE_EDIT_EMPLOYEE, intent);
				finish();
			}
		});
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_edit, menu);
		return true;
	}
}
