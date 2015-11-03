package tranduythanh.com;

import java.io.FileOutputStream;
import java.util.Calendar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	CameraSurfaceView cameraView = null;
	Button btn,btnadd,btnshare;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn=(Button) findViewById(R.id.btncapture);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				doCamera();
			}
		});
		btnadd=(Button) findViewById(R.id.btnaddview);
		btnadd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				cameraView=new CameraSurfaceView
						(getApplicationContext());
				LinearLayout ll=(LinearLayout) 
						findViewById(R.id.LinearLayout1);
				ll.addView(cameraView);
			}
		});
		btnshare=(Button) findViewById(R.id.btndoshare);
		btnshare.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//doShare();
				doWallpaper();
			}
		});
	}
	public void doCamera()
	{
		cameraView.capture(new Camera.PictureCallback() {
			public void onPictureTaken(byte[] data, Camera camera) {
				FileOutputStream fos;
				try {
					Calendar cal=Calendar.getInstance();
					String filename="hinhtm_"+cal.getTimeInMillis()+".jpg";
					String path=Environment
							.getExternalStorageDirectory()
							.getAbsolutePath()+"/"+filename;
					fos=new FileOutputStream(path);
					fos.write(data);
					fos.close();
					Toast.makeText(MainActivity.this, "OK\n"+path,
							Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void doShare()
	{
		cameraView.capture(new Camera.PictureCallback() {
			public void onPictureTaken(byte[] data, Camera camera) {
				try {
					Bitmap bm = BitmapFactory.decodeByteArray(
							data, 0, data.length);
					String fileUrl = MediaStore.Images.Media.
							insertImage(getContentResolver(), bm,
									"Camera Still Image",
									"Camera Pic Sample App Took");
					if (fileUrl == null) {
						return;
					} else {
						Uri picUri = Uri.parse(fileUrl);
						sendBroadcast(new Intent(
								Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
								picUri));
					}
				} catch (Exception e) {
					e.printStackTrace();}
			}
		});
	}
	public void doWallpaper()
	{
		cameraView.capture(new Camera.PictureCallback() {
			public void onPictureTaken(byte[] data, Camera camera) {
				Bitmap recordedImage =
						BitmapFactory.decodeByteArray
							(data, 0, data.length);
				try {
					WallpaperManager wpManager = WallpaperManager
							.getInstance(MainActivity.this);
					wpManager.setBitmap(recordedImage);
				} catch (Exception e) {
					Log.e("Still", "Setting wallpaper failed.", e);
				}
			}
		});
	}
}
