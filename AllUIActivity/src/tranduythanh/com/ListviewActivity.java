package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListviewActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		final String arr2[]={"Tèo","Tý","Bin","Bo"};
		final String arr[]=getResources()
						.getStringArray(R.array.arrperson);
		ListView lv=(ListView) findViewById(R.id.lvperson);
		ArrayAdapter<String>adapter=new ArrayAdapter<String>
				(this, android.R.layout.simple_list_item_1, arr);
		lv.setAdapter(adapter);
		final TextView txt=(TextView) findViewById(R.id.txtselection);
		lv.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0,
					View arg1, 
					int arg2,
					long arg3) {
				txt.setText("position :"+arg2+" ; value ="+arr[arg2]);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_listview, menu);
		return true;
	}

}
