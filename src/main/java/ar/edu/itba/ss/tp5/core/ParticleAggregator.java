
	package ar.edu.itba.ss.tp5.core;

	import java.util.HashMap;
	import java.util.Map;

	import ar.edu.itba.ss.core.Particle;

	public class ParticleAggregator {

		protected static final ParticleAggregator instance
			= new ParticleAggregator();

		protected final Map<String, double []> aggregations;
		protected final Map<String, Integer> indexes;

		protected ParticleAggregator() {
			this.aggregations = new HashMap<>();
			this.indexes = new HashMap<>();
		}

		public static ParticleAggregator getInstance() {
			return instance;
		}

		public ParticleAggregator addAggregation(final String key, final int size) {
			aggregations.put(key, new double [size]);
			indexes.put(key, 0);
			return this;
		}

		public <T extends Particle> double [] getAggregation(final String key) {
			return aggregations.get(key);
		}

		public ParticleAggregator update(final String key, final double value) {
			final double [] values = aggregations.get(key);
			final int index = indexes.get(key);
			values[index] = value;
			indexes.put(key, (index + 1) % values.length);
			return this;
		}
	}
