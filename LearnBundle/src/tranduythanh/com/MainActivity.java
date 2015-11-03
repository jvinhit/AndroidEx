package tranduythanh.com;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText txta,txtb;
	Button btnketqua;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txta=(EditText) findViewById(R.id.txta);
		txtb=(EditText) findViewById(R.id.txtb);
		btnketqua=(Button) findViewById(R.id.btnketqua);
		btnketqua.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent myIntent=new Intent(MainActivity.this, ResultActivity.class);
				Bundle bundle=new Bundle();
				int a=Integer.parseInt(txta.getText().toString());
				int b=Integer.parseInt(txtb.getText().toString());
				bundle.putInt("soa", a);
				bundle.putInt("sob", b);
				myIntent.putExtra("MyPackage", bundle);
				startActivity(myIntent);
			}
		});
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
