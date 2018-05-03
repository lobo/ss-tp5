
	package ar.edu.itba.ss.tp5.io;

	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.List;
	import java.util.Optional;

	import ar.edu.itba.ss.tp3.core.MassiveParticle;
	import ar.edu.itba.ss.tp5.config.Configuration;

	public class OutputFile {

		protected final PrintWriter output;
		protected final boolean hasDrain;

		protected OutputFile(final Configuration configuration)
				throws IOException {
			this.output = new PrintWriter(
					new FileWriter(configuration.getOutput()));
			this.hasDrain = 0.0 < configuration.getDrain();
		}

		public static Optional<OutputFile> in(
				final Configuration configuration) {
			try {
				return Optional.of(new OutputFile(configuration));
			}
			catch (final IOException exception) {
				System.out.println(
					"No se pudo crear el archivo de simulación.");
				return Optional.empty();
			}
		}

		public <T extends MassiveParticle> OutputFile write(
				final double time, final List<T> particles) {
			int remaining = particles.size() - (hasDrain? 2 : 0);
			for (final T p : particles) {
				if (0 < remaining--) {
					output.println(
						p.getX() + " " + p.getY() + " " +
						p.getRadius() + " " + p.getSpeed());
				}
				else break;
			}
			return this;
		}

		public void close() {
			output.close();
		}
	}
