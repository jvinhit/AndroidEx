package tranduythanh.com;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
			implements OnClickListener{
	TextView lbl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn1=(Button)findViewById(R.id.btndelete);
		btn1.setOnClickListener(this);
		Button btn2=(Button)findViewById(R.id.btninsert);
		btn2.setOnClickListener(this);
		Button btn3=(Button)findViewById(R.id.display);
		btn3.setOnClickListener(this);
		Button btn4=(Button)findViewById(R.id.Update);
		btn4.setOnClickListener(this);
		Button btn5=(Button)findViewById(R.id.displayOne);
		btn5.setOnClickListener(this);
		lbl=(TextView)findViewById(R.id.lbl);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btndelete:
			deleteAll();
			break;
		case R.id.btninsert:
			InsertData();
			break;
		case R.id.display:
			getData();

			break;
		case R.id.displayOne:
			getDataOne();

			break;
		case R.id.Update:
			update();
			break;
		}
	}
	void deleteAll()
	{
		Uri uri=Uri.parse(MyContentProvider.CONTENT_URI+"/objects");
		int ret=getContentResolver().delete(uri, null, null);
		String msg="Delete all are successful";
		if(ret<=0)
			msg="Delete all are failed";
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		
	}
	void update()
	{
		Uri uri = Uri.parse(MyContentProvider.CONTENT_URI+"/object/2");
		ContentResolver resolver = this.getContentResolver();

		ContentValues newContent = new ContentValues();

		newContent.put(Movie.MOVIE_NAME, "namename_change");
		newContent.put(Movie.MOVIE_DESC,"Description_change");

		resolver.update(uri, newContent, null, null);
	}
	void InsertData(){
		Uri uri = Uri.parse(MyContentProvider.CONTENT_URI+"/object");
		ContentResolver resolver = getContentResolver();
		ContentValues newContent = new ContentValues();
		newContent.put(Movie.MOVIE_NAME, "namename");
		newContent.put(Movie.MOVIE_DESC,"Description");
		resolver.insert(uri, newContent);
	}
	void getData(){
		Uri uri = Uri.parse(MyContentProvider.CONTENT_URI+"/objects");
		ContentResolver resolver = this.getContentResolver();
		Cursor cursor = resolver.query(uri, null, null, null, null);
		int nameIndex = cursor.getColumnIndex(Movie.MOVIE_NAME);
		int valueIndex = cursor.getColumnIndex(Movie.MOVIE_DESC);
		String str="";
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			String name = cursor.getString(nameIndex);
			String value = cursor.getString(valueIndex);
			str += name + " " + value + "\n";
			cursor.moveToNext();
		}
		lbl.setText(str);
	}
	void getDataOne()
	{
		Uri uri = Uri.parse(MyContentProvider.CONTENT_URI+"/object/2");
		ContentResolver resolver = this.getContentResolver();

		Cursor cursor = resolver.query(uri, null, null, null, null);
		if(cursor.getCount() == 0)
		{
			return;
		}
		int nameIndex = cursor.getColumnIndex(Movie.MOVIE_NAME);
		int valueIndex = cursor.getColumnIndex(Movie.MOVIE_DESC);
		String str="";
		cursor.moveToFirst();
		do
		{
			String name = cursor.getString(nameIndex);
			String value = cursor.getString(valueIndex);
			str += name + " " + value + "\n";
			cursor.moveToNext();
		}while(!cursor.isAfterLast());
		lbl.setText(str);
	}
}
