package info.android.akihabara.cos.sample02;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class StampCardPref extends PreferenceActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.pref);
  }
}
