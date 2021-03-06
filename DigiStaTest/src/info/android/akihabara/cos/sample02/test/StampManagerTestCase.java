package info.android.akihabara.cos.sample02.test;

import java.io.File;
import java.lang.reflect.Field;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import info.android.akihabara.cos.sample02.StampManager;
import junit.framework.TestCase;

public class StampManagerTestCase extends TestCase {
	private StampManager stampManager;
	private File signDir;
	private static TestUtility.ObjectExposer<StampManager> objectExposer =
				new TestUtility.ObjectExposer<StampManager>(StampManager.class);

	public StampManagerTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		stampManager = new StampManager();
		Field signDirField = objectExposer.exposeField("SIGN_DIR");
		signDir = (File) signDirField.get(stampManager);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		stampManager.clearAllStamps();
	}

	public void testInitialize() throws IllegalArgumentException, IllegalAccessException {
		boolean result;
		
		stampManager.clearAllStamps();
		result = stampManager.initialize();
		assertTrue("すべてのスタンプを消した後、initializeは成功する。", result);
		assertTrue("SIGN_DIRは存在する。", signDir.exists());
		assertEquals("SIGN_DIRは空。", 0, signDir.list().length);
		
		result = stampManager.initialize();
		assertFalse("スタンプを一度初期化した後、initialzeは失敗する。", result);
	}
	
	public void testClearAllStamps() {
		boolean result;
		
		stampManager.initialize();
		result = stampManager.clearAllStamps();
		assertTrue("スタンプを初期化した後、clearAllStampsは成功する。", result);
		assertFalse("SIGN_DIRは存在しない。", signDir.exists());
	
		result = stampManager.clearAllStamps();
		assertFalse("一旦スタンプをクリアした後、clearAllStampsは失敗する。", result);
		assertFalse("SIGN_DIRは存在しない。", signDir.exists());
	}
	
	public void testAddStamp() {
		Bitmap bmpExpected1 = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
		Bitmap bmpExpected2 = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
		Bitmap bmpActual1;
		Bitmap bmpActual2;
		String[] bmpPaths;
		
		stampManager.addStamp(bmpExpected1);
		bmpPaths = signDir.list();
		bmpActual1 = BitmapFactory.decodeFile( getPathInDir( signDir, bmpPaths[0]) );
		assertEquals("スタンプを1つ追加した後", bmpExpected1.getHeight(), bmpActual1.getHeight());
		assertEquals("スタンプを1つ追加した後", bmpExpected1.getWidth(), bmpActual1.getWidth());
		assertEquals("スタンプを1つ追加した後", 1, bmpPaths.length);
		
		stampManager.addStamp(bmpExpected2);
		bmpPaths = signDir.list();
		bmpActual1 = BitmapFactory.decodeFile(bmpPaths[0]);
		bmpActual2 = BitmapFactory.decodeFile(bmpPaths[1]);
		assertEquals("スタンプを2つ追加した後", bmpExpected1.getHeight(), bmpActual1.getHeight());
		assertEquals("スタンプを2つ追加した後", bmpExpected1.getWidth(), bmpActual1.getWidth());
		assertEquals("スタンプを2つ追加した後", bmpExpected2.getHeight(), bmpActual2.getHeight());
		assertEquals("スタンプを2つ追加した後", bmpExpected2.getWidth(), bmpActual2.getWidth());
		assertEquals("スタンプを1つ追加した後", 2, bmpPaths.length);
	}

	public void testIteration() {
		
		StampManager.StampsIterator iterator = stampManager.iterator();
		assertFalse("まだスタンプを追加していないとき", iterator.hasNext());
		assertNull("まだスタンプを追加していないとき", iterator.next());
		
		Bitmap bmpExpected = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
		Bitmap bmpActual;
		
		stampManager.addStamp(bmpExpected);
		iterator = stampManager.iterator();
	
		assertTrue("スタンプを1つ追加したとき", iterator.hasNext());
		bmpActual = iterator.next();
		assertEquals("スタンプを1つ追加したとき", bmpExpected.getHeight(), bmpActual.getHeight());
		assertEquals("スタンプを1つ追加したとき", bmpExpected.getWidth(), bmpActual.getWidth());
		assertFalse("スタンプを1つ追加して1つ読んだ後", iterator.hasNext());
		assertNull("スタンプを1つ追加して1つ読んだ後", iterator.next());
		
		stampManager.addStamp(bmpExpected);
		stampManager.addStamp(bmpExpected);
		iterator = stampManager.iterator();
		
		assertTrue("スタンプを2つ追加したとき", iterator.hasNext());
		bmpActual = iterator.next();
		assertEquals("スタンプを2つ追加したとき", bmpExpected.getHeight(), bmpActual.getHeight());
		assertEquals("スタンプを2つ追加したとき", bmpExpected.getWidth(), bmpActual.getWidth());
		assertTrue("スタンプを2つ追加して1つ読んだ後", iterator.hasNext());
		
		bmpActual = iterator.next();
		assertEquals("スタンプを2つ追加して1つ読んだとき", bmpExpected.getHeight(), bmpActual.getHeight());
		assertEquals("スタンプを2つ追加して1つ読んだとき", bmpExpected.getWidth(), bmpActual.getWidth());
		assertFalse("スタンプを2つ追加して2つ読んだ後", iterator.hasNext());
		assertNull("スタンプを2つ追加して2つ読んだ後", iterator.next());
	}
	
	// Helper Methods
	private String getPathInDir (File directory, String relativePath){
		return directory.getAbsolutePath() + File.separator + relativePath;
	}

}
