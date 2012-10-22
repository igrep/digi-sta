package info.android.akihabara.cos.sample02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.util.Log;

public class StampManager implements Iterator<Bitmap>	{
	private static final String TAG = "test";
	public final static File SIGN_DIR = new File(
			Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +
			File.separator + "digista" );
	
	public StampManager () {
		this.initialize();
	}
	
	public boolean clearAllStamps () {
		if ( SIGN_DIR.exists() ){
			File [] stamps = SIGN_DIR.listFiles();
			for( int i = 0; i < stamps.length; ++i){
				stamps[i].delete();
			}
		}
		return SIGN_DIR.delete();
	}
	
	public boolean initialize () {
		if ( ! SIGN_DIR.exists() ){
			return SIGN_DIR.mkdir();
		} else {
			return false;
		}
	}

	public void addStamp (Bitmap stampBmp) {
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

	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	public Bitmap next() {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}