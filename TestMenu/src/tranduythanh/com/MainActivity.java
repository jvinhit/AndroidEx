package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.Spannable.Factory;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btn1,btn2;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1=(Button) findViewById(R.id.btnctmnu1);
		btn2=(Button) findViewById(R.id.btnctmnu2);
		registerForContextMenu(btn1);
		registerForContextMenu(btn2);
	}
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		if(v.getId()==R.id.btnctmnu1)
		{
			getMenuInflater().inflate
				(R.menu.contextmenu1, menu);
		}
		else if(v.getId()==R.id.btnctmnu2)
		{
			contextmenu2(menu);
		}
	}
	public void contextmenu2(Menu menu)
	{
		//4 arguments:groupId, itemId, order, title
		int groupId=1;
		menu.add(groupId, 113, 0, "Red");
		menu.add(groupId, 114, 1, "Green");
		menu.add(groupId, 115, 2, "Blue");
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Typeface type=Typeface.createFromAsset
						(getAssets(), "ARIAL.TTF");
		switch(item.getItemId())
		{
		case R.id.mnubold:
			btn1.setTypeface(type,Typeface.BOLD);
			break;
		case R.id.mnuItalic:
			btn1.setTypeface(type, Typeface.ITALIC);
			break;
		case R.id.mnuunderline:
			//research from Internet
			break;
		case 113:
			btn2.setBackgroundColor(
					getResources().
						getColor(android.R.color.holo_red_dark));
			break;
		case 114:
			btn2.setBackgroundColor(
					getResources().
					getColor(android.R.color.holo_green_dark));
			break;
		case 115:
			btn2.setBackgroundColor(
					getResources().
					getColor(android.R.color.holo_blue_dark));
			break;
		}
		return super.onContextItemSelected(item);
	}
	@Override
	public boolean onCreateOptionsMenu
		(Menu menu) {
		getMenuInflater()
		.inflate(R.menu.activity_main,
				menu);
		return true;
	}
	public boolean onOptionsItemSelected
		(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.mnuxemdssv:
			//process here
			Toast.makeText(this, 
					"Xem ds Sinh vien",
					Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.mnuxoads:
			//process here
			break;
		}
		return true;
	}
	
	
}
