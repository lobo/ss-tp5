
	package ar.edu.itba.ss.tp5.support;

	import java.lang.reflect.InvocationTargetException;

	public class ClassBuilder {

		public static Object getInstance(final String name)
				throws ClassNotFoundException {
			try {
				return Class.forName(name)
						.getDeclaredConstructor()
						.newInstance();
			}
			catch (final InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException exception) {
				throw new ClassNotFoundException("", exception);
			}
		}
	}
