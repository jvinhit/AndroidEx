package tranduythanh.com;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.os.StrictMode;
import android.renderscript.Mesh.Primitive;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity 
		implements OnClickListener{
	EditText txtcateid,txtcatename;
	Button btninsertcate,btnlistcate;
	ListView lvlistcatalog;
	final String NAMESPACE="http://tranduythanh.com/";
	final String URL="http://192.168.3.102/ProductWebService/" +
			"ProductWebService.asmx?WSDL";
	ArrayList<String> arrCate=new ArrayList<String>();
	ArrayAdapter<String>adapter=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.ThreadPolicy policy = new 
				StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
		StrictMode.setThreadPolicy(policy); 

		txtcateid=(EditText) findViewById(R.id.editcateid);
		txtcatename=(EditText) findViewById(R.id.editcatename);
		btninsertcate=(Button) findViewById(R.id.btninsertcate);
		btnlistcate=(Button) findViewById(R.id.btnshowlist);
		btninsertcate.setOnClickListener(this);
		btnlistcate.setOnClickListener(this);
		lvlistcatalog=(ListView) findViewById(R.id.lvcatalog);

		adapter=new ArrayAdapter<String>
		(this, android.R.layout.simple_list_item_1, arrCate);
		lvlistcatalog.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v==btnlistcate)
		{
			showlistcate();
		}
		else if(v==btninsertcate)
		{
			insertcate1();
		}
	}
	public void insertcate1()
	{
		try
		{
			final String METHOD_NAME="insertCatalog";
			final String SOAP_ACTION=NAMESPACE+METHOD_NAME;
			
			SoapObject request=new SoapObject(NAMESPACE, METHOD_NAME);
			
			SoapObject newCate=new SoapObject(NAMESPACE, "cate");
			newCate.addProperty("CateId", txtcateid.getText()+"");
			newCate.addProperty("CateName", txtcatename.getText()+"");
			
			request.addSoapObject(newCate);
			
			SoapSerializationEnvelope envelope=
					new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet=true;
			envelope.setOutputSoapObject(request);
			
			HttpTransportSE androidHttpTransport=
					new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapPrimitive soapPrimitive= (SoapPrimitive) 
						envelope.getResponse();
			int ret=Integer.parseInt(soapPrimitive.toString());
			String msg="Insert Cate Successful";
			if(ret<=0)
				msg="Insert Cate Failed";
			Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		}
		catch(Exception e)
		{

		}
	}//end insert cate
	public void insertcate2()
	{

	}
	public void showlistcate()	{
		try
		{
			final String METHOD_NAME="getListCatalog";
			final String SOAP_ACTION=NAMESPACE+METHOD_NAME;
			SoapObject request=new SoapObject(NAMESPACE, METHOD_NAME);
			SoapSerializationEnvelope envelope=
					new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet=true;
			envelope.setOutputSoapObject(request);
			MarshalFloat marshal=new MarshalFloat();
			marshal.register(envelope);
			HttpTransportSE androidHttpTransport=
					new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION, envelope);
			//Get Array Catalog into soapArray
			SoapObject soapArray=(SoapObject) envelope.getResponse();
			arrCate.clear();
			//soapArray.getPropertyCount() return number of 
			//element in soapArray
			for(int i=0; i<soapArray.getPropertyCount(); i++)
			{
				//(SoapObject) soapArray.getProperty(i) get item at position i
				SoapObject soapItem =(SoapObject) soapArray.getProperty(i);
				//soapItem.getProperty("CateId") get value of CateId property
				String cateId=soapItem.getProperty("CateId").toString();
				String cateName=soapItem.getProperty("CateName").toString();
				arrCate.add(cateId+" - "+cateName);
			}
			arrCate.add(soapArray.getPropertyCount()+" -- cate");
			adapter.notifyDataSetChanged();
		}
		catch(Exception e)
		{

		}
	}
}
