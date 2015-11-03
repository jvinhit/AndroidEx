package tranduythanh.com;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListviewListActivity extends ListActivity {

	TextView selection;
	String arr[]={"loan","ngoc","hieu","hoa","dog"};
	//String arr[]={};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_list);
		setListAdapter(new ArrayAdapter<String>
		(this,
		android.R.layout.simple_list_item_1,
		arr));
		selection=(TextView) findViewById(R.id.selection);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String txt="postion = "+position +"; value ="+arr[position];
		selection.setText(txt);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_listview_list, menu);
		return true;
	}

}
