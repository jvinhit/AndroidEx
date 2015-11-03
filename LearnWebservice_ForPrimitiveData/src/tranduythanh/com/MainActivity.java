package tranduythanh.com;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView txtcount;
	Button btncount;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.ThreadPolicy policy = new 
				StrictMode.ThreadPolicy.Builder()
							.permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		
		txtcount=(TextView) findViewById(R.id.txtcount);
		btncount=(Button) findViewById(R.id.btncount);
		btncount.setOnClickListener(new
				View.OnClickListener() {
			public void onClick(View arg0) {
				doCount();
			}
		});
	}
	public void doCount(){
		try{
			final String NAMESPACE="http://tranduythanh.com/";
			final String METHOD_NAME="getNumberOfCatalog";
			final String SOAP_ACTION=NAMESPACE+METHOD_NAME;
			final String URL="http://192.168.3.102/ProductWebService/" +
								"ProductWebService.asmx?WSDL";
			SoapObject request=new SoapObject(NAMESPACE, METHOD_NAME);
			SoapSerializationEnvelope envelope=
					new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet=true;
			envelope.setOutputSoapObject(request);
			
			HttpTransportSE androidHttpTransport=
					new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapPrimitive response=(SoapPrimitive) envelope.getResponse();
			txtcount.setText(response.toString());
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
	}
}//end MainActivity
