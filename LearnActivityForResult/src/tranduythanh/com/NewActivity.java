package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewActivity extends Activity {
	EditText txtid,txtname;
	Button btnsave;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);
		txtid=(EditText) findViewById(R.id.txtma);
		txtname=(EditText) findViewById(R.id.txtten);
		btnsave=(Button) findViewById(R.id.btnluu);
		btnsave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent=getIntent();
				Bundle bundle=new Bundle();
				int id=Integer.parseInt(txtid.getText().toString());
				String name=txtname.getText().toString();
				Person p=new Person(id,name);
				bundle.putSerializable("per", p);
				intent.putExtra("DATA", bundle);
				setResult(MainActivity.SAVE_NEW_EMPLOYEE, intent);
				finish();
			}
		});
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_new, menu);
		return true;
	}
}
