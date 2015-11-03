package tranduythanh.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

	Button btncreatecache,btnreadcache;
	EditText editdata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btncreatecache=(Button) findViewById(R.id.btncreatecache);
		btnreadcache=(Button) findViewById(R.id.btnreadcache);
		editdata =(EditText) findViewById(R.id.editdata);
		btncreatecache.setOnClickListener(this);
		btnreadcache.setOnClickListener(this);
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
		if(v.getId()==R.id.btncreatecache)
		{
			createCache();
		}
		else if(v.getId()==R.id.btnreadcache)
		{
			readCache();
		}
	}
	public void loadAllCache()
	{
		File pathCacheDir = getCacheDir();
		File []listCache= pathCacheDir.listFiles();
		for(File f :listCache)
		{
			//process f here
			f.delete();
		}
	}
	public void readCache() {
		try {
			File pathCacheDir = getCacheDir();
			String strCacheFileName = "myCacheFile.cache";
			File newCacheFile = new 
					File(pathCacheDir, strCacheFileName);
			Scanner sc=new Scanner(newCacheFile);
			String data="";
			while(sc.hasNext())
			{
				data+=sc.nextLine()+"\n";
			}
			editdata.setText(data);
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void createCache()
	{
		try {
			File pathCacheDir = getCacheDir();
			String strCacheFileName = "myCacheFile.cache";
			String strFileContents = editdata.getText()+"";
			File newCacheFile = new 
					File(pathCacheDir, strCacheFileName);
			newCacheFile.createNewFile();
			FileOutputStream foCache =
					new FileOutputStream(
							newCacheFile.getAbsolutePath());
			foCache.write(strFileContents.getBytes());
			foCache.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
