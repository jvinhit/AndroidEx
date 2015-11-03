package tranduythanh.com;

import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnstart,btnstop;
	MediaRecorder audioRecorder =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnstart=(Button) findViewById(R.id.btnstartrecording);
		btnstop=(Button) findViewById(R.id.btnstoprecording);
		btnstart.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				doStartRecording();
			}
		});
		btnstop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				doStopRecording();
			}
		});
	}
	public void doStartRecording()
	{
		try {if (audioRecorder == null) {
				audioRecorder = new MediaRecorder();//step 1
				}
			String saveto =Environment.
					getExternalStorageDirectory().
					getAbsolutePath()+"/myrecord.mp3";
			audioRecorder.setAudioSource(
					MediaRecorder.AudioSource.MIC);//step 2
			audioRecorder.setOutputFormat(
					MediaRecorder.OutputFormat.DEFAULT);//step 3
			audioRecorder.setAudioEncoder(
					MediaRecorder.AudioEncoder.DEFAULT);//step 4
			audioRecorder.setOutputFile(saveto);//step 5 
			audioRecorder.prepare();//step 6
			audioRecorder.start();//step 7
		} 
		catch (Exception e){e.printStackTrace();}
	}
	public void doStopRecording()
	{
		if (audioRecorder != null) {
			audioRecorder.stop();//step 8
			audioRecorder.release();
			audioRecorder = null;
			}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
