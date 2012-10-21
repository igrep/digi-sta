package info.android.akihabara.cos.sample02.test;

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

	public void testAddStamp() {
		fail("Not yet implemented");
	}

	public void testReadStamps() {
		fail("Not yet implemented");
	}

}
