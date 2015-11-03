package tranduythanh.com;

import java.util.List;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class CameraSurfaceView extends SurfaceView
		implements SurfaceHolder.Callback {
	private SurfaceHolder mHolder;
	private Camera camera = null;
	public CameraSurfaceView(Context context) {
		super(context);
		mHolder = getHolder();
		mHolder.addCallback(this);
	}
	public Size getBestFit(List<Camera.Size> sizes,
			int width, int height){
		int bestFit=0;
        int difference = Integer.MAX_VALUE;
        for(int i=0; i<sizes.size(); i++){
            Size s = sizes.get(i);
            int dif = (width-s.width)+(height-s.height);
            if(s.width<=width && s.height<=height 
            		&& dif>0 && dif < difference){
                bestFit = i;
                difference = dif;
            }
        }
        return sizes.get(bestFit);
	}
	public void surfaceChanged(SurfaceHolder holder,
			int format, int width, int height) {
		try
		{
			Parameters params=camera.getParameters();
			List<Camera.Size> sizes = params
					.getSupportedPreviewSizes();
			Camera.Size pickedSize = getBestFit(sizes,
										width, height);
			if (pickedSize != null) {
				params.setPreviewSize(pickedSize.width,
										pickedSize.height);
				params.setPictureFormat(ImageFormat.JPEG);
				camera.setParameters(params);
			}
			camera.startPreview();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			camera = Camera.open();
			camera.setPreviewDisplay(mHolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
		camera.release();
		camera = null;
	}
	public void makeZoom()
	{
		camera.startSmoothZoom(1);
		camera.startSmoothZoom(2);
		camera.startSmoothZoom(3);
		camera.stopSmoothZoom();
		Camera.Parameters para= camera.getParameters();
		boolean iszoom=para.isZoomSupported();
		Log.i("ZOOM-SP", "zoomsupported="+iszoom);
		int maxzoom=para.getMaxZoom();
		Log.i("ZOOM_MX", "max="+maxzoom);
		para.setZoom(2);
		para.setRotation(2);
		camera.setParameters(para);
	}
	public boolean capture(Camera.PictureCallback
			jpegHandler) {
		if (camera != null) {
			camera.takePicture(null, null, jpegHandler);
			return true;
		} else {
			return false;
		}
	}
}
