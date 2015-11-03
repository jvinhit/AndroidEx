package tranduythanh.com;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener{

	BroadcastReceiver mybroadcast;
	ArrayList<String>smsList;
	ArrayAdapter<String>adapter;
	TextView lblmsg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn=(Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);
		
		ListView smsListView = (ListView) findViewById( R.id.listView1 );
		smsList=new ArrayList<String>();
		adapter= new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, smsList);
	    smsListView.setAdapter(  adapter);
	    smsListView.setOnItemClickListener( this );
	    lblmsg=(TextView) findViewById(R.id.lblmsg);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ContentResolver contentResolver = getContentResolver();
	    Cursor cursor = contentResolver.query( Uri.parse( "content://sms/inbox" ), null, null, null, null);
	    int indexBody = cursor.getColumnIndex( MyBroadCast.BODY );
	    int indexAddr = cursor.getColumnIndex( MyBroadCast.ADDRESS);
	     
	    if ( indexBody < 0 || !cursor.moveToFirst() ) return;
	     
	    smsList.clear();
	     
	    do
	    {
	        String str = "Sender: " + cursor.getString( indexAddr ) + "\n" + cursor.getString( indexBody );
	        smsList.add( str );
	    }
	    while( cursor.moveToNext() );
	         
	    adapter.notifyDataSetChanged();
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id ) {
		// TODO Auto-generated method stub
		 try
	        {
	            String sender = smsList.get( pos ).split("\n")[0];
	            String encryptedData = smsList.get( pos ).split("\n")[1];
	            String data = sender + "\n" + StringCryptor.decrypt( new String(MyBroadCast.PASSWORD), encryptedData );
	            lblmsg.setText(data);
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	}

}
