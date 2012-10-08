package info.android.akihabara.cos.sample02;


/* このファイルは
 *   日本Androidの会 秋葉原支部 コスプレ理系女子普及部
 *   Android アプリ開発　勉強会 教材
 *   Programmed by Kazuyuki Eguchi 2012
 * を元に作りました。
 * オリジナルのソースコードは
 * http://yoriko.mydns.jp/Sample02.zip
 * にあります。
 */

import java.util.ArrayList;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SignActivity extends Activity {
  // 定数の定義
  private String TAG = "test";

  private Uri mImageUri = null;

  private final int MENU_ID_PREF = 1;
  private final int MENU_ID_DONE = 2;
  private final int MENU_ID_CLEAR = 3;

  private final Object prefActivity = StampCardPref.class;

  // 変数の定義
  private DView view = null;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    Log.d(TAG,"initializing view");
    this.setContentView(R.layout.main);
    DView view = (DView) findViewById(R.id.sign);
  }

  @Override
  protected void onDestroy()
  {
    super.onDestroy();
  }

  @Override
  protected void onPause()
  {
    super.onPause();
  }

  @Override
  protected void onResume()
  {
    super.onResume();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // メニューを呼び出された時（初回のみ）
    menu.add(Menu.NONE, MENU_ID_PREF, Menu.NONE, "ご主人様の情報の登録");
    menu.add(Menu.NONE, MENU_ID_DONE, Menu.NONE, "サインが描けました、ご主人様！");
    menu.add(Menu.NONE, MENU_ID_CLEAR, Menu.NONE, "書き直す");
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu)
  {
    // メニューが呼び出される度に呼ばれる
    return super.onPrepareOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    // メニューの内容を選択した時
    Log.d(TAG,"onOptionsItemSelected");

    switch(item.getItemId())
    {
      case MENU_ID_PREF:
        startPref();
        return true;

      default:
        break;
    }

    return super.onOptionsItemSelected(item);
  }

  public void startPref(){
    Intent intent = new Intent( this, (Class<?>)prefActivity );
    startActivity(intent);
  }

}
