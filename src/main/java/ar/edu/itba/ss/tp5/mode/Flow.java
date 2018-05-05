
	package ar.edu.itba.ss.tp5.mode;

	import ar.edu.itba.ss.tp5.config.Configuration;
	import ar.edu.itba.ss.tp5.interfaces.Mode;
	import ar.edu.itba.ss.tp5.io.FlowFile;

	public class Flow implements Mode {

		@Override
		public void run(final Configuration configuration) {
			System.out.println("Flowing...");
			if (FlowFile.generate(configuration)) {
				System.out.println(
					"\tEl flujo fue calculado con éxito.");
			}
		}
	}
