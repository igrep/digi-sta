package info.android.akihabara.cos.sample02;

import java.io.File;

import android.os.Environment;

public class DigistaUtils {
	public final static File SIGN_DIR = new File(
			Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +
			File.separator + "digista" );
	
	public static File newSignFile (String filename) {
		if ( ! SIGN_DIR.exists() ){
			SIGN_DIR.mkdir();
		}
		return new File( SIGN_DIR, filename );
	}
}
