package info.android.akihabara.cos.sample02.test;

import android.graphics.Bitmap;
import info.android.akihabara.cos.sample02.StampManager;
import junit.framework.TestCase;

public class StampManagerTestCase extends TestCase {
	private static StampManager stampManager = null;

	public StampManagerTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		stampManager = new StampManager();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		stampManager.clearAllStamps();
	}

	public void testInitialize() {
		boolean result;
		
		stampManager.clearAllStamps();
		result = stampManager.initialize();
		assertTrue("一旦すべてのスタンプを消した後", result);
		
		result = stampManager.initialize();
		assertFalse("一旦スタンプを初期化した後", result);
	}
	
	public void testClearAllStamps() {
		boolean result;
		
		stampManager.initialize();
		result = stampManager.clearAllStamps();
		assertTrue("一旦スタンプを初期化した後", result);
	
		result = stampManager.clearAllStamps();
		assertFalse("一旦スタンプをクリアした後", result);
	}

	public void testIteration() {
		
		StampManager.StampsIterator iterator = stampManager.iterator();
		assertFalse("まだスタンプを追加していないとき", iterator.hasNext());
		assertNull("まだスタンプを追加していないとき", iterator.next());
		
		Bitmap bmpExpected = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
		Bitmap bmpActual = null;
		
		stampManager.addStamp(bmpExpected);
		iterator = stampManager.iterator();
	
		assertTrue("スタンプを1つ追加したとき", iterator.hasNext());
		bmpActual = iterator.next();
		assertEquals("スタンプを1つ追加したとき", bmpExpected, bmpActual);
		assertFalse("スタンプを1つ追加して1つ読んだ後", iterator.hasNext());
		assertNull("スタンプを1つ追加して1つ読んだ後", iterator.next());
		
		stampManager.addStamp(bmpExpected);
		stampManager.addStamp(bmpExpected);
		iterator = stampManager.iterator();
		
		assertTrue("スタンプを2つ追加したとき", iterator.hasNext());
		bmpActual = iterator.next();
		assertEquals("スタンプを2つ追加したとき", bmpExpected, bmpActual);
		assertTrue("スタンプを2つ追加して1つ読んだ後", iterator.hasNext());
		
		bmpActual = iterator.next();
		assertEquals("スタンプを2つ追加して1つ読んだとき", bmpExpected, bmpActual);
		assertFalse("スタンプを2つ追加して2つ読んだ後", iterator.hasNext());
		assertNull("スタンプを2つ追加して2つ読んだ後", iterator.next());
	}

}
