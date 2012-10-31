package info.android.akihabara.cos.sample02.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestUtility {
	public static class ObjectExposer <T>{
		private T object;
		private Class<? extends Object> klass;
		public ObjectExposer(T target) {
			object = target;
			klass = object.getClass();
		}
		public Method exposeMethod(Object object, String name) throws SecurityException, NoSuchMethodException{
			Method method = klass.getDeclaredMethod(name);
			method.setAccessible(true);
			return method;
		}
		public Field exposeField(Object object, String name) throws SecurityException, NoSuchFieldException{
			Field field = klass.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		}
	}
}
