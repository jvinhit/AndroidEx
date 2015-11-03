package tranduythanh.com;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {
	TextView txtketqua;
	Button btnBack;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		btnBack=(Button) findViewById(R.id.btnBack);
		txtketqua=(TextView) findViewById(R.id.txtketqua);
		Intent callerIntent=getIntent();
		Bundle packageFromCaller=
				callerIntent.getBundleExtra("MyPackage");
		int a=packageFromCaller.getInt("soa");
		int b=packageFromCaller.getInt("sob");
		giaipt(a, b);
		btnBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
	public void giaipt(int a,int b)
	{
		String kq="";
		if(a==0 && b==0)
		{
			kq="Vô số nghiệm";
		}
		else if(a==0 && b!=0)
		{
			kq="Vô nghiệm";
		}
		else
		{
			DecimalFormat dcf=new DecimalFormat("0.##");
			kq=dcf.format(-b*1.0/a);
		}
		txtketqua.setText(kq);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}
}
