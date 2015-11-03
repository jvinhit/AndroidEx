package tranduythanh.com;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText txtsoluong,txtdongia,txtthanhtien;
	Button btntinhtien;
	final String URL="http://192.168.3.102/ProductWebService/" +
			"ProductWebService.asmx?WSDL";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.ThreadPolicy policy = new 
				StrictMode.ThreadPolicy.Builder()
							.permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		
		txtsoluong=(EditText) findViewById(R.id.editsoluong);
		txtdongia=(EditText) findViewById(R.id.editdongia);
		txtthanhtien=(EditText) findViewById(R.id.editthanhtien);
		btntinhtien=(Button) findViewById(R.id.btntinhtien);
		btntinhtien.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				doTinhTien();
			}
		});
	}
	public void doTinhTien(){
		try{
			final String NAMESPACE="http://tranduythanh.com/";
			final String METHOD_NAME="getMoney";
			final String SOAP_ACTION=NAMESPACE+METHOD_NAME;
			SoapObject request=new SoapObject(NAMESPACE, METHOD_NAME);
			int sl=Integer.parseInt(txtsoluong.getText()+"");
			double dg=Double.parseDouble(txtdongia.getText()+"");
			request.addProperty("unitPrice", dg);
			request.addProperty("quantity", sl);
			SoapSerializationEnvelope envelope=
					new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet=true;
			envelope.setOutputSoapObject(request);
			MarshalFloat marshal=new MarshalFloat();
			marshal.register(envelope);
			HttpTransportSE androidHttpTransport=
					new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapPrimitive response=(SoapPrimitive) envelope.getResponse();
			txtthanhtien.setText(response.toString());
		}
		catch(Exception e)	{}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
