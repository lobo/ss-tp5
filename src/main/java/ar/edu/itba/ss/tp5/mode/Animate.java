
	package ar.edu.itba.ss.tp5.mode;

	import ar.edu.itba.ss.tp5.config.Configuration;
	import ar.edu.itba.ss.tp5.interfaces.Mode;
	import ar.edu.itba.ss.tp5.io.OvitoFile;
	import ar.edu.itba.ss.tp5.io.SmallOutputFile;

	public class Animate implements Mode {

		@Override
		public void run(final Configuration configuration) {
			System.out.println("Animation...");
			if (OvitoFile.generate(configuration)) {

				System.out.println(
					"\tLa animación fue generada con éxito.");
				if (SmallOutputFile.generate(configuration.getOutput())) {
					System.out.println(
						"\tLa simulación reducida fue generada con éxito.");
				}
			}
		}
	}
