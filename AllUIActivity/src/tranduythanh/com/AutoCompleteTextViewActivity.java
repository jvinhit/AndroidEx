package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class AutoCompleteTextViewActivity extends Activity
					implements TextWatcher {
	TextView selection;
	AutoCompleteTextView viewcomplete;
	String arr[]={"hà nội","Huế","Sài gòn",
				  "hà giang","Hội an","Kiên giang",
				  "Lâm đồng","Long khánh"};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auto_complete_text_view);
		selection =(TextView) findViewById(R.id.selection);
		viewcomplete=(AutoCompleteTextView) findViewById(R.id.editauto);
		viewcomplete.addTextChangedListener(this);
		viewcomplete.setAdapter(new ArrayAdapter<String>(this, 
					 android.R.layout.simple_list_item_1, 
					 arr));
		MultiAutoCompleteTextView mul=(MultiAutoCompleteTextView) 
				findViewById(R.id.multiAutoCompleteTextView1);
		mul.setAdapter(new ArrayAdapter<String>(this, 
					 android.R.layout.simple_list_item_1, 
					 arr));
		mul.setTokenizer(new MultiAutoCompleteTextView
				.CommaTokenizer());
	}
	public void onTextChanged(CharSequence arg0, int arg1,
			int arg2, int arg3) {
		selection.setText(viewcomplete.getText());
	}
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.activity_auto_complete_text_view, menu);
		return true;
	}
}
