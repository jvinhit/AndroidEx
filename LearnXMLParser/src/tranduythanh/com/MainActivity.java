package tranduythanh.com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

	Button btndom;
	EditText editdata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btndom=(Button) findViewById(R.id.btndomparser);
		btndom.setOnClickListener(this);
		editdata=(EditText) findViewById(R.id.editdata);
	}
	public void domParser()
	{
		try {
			DocumentBuilderFactory fac=
					DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=
					fac.newDocumentBuilder();
			String sdcard=Environment.
					getExternalStorageDirectory().
					getAbsolutePath();
			String xmlfile=sdcard+"/employee.xml";
			FileInputStream fIn=new 
					FileInputStream(xmlfile);
			Document doc=builder.parse(fIn);
			Element root= doc.getDocumentElement();
			NodeList list= root.getChildNodes();
			String datashow="";
			for(int i=0;i<list.getLength();i++)
			{
				Node node=list.item(i);
				if(node instanceof Element)
				{
					Element employee=(Element) node;
					String id=employee.getAttribute("id");
					String title=employee.getAttribute("title");
					NodeList listChild= employee
								.getElementsByTagName("name");
					String name=listChild.item(0).getTextContent();
					listChild=employee.getElementsByTagName("phone");
					String phone=listChild.item(0).getTextContent();
					
					datashow+=id+"-"+title+"-"+name+"-"+phone
							+"\n---------\n";
				}
			}
			editdata.setText(datashow);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saxparser()
	{
		try {
			XmlPullParserFactory fc=XmlPullParserFactory
					.newInstance();
			XmlPullParser parser= fc.newPullParser();

			String sdcard=Environment.
					getExternalStorageDirectory().
					getAbsolutePath();
			String xmlfile=sdcard+"/employee.xml";
			FileInputStream fIn=new 
					FileInputStream(xmlfile);

			parser.setInput(fIn, "UTF-8");
			
			int eventType=-1;
			String nodeName;
			String datashow="";
			while(eventType!=XmlPullParser.END_DOCUMENT)
			{
				eventType=parser.next();
				switch(eventType)
				{
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					nodeName=parser.getName();
					if(nodeName.equals("employee")){
						datashow+=parser.getAttributeValue(0)+"-";
						datashow+=parser.getAttributeValue(1)+"-";
					}
					else if(nodeName.equals("name")){
						datashow+=parser.nextText()+"-";
					}
					else if(nodeName.equals("phone")){
						datashow+=parser.nextText()+"-";
					}
					break;
				case XmlPullParser.END_TAG:
					nodeName=parser.getName();
					if(nodeName.equals("employee")){
						datashow+="\n----------------\n";
					}
					break;
				}
			}
			editdata.setText(datashow);

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		if(v.getId()==R.id.btndomparser)
		{
			domParser();
			//saxparser();
		}
	}

}
