
	package ar.edu.itba.ss.tp5.io;

	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.List;
	import java.util.Optional;

	import ar.edu.itba.ss.tp3.core.MassiveParticle;

	public class OutputFile {

		protected final PrintWriter output;

		protected OutputFile(final String filename)
				throws IOException {
			this.output = new PrintWriter(new FileWriter(filename));
		}

		public static Optional<OutputFile> in(final String filename) {
			try {
				return Optional.of(new OutputFile(filename));
			}
			catch (final IOException exception) {
				System.out.println(
					"No se pudo crear el archivo de simulación.");
				return Optional.empty();
			}
		}

		public <T extends MassiveParticle> OutputFile write(
				final double time, final List<T> particles) {
			for (final T p : particles) {
				output.println(
					p.getX() + " " + p.getY() + " " +
					p.getRadius() + " " + p.getSpeed());
			}
			return this;
		}

		public void close() {
			output.close();
		}
	}
