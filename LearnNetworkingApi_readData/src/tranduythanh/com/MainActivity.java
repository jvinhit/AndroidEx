package tranduythanh.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnread;
	EditText txtData;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnread=(Button) findViewById(R.id.button1);
		txtData=(EditText) findViewById(R.id.editText1);
		btnread.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				txtData.setText("");
				MyAsyncTask task=new MyAsyncTask();
				task.execute("http://goo.gl/KKDx1");
			}
		});
	}
	
	private class MyAsyncTask extends 
				AsyncTask<String, String, Void>	{
		public void doRead(String s){
			try {
				URL url=new URL(s);
				HttpURLConnection http =
				(HttpURLConnection)url.openConnection();
				Log.i("Net", "length = " + http.getContentLength());
				Log.i("Net", "respCode = " + http.getResponseCode());
				Log.i("Net", "contentType = "+ http.getContentType());
				Log.i("Net", "content = " + http.getContent());
				
				InputStream input=url.openStream();
				BufferedReader reader=new BufferedReader(
						new InputStreamReader(input));
				String line="";
				while((line=reader.readLine())!=null){
					publishProgress(line);
				}
				input.close();
			} 
			catch(Exception ex){
				ex.printStackTrace();}
		}
		protected Void doInBackground(String... params) {
			doRead(params[0]);
			return null;}
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
			txtData.append(values[0]+"\n");
		}
	}
}
