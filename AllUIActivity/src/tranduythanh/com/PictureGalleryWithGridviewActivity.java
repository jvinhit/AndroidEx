package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PictureGalleryWithGridviewActivity 
       extends Activity implements OnItemClickListener
{
	TextView tvMsg;
	GridView gv;
	TextView tvSoloMsg;
	Integer[]mThumbIds;
	ImageView ivSoloPicture;
	Button btnBack;
	Bundle myBackupBundle;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myBackupBundle=savedInstanceState;
		setContentView(R.layout.activity_picture_gallery_with_gridview);
		tvMsg=(TextView) findViewById(R.id.tvMsg);
		mThumbIds=new Integer[]{R.drawable.image1,R.drawable.image2,
			   R.drawable.image3,R.drawable.image4,
			   R.drawable.image5,R.drawable.image6,
			   R.drawable.ic_launcher,R.drawable.lifecycle};
		gv=(GridView) findViewById(R.id.gridView1);
		gv.setAdapter(new MyImageAdapter(this));
		gv.setOnItemClickListener(this);
	}

	
	public void onItemClick(AdapterView<?> arg0,
			View arg1, int arg2, long arg3) {
		showdetail(arg2);
	}
	public void showdetail(int posistion)
	{
		setContentView(R.layout.solo_picture);
		tvSoloMsg=(TextView) findViewById(R.id.tvSoloMsg);
		tvSoloMsg.setText("Image at "+posistion);
		ivSoloPicture=(ImageView) findViewById(R.id.imgSolo);
		ivSoloPicture.setImageResource(mThumbIds[posistion]);
		btnBack=(Button) findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				onCreate(myBackupBundle);
			}
		});
	}
	public class MyImageAdapter extends BaseAdapter {
		private Context mContext;
		public MyImageAdapter(Context c){
			mContext=c;
		}
		public int getCount() {return mThumbIds.length;	}
		public Object getItem(int arg0) {return null;}
		public long getItemId(int arg0) {return 0;}
		public View getView(int arg0, View convertView, ViewGroup arg2) {
			ImageView imgView;
			if(convertView==null){
				imgView=new ImageView(mContext);
				imgView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imgView.setPadding(8, 8, 8, 8);
			}else{
				imgView=(ImageView) convertView;
			}
			imgView.setImageResource(mThumbIds[arg0]);
			return imgView;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
				R.menu.activity_picture_gallery_with_gridview, menu);
		return true;
	}
}
