package tranduythanh.com;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
	Movie m;
	public static final String CONTENT_URI =
			"content://tranduythanh.com.own.contentprovider";
	public static final int MATCH_ALL = 1;
	public static final int MATCH_ID = 2;
	public static final String AUTHORITY = 
			"tranduythanh.com.own.contentprovider";
	private UriMatcher uriMatcher;
	public int delete(Uri arg0, String arg1, String[] arg2) {
		int ret=-1;
		switch(uriMatcher.match(arg0))
		{
			case MATCH_ALL:
				ret=m.deleteall();
				break;
			case MATCH_ID:
				ret=m.delete(arg0.getLastPathSegment());
				break;
		}
		return ret;
	}
	public String getType(Uri uri) {
		return "";
	}
	public Uri insert(Uri uri, ContentValues values) {
		m.createMovie(values);
		return null;
	}
	public boolean onCreate() {
		m = new Movie(this.getContext());
		// Setup the UriMatcher
		this.uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		this.uriMatcher.addURI(AUTHORITY, "objects", MATCH_ALL);
		this.uriMatcher.addURI(AUTHORITY, "object/#", MATCH_ID);
		return true;
	}
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		switch (uriMatcher.match(uri)) {
		case MATCH_ALL:
			cursor= m.fetchAllMovies();
			break;
		case MATCH_ID:
			cursor=m.fetchOneMovie(uri.getLastPathSegment());
			break;
		}
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int ret=-1;
		switch (uriMatcher.match(uri)) {
		case MATCH_ALL:
			ret=m.updateMovie(values, null, null);
			break;
		case MATCH_ID:
			ret=m.updateMovie(values, uri.getLastPathSegment());
			break;
		default:
			break;
		}
		return ret;
	}
}
