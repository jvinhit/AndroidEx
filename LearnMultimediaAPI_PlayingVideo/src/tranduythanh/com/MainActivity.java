package tranduythanh.com;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	Button btnplay;
	VideoView videoview;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnplay=(Button) findViewById(R.id.btnplayvideo);
		videoview=(VideoView) findViewById(R.id.videoView1);
		btnplay.setOnClickListener(new 
				View.OnClickListener() {
			public void onClick(View arg0) {
				doPlayingVideo();
			}
		});
	}
	public void doPlayingVideo()
	{
		String path=Environment
				.getExternalStorageDirectory()
				.getAbsolutePath()+"/myfile.mp4";
		MediaController mc = new MediaController(this);
		Uri video = Uri.parse(path);
		videoview.setMediaController(mc);
		videoview.setVideoURI(video);
		videoview.start();
	}
}
