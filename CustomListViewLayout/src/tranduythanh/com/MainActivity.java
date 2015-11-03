package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {
	TextView selection;
	String items[]={"Hà nội","huế","spa","côn sơn",
					"vũng tàu","đà nẵng"};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		selection=(TextView) findViewById(R.id.selection);
		setListAdapter(new MyArrayAdapter(this));
	}
	protected void onListItemClick(ListView l, View v, 
			int position, long id) {
		super.onListItemClick(l, v, position, id);
		selection.setText(items[position]);
	}
	
	public class MyArrayAdapter extends ArrayAdapter<String>
	{
		private Activity context;
		public MyArrayAdapter(Activity context){
			super(context, R.layout.listviewitem_row, items);
			this.context=context;
		}
		public View getView(int position, View convertView, 
				ViewGroup parent) {
			LayoutInflater inflater=
					this.context.getLayoutInflater();
			View row=inflater.inflate(R.layout.listviewitem_row, null);
			TextView txtdisplay=(TextView) 
					row.findViewById(R.id.textView1);
			ImageView imgdisplay=(ImageView) 
					row.findViewById(R.id.imageView1);
			txtdisplay.setText(items[position]);
			if(items[position].length()>=5){
				imgdisplay.setImageResource(R.drawable.img_lvi1);
			}else{
				imgdisplay.setImageResource(R.drawable.img_lvi2);}
			return row;
		}
	}//end MyArrayAdapter
}//end MainActivity
