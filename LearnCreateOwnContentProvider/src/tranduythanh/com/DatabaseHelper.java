package tranduythanh.com;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "movieDB";
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_CREATE = 
			"create table favorite_movie " +
			"( _id integer primary key," +
			"name text not null, " +
			"description text not null);";
	public DatabaseHelper(Context context, String name,
			CursorFactory factory,int version) {
		super(context, name, factory, version);
	}
	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL(DATABASE_CREATE);
	}
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		arg0.execSQL("DROP TABLE IF EXISTS favorite_movie");
		onCreate(arg0);
	}
}
