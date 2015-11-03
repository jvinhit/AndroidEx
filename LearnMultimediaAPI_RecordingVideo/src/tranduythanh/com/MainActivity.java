package tranduythanh.com;

import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnrecording,btnstop;
	SurfaceView surfaceview;
	MediaRecorder videoRecorder=null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnrecording=(Button) findViewById(R.id.btnrecording);
		surfaceview=(SurfaceView) findViewById(R.id.surfaceView1);
		btnrecording.setOnClickListener(new 
				View.OnClickListener() {
			public void onClick(View arg0) {
				doRecordingVideo();
			}
		});
		btnstop=(Button) findViewById(R.id.btnstop);
		btnstop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				doStopRecordingVideo();
			}
		});
	}
	public void doRecordingVideo()
	{	try {if (videoRecorder == null) {
				videoRecorder = new MediaRecorder();//step 1
				}
			String saveto =Environment.getExternalStorageDirectory()
					.getAbsolutePath()+"/myfile.mp4";
			videoRecorder.setVideoSource(
					MediaRecorder.VideoSource.CAMERA);//step 2
			videoRecorder.setOutputFormat(
					MediaRecorder.OutputFormat.MPEG_4 );//step 3
			videoRecorder.setVideoSize(640, 480);//step 4
			videoRecorder.setVideoFrameRate(30);//step 5
			videoRecorder.setVideoEncoder(
					MediaRecorder.VideoEncoder.H264);//step 6
			videoRecorder.setOutputFile(saveto);//step 7
			videoRecorder.setPreviewDisplay(
					surfaceview.getHolder().getSurface());//step 8
			videoRecorder.prepare();//step 9
			videoRecorder.start();//step 10
		} 
		catch(Exception e){
			ShowMessage(e);
		}
	}
	public void ShowMessage(Exception e)
	{
		Toast.makeText(this, e.toString(),
				Toast.LENGTH_LONG).show();
	}
	public void doStopRecordingVideo()
	{
		if (videoRecorder!= null) {
			videoRecorder.stop();//step 11
			videoRecorder.release();
			videoRecorder = null;
			//Using Intent to start this video when recording 
			//is completed
			Intent intent=new Intent(Intent.ACTION_VIEW);
			String path=Environment
					.getExternalStorageDirectory()
					.getAbsolutePath()+"/myfile.mp4";
			intent.setDataAndType(Uri.parse(path), "video/*");
			startActivity(intent);
		}
	}
}
