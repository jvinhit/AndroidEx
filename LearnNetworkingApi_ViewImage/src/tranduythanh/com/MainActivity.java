package tranduythanh.com;

import java.io.InputStream;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView imgview;
	Button btn;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imgview=(ImageView) findViewById(R.id.imageView1);
		btn=(Button) findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new MyImgTask()
					.execute("http://hinh.com/hinh1.jpg");
			}
		});
	}
	private class MyImgTask extends
			AsyncTask<String, Void, Bitmap>
	{
		protected Bitmap doInBackground(String... params) {
			Bitmap b=null;
			try {
				InputStream bmis;
				bmis = new URL(params[0]).openStream();
				b= BitmapFactory.decodeStream(bmis);
			} catch (Exception e){
				e.printStackTrace();
			}
			return b;
		}
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			imgview.setImageBitmap(result);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
