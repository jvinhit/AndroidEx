package tranduythanh.com;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ListviewActivityWithArrayList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_activity_with_array_list);
		final ArrayList<String>list=new ArrayList<String>();
		final EditText txtten=(EditText) findViewById(R.id.txtTen);
		final TextView txtchon=(TextView) findViewById(R.id.txtselection);
		final ListView lv=(ListView) findViewById(R.id.lvperson);
		final ArrayAdapter<String> adapter=new 
		ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
		lv.setAdapter(adapter);
		final Button btn=(Button) findViewById(R.id.btnNhap);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				list.add(txtten.getText()+"");
				adapter.notifyDataSetChanged();
			}
		});
		lv.setOnItemClickListener(new AdapterView
				.OnItemClickListener() {
			public void onItemClick(
					AdapterView<?> arg0,View arg1, 
					int arg2,long arg3) {
				txtchon.setText("position : "+ arg2+
						"; value ="+list.get(arg2));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
				R.menu.activity_listview_activity_with_array_list, menu);
		return true;
	}

}
