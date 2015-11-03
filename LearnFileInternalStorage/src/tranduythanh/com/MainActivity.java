package tranduythanh.com;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.util.PrintWriterPrinter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	Button btnread,btnwrite;
	EditText editdata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnread=(Button) findViewById(R.id.btnreaddata);
		btnwrite=(Button) findViewById(R.id.btnwritedata);
		editdata=(EditText) findViewById(R.id.editdata);
		btnread.setOnClickListener(this);
		btnwrite.setOnClickListener(this);
	}
	public void onClick(View v) {
		if(v.getId()==R.id.btnreaddata)
		{
			readData();
		}
		else if(v.getId()==R.id.btnwritedata)
		{
			writeData();
		}
	}
	public void readData()
	{
		try {
			FileInputStream in= openFileInput("myfile.txt");
			BufferedReader reader=new 
					BufferedReader(new InputStreamReader(in));
			String data="";
			StringBuilder builder=new StringBuilder();
			while((data=reader.readLine())!=null)
			{
				builder.append(data);
				builder.append("\n");
			}
			in.close();
			editdata.setText(builder.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeData()
	{
		try {
			FileOutputStream out=
					openFileOutput("myfile.txt",0);
			OutputStreamWriter writer=
					new OutputStreamWriter(out);
			writer.write(editdata.getText().toString());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void readData2()
	{
		String data;
		InputStream in= getResources()
				.openRawResource(R.drawable.myfile);
		InputStreamReader inreader=new InputStreamReader(in);
		BufferedReader bufreader=new BufferedReader(inreader);
		StringBuilder builder=new StringBuilder();
		if(in!=null)
		{
			try{
				while((data=bufreader.readLine())!=null)
				{
					builder.append(data);
					builder.append("\n");
				}
				in.close();
				editdata.setText(builder.toString());
			}
			catch(IOException ex){
				Log.e("ERROR", ex.getMessage());
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


}
