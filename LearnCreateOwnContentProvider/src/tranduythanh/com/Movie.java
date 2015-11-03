package tranduythanh.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Movie {
	private DatabaseHelper dbHelper;
	private SQLiteDatabase database;
	public final static String MOVIE_TABLE="favorite_movie";
	public final static String MOVIE_NAME="name";
	public final static String MOVIE_DESC="description";
	public final static String MOVIE_ID="_id";
	public Movie(Context context){
		dbHelper = new DatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
	}
	public long createMovie(String name,String desc){
		ContentValues values = new ContentValues();
		values.put(MOVIE_NAME, name);
		values.put(MOVIE_DESC, desc);
		return database.insert(MOVIE_TABLE, null, values);
	}
	public void createMovie(ContentValues values){
		database.insert(MOVIE_TABLE, null, values);
	}
	public Cursor fetchAllMovies(){
		String[] cols = new String[] {MOVIE_NAME, MOVIE_DESC,MOVIE_ID};
		Cursor mCursor = database.query(true, MOVIE_TABLE,cols,null
				, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	public Cursor fetchOneMovie(String id){
		String[] cols = new String[] 
				{MOVIE_NAME, MOVIE_DESC,MOVIE_ID};
		Cursor c=database.query(MOVIE_TABLE, cols, 
				MOVIE_ID+"=?", new String[]{id},
				null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	public int delete(String id){
		return database.delete(MOVIE_TABLE, 
				MOVIE_ID + "=" + id, null);
	}
	public int deleteall()
	{
		return database.delete(MOVIE_TABLE, null, null);
	}
	public int updateMovie(ContentValues values,String id){
		return database.update(MOVIE_TABLE, values,
				MOVIE_ID + "=?",new String[]{id});
	}
	public int updateMovie(ContentValues values, 
			String selection,String[] selectionArgs)
	{
		return database.update(MOVIE_TABLE, values, 
				selection, selectionArgs);
	}
}
