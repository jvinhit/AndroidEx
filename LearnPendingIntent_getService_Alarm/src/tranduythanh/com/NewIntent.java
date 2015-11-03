package tranduythanh.com;

import android.content.Context;
import android.content.Intent;

public class NewIntent extends Intent {
	public Context glbCtx;
	public NewIntent(Context ctx, Class<?>cls)
	{
		super(ctx, cls);
		glbCtx=ctx;
	}
}
