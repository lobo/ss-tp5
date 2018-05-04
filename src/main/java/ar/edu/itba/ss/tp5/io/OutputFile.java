
	package ar.edu.itba.ss.tp5.io;

	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.List;
	import java.util.Optional;

	import ar.edu.itba.ss.tp3.core.MassiveParticle;
	import ar.edu.itba.ss.tp5.config.Configuration;
	import ar.edu.itba.ss.tp5.core.ParticleAggregator;

	public class OutputFile {

		protected final ParticleAggregator aggregator;
		protected final PrintWriter output;
		protected final boolean hasDrain;

		protected OutputFile(final Configuration configuration)
				throws IOException {
			this.aggregator = ParticleAggregator.getInstance();
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
				final double time, final List<T> state) {
			final int particles = state.size() - (hasDrain? 2 : 0);
			final double [] pressures = aggregator.getAggregation("pressure");
			for (int i = 0; i < particles; ++i) {
				final T p = state.get(i);
				output.println(
					p.getX() + " " + p.getY() + " " +
					p.getRadius() + " " + p.getSpeed() + " " +
					pressures[i]);
			}
			return this;
		}

		public void close() {
			output.close();
		}
	}
