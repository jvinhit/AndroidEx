package tranduythanh.com;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btnplay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnplay=(Button) findViewById(R.id.button1);
		btnplay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				doPlayMusic();
			}
		});
	}
	public void doPlayMusic()
	{
		MediaPlayer  player = 
				new MediaPlayer ();//step 1
		try {
			String saveto =Environment.
					getExternalStorageDirectory().
					getAbsolutePath()+"/myrecord.mp3";
			player.setDataSource(saveto);//step 2
			player.prepare();//step 3
			player.start();//step 4
			
			player.stop();//step 5
			player.release();
			player=null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
