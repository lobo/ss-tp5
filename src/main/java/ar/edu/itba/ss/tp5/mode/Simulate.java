
	package ar.edu.itba.ss.tp5.mode;

	import ar.edu.itba.ss.tp5.config.Configuration;
	import ar.edu.itba.ss.tp5.interfaces.Mode;

	public class Simulate implements Mode {

		@Override
		public void run(final Configuration configuration) {
			System.out.println("Simulation...");
		}
	}
