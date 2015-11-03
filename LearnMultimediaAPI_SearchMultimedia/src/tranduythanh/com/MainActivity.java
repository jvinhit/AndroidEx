package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

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
				doSearch();
			}
		});
	}
	public void doSearch()
	{
		Intent searchMusic = new Intent(
				android.provider.MediaStore
					.INTENT_ACTION_MEDIA_SEARCH);
		searchMusic.putExtra(android.provider.
						MediaStore.EXTRA_MEDIA_ARTIST,
							"Tuan Ngoc");
		searchMusic.putExtra(android.provider.
						MediaStore.EXTRA_MEDIA_TITLE,
							"Bai tinh ca cho em");
		searchMusic.putExtra(android.provider.
						MediaStore.EXTRA_MEDIA_FOCUS,
							"audio/*");
		startActivity(searchMusic);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
