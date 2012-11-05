package info.android.akihabara.cos.sample02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.util.Log;

public class StampManager implements Iterable<Bitmap>	{
	private static final String TAG = "test";
	private final static File SIGN_DIR = new File(
			Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +
			File.separator + "digista" );
	
	public StampManager () {
		boolean result = this.initialize();
		if( result ){
			Log.d(TAG, "Successfully created " + SIGN_DIR);
		} else {
			Log.d(TAG, SIGN_DIR + " was not created.");
		}
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
			Log.d(TAG, SIGN_DIR + " does not exist. Trying to create.");
			return SIGN_DIR.mkdir();
		} else {
			Log.d(TAG, SIGN_DIR + " already exist. No need to create.");
			return false;
		}
	}

	public void addStamp (Bitmap stampBmp) {
		String filename =  System.currentTimeMillis() + ".png";

		File file = new File( SIGN_DIR, filename );
		Log.d(TAG, "Creating " + filename);
		try {
			FileOutputStream out = new FileOutputStream(file);
			stampBmp.compress(CompressFormat.PNG, 100, out);
			out.close();
		} catch (IOException e) {
			Log.d(TAG, "Failed to write file " + filename + "with an exception" + e);
		}
		stampBmp.recycle();
	}

	public StampsIterator iterator() {
		return new StampsIterator();
	}
	
	public static class StampsIterator implements Iterator<Bitmap> {
		private int index;
		private String[] signs;
		
		public StampsIterator(){
			index = 0;
			signs = SIGN_DIR.list();
		}

		public boolean hasNext() {
			return index < signs.length;
		}

		public Bitmap next() {
			Bitmap result = null;
			if ( hasNext() ){
				result = BitmapFactory.decodeFile(
						SIGN_DIR.getAbsolutePath() + File.separator + signs[index] );
				++index;
			}
			return result;
		}

		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}