package info.android.akihabara.cos.sample02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.util.Log;

public class StampManager {
	private static final String TAG = "test";
	public final static File SIGN_DIR = new File(
			Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +
			File.separator + "digista" );
	
	public static void addStamp (Bitmap stampBmp) {
		if ( ! SIGN_DIR.exists() ){
			SIGN_DIR.mkdir();
		}
		String filename =  System.currentTimeMillis() + ".png";

		File file = new File( SIGN_DIR, filename );
		Log.d(TAG, "Creating" + filename);
		try {
			FileOutputStream out = new FileOutputStream(file);
			stampBmp.compress(CompressFormat.PNG, 100, out);
			out.close();
		} catch (IOException e) {
			Log.d(TAG, "Failed to write file " + filename + "with an exception" + e);
		}
		stampBmp.recycle();
	}

}