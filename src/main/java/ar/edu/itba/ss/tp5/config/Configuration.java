
	package ar.edu.itba.ss.tp5.config;

		/**
		* <p>Esta clase representa la estructura del archivo de configuración,
		* la cual será automáticamente completada durante el inicio del
		* sistema. La clase debe ser completamente mapeable con respecto al
		* archivo origen.</p>
		*/

	public final class Configuration {

		// Archivo de configuración origen:
		public static final String CONFIGURATION_FILENAME
			= "config.json";

		protected String output = "res/data/output.data";
		protected double delta = 1.0;
		protected double time = 1.0;
		protected int fps = 50;

		public String getOutput() {
			return output;
		}

		public double getDelta() {
			return delta;
		}

		public double getTime() {
			return time;
		}

		public int getFPS() {
			return fps;
		}
	}
