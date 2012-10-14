package info.android.akihabara.cos.sample02;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class StampHistoryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_history);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_stamp_history, menu);
        return true;
    }
}
