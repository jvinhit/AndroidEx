package tranduythanh.com;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity 
	implements OnClickListener, OnItemClickListener {
	ArrayList<String>smsList;
	ArrayAdapter<String>adapter;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn=(Button) findViewById(R.id.btnreadsms);
		btn.setOnClickListener(this);
		ListView smsListView = (ListView) findViewById( R.id.lvsms );
		smsList=new ArrayList<String>();
		adapter= new ArrayAdapter<String>
			( this, android.R.layout.simple_list_item_1, smsList);
	    smsListView.setAdapter(  adapter);
	    smsListView.setOnItemClickListener( this );
	}
	public void onItemClick(AdapterView<?> arg0, 
				View arg1, int arg2, long arg3) {
		try{String data=smsList.get(arg2);
			Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        } 
        catch (Exception e){
            e.printStackTrace();}
	}
	public void onClick(View v) {
		ContentResolver contentResolver = getContentResolver();
	    Cursor cursor = contentResolver.query(
	    			Uri.parse( MySmsReceiver.SMS_URI),
	    				null, null, null, null);
	    int indexBody = cursor.getColumnIndex( MySmsReceiver.BODY );
	    int indexAddr = cursor.getColumnIndex( MySmsReceiver.ADDRESS);
	    if ( indexBody < 0 || !cursor.moveToFirst() ) return;
	    smsList.clear();
	    do{
	        String str = "Sender: " + 
	        			  cursor.getString( indexAddr ) + "\n"+ 
	        			  cursor.getString( indexBody );
	        smsList.add( str );
	    }
	    while( cursor.moveToNext() );
	    adapter.notifyDataSetChanged();
	}
}
