package tranduythanh.com;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	Button btngetlist;
	ListView lvcatalog;
	final String URL="http://192.168.3.102/ProductWebService/" +
			"ProductWebService.asmx?WSDL";
	ArrayList<String> arrCate=new ArrayList<String>();
	ArrayAdapter<String>adapter=null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy = new 
				StrictMode.ThreadPolicy.Builder()
							.permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		lvcatalog=(ListView) findViewById(R.id.lvcatalog);
		btngetlist=(Button) findViewById(R.id.btnlistcatalog);
		btngetlist.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0)
				{doGetList();}
		});
		adapter=new ArrayAdapter<String>
			(this, android.R.layout.simple_list_item_1, arrCate);
		lvcatalog.setAdapter(adapter);
	}
	public void doGetList()	{
		try{final String NAMESPACE="http://tranduythanh.com/";
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
			adapter.notifyDataSetChanged();
		}
		catch(Exception e){}}
}
