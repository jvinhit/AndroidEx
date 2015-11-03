package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	Button btncreatedatabase,
			btndeletedatabase,btncreatetable,
			btninsertrecordtolop,btnupdaterowtolop,
			btnquerytbllop;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btncreatedatabase=(Button) findViewById(R.id.btncreatedatabase);
		btncreatedatabase.setOnClickListener(this);
		btndeletedatabase=(Button) findViewById(R.id.btndeletedatabase);
		btndeletedatabase.setOnClickListener(this);
		btncreatetable=(Button) findViewById(R.id.btncreatetable);
		btncreatetable.setOnClickListener(this);
		btninsertrecordtolop=(Button) findViewById(R.id.btnInsertRowToTblLop);
		btninsertrecordtolop.setOnClickListener(this);
		btnupdaterowtolop=(Button) findViewById(R.id.btnupdaterowtotbllop);
		btnupdaterowtolop.setOnClickListener(this);
		btnquerytbllop=(Button) findViewById(R.id.btnquerylop);
		btnquerytbllop.setOnClickListener(this);
		doCreateDb();
	}
	SQLiteDatabase database=null;
	public void doCreateDb()
	{
		database=openOrCreateDatabase(
				"qlsinhvien.db",
				MODE_PRIVATE,
				null);
//		hoac
		// Cai nay la neu chua co thi create con neu co roi thi thoi
		// database =SQLiteDatabase.openDatabase("qlsinhvien.db", null, SQLiteDatabase.CREATE_IF_NECESSARY);
	}
	
	// Để delete database ta chỉ còn gọi method :
	//deleteDatabase(tên CSDL).
	//Nếu xóa thành công thì trả về true, xóa thất bại trả về false;
	public void doDeleteDb()
	{
		String msg="";
		if(deleteDatabase("qlsinhvien.db")==true)
		{
			msg="Delete database [qlsinhvien.db] is successful";
		}
		else
		{
			msg="Delete database [qlsinhvien.db] is failed";
		}
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	public void doCreateTable()
	{
		doCreateDb();
		doCreateLopTable();
		doCreateSinhvienTable();
	}
	public void doCreateLopTable()
	{
		String sql="CREATE TABLE tbllop (";
			 sql+="malop TEXT primary key,";
			 sql+="tenlop TEXT,";
			 sql+="siso INTEGER)";
		database.execSQL(sql);	 
	}
	public void doCreateSinhvienTable()
	{
		String sql="CREATE TABLE tblsinhvien ("+
				"masv TEXT PRIMARY KEY ,"+
				"tensv TEXT,"+
				"malop TEXT NOT NULL CONSTRAINT malop "+
				" REFERENCES tbllop(malop) ON DELETE CASCADE)";
		database.execSQL(sql);
	}
	public void doDeleteRecordTable()
	{
		database.delete("tbllop", null, null);
		String malop="DHTH7C";
		database.delete("tbllop", 
				"malop=?", 
				new String[]{malop});
	}
	public void loadalllop()
	{
		Cursor c=database.query("tbllop",
				null, null, null, null, null, null);
		c.moveToFirst();
		String data="";
		while(c.isAfterLast()==false)
		{
			data+=c.getString(0)+"-"+
				  c.getString(1)+"-"+
				  c.getString(2);
			data+="\n";
			c.moveToNext();
		}
		Toast.makeText(this, data, Toast.LENGTH_LONG).show();
		c.close();
	}
	public void doInsertRecord()
	{
		// ContentValues để đưa dữ liệu vào bảng.
		// Đối tượng này có các phương thức put (tên cột , dữ liệu)
//		 Sau đó gọi phương thức insert để đưa đối tượng (dòng này) vào bảng.
		ContentValues values=new ContentValues();
		values.put("malop", "DHTH7A");
		values.put("tenlop", "Dai hoc tin hoc 7a");
		values.put("siso", 20);
		String msg="";
		if(database.insert("tbllop", null, values)==-1){
			msg="Failed to insert record";
		}
		else{
			msg="insert record is successful";
		}
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	public void updateLopName(String malop,String new_tenlop)
	{
		ContentValues values=new ContentValues();
		values.put("tenlop", new_tenlop);
		String msg="";
		int ret=database.update("tbllop", values,
				"malop=?", new String[]{malop});
		if(ret==0){
			msg="Failed to update";
		}
		else{
			msg="updating is successful";
		}
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public void onClick(View arg0) {
		if(arg0==btncreatedatabase)
		{
			doCreateDb();
		}
		else if(arg0==btndeletedatabase)
		{
			doDeleteDb();
		}
		else if(arg0==btncreatetable)
		{
			doCreateTable();
		}
		else if(arg0==btninsertrecordtolop)
		{
			doInsertRecord();
		}
		else if(arg0==btnupdaterowtolop)
		{
			updateLopName("DHTH7C", "DAI HOC XYZ");
		}
		else if(arg0==btnquerytbllop)
		{
			loadalllop();
		}
	}

}
