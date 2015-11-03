package tranduythanh.com;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn=(Button) findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				doShareAudio();
			}
		});
	}
	public void doShareAudio()
	{
		try{
			ContentValues values = new ContentValues(9);
			values.put(MediaStore.MediaColumns.TITLE, "RecordedAudio");
			values.put(MediaStore.Audio.Media.ALBUM,
					"Your Groundbreaking Album");
			values.put(MediaStore.Audio.Media.ARTIST, "Your Name");
			values.put(MediaStore.Audio.Media.DISPLAY_NAME,
					"The Audio File You Recorded In Media App");
			values.put(MediaStore.Audio.Media.IS_RINGTONE, 1);
			values.put(MediaStore.Audio.Media.IS_MUSIC, 1);
			values.put(MediaStore.MediaColumns.DATE_ADDED,
					System.currentTimeMillis() / 1000);
			values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
			String pathForAppFiles="Your the path file here";
			values.put(MediaStore.Audio.Media.DATA, pathForAppFiles);
			Uri audioUri = getContentResolver().insert(
					MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
			if (audioUri == null) {
				Log.d("Audio", "Content resolver failed");
				return;
			}
			sendBroadcast(new Intent(
					Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,audioUri));
		}
		catch(Exception e){
			e.printStackTrace();}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
