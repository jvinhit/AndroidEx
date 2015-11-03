package tranduythanh.com;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final int NEW_EMPLOYEE=113;
	public static final int EDIT_EMPLOYEE=114;
	public static final int SAVE_NEW_EMPLOYEE=115;
	public static final int SAVE_EDIT_EMPLOYEE=116;
	ListView lv;
	int posselected=-1;
	ArrayList<Person>list=new ArrayList<Person>();
	ArrayAdapter<Person>adapter=null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list.add(new Person(1, "teo"));
		list.add(new Person(2, "ty"));
		lv=(ListView) findViewById(R.id.lvnhanvien);
		adapter=new ArrayAdapter<Person>
		(this, android.R.layout.simple_list_item_1, list);
		lv.setAdapter(adapter);
		lv.setOnItemLongClickListener(
		new AdapterView.OnItemLongClickListener() {
		 public boolean onItemLongClick(
		 AdapterView<?> arg0, View arg1,int arg2, long arg3) {
			posselected=arg2;
			return false;}
		  });
		registerForContextMenu(lv);
	}
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.mycontextmenu, menu);
	}
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.mnunew:
			doStartNew();
			break;
		case R.id.mnuedit:
			doStartEdit();
			break;
		case R.id.mnudelete:
			doDelete();
			break;
		}
		return super.onContextItemSelected(item);
	}
	public void doStartNew()
	{
		Intent intentNew=new Intent(this, NewActivity.class);
		startActivityForResult(intentNew, MainActivity.NEW_EMPLOYEE);
	}
	public void doStartEdit()
	{
		Intent intentEdit=new Intent(this, EditActivity.class);
		Person p=list.get(posselected);
		Bundle bundle=new Bundle();
		bundle.putSerializable("per", p);
		intentEdit.putExtra("DATA", bundle);
		startActivityForResult(intentEdit, MainActivity.EDIT_EMPLOYEE);
	}
	public void doDelete()
	{
		AlertDialog.Builder builder=new Builder(MainActivity.this);
		builder.setTitle("Hỏi xóa");
		builder.setMessage("Muốn xóa thiệt hả?");
		builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				list.remove(posselected);
				adapter.notifyDataSetChanged();
			}
		});
		builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();}
		});
		builder.create().show();
	}
	protected void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode)
		{
		case MainActivity.NEW_EMPLOYEE:
			if(resultCode==MainActivity.SAVE_NEW_EMPLOYEE){
				Bundle bundle= data.getBundleExtra("DATA");
				Person p=(Person) bundle.getSerializable("per");
				list.add(p);
				adapter.notifyDataSetChanged();
			}
			break;
		case MainActivity.EDIT_EMPLOYEE:
			if(resultCode==MainActivity.SAVE_EDIT_EMPLOYEE){
				Bundle bundle= data.getBundleExtra("DATA");
				Person p=(Person) bundle.getSerializable("per");
				list.set(posselected, p);
				adapter.notifyDataSetChanged();
			}
			break;
		}
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}//end MainActivity
