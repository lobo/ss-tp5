
	package ar.edu.itba.ss.tp5.core;

	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.stream.Stream;

	import ar.edu.itba.ss.core.CellIndexMethod;
	import ar.edu.itba.ss.core.NearNeighbourList;
	import ar.edu.itba.ss.core.OptimalGrid;
	import ar.edu.itba.ss.core.Particle;
	import ar.edu.itba.ss.core.SquareSpace;
	import ar.edu.itba.ss.core.interfaces.ParticleGenerator;

	public class NeighbourCache {

		protected final Map<Integer, Map<Particle, List<Particle>>> cache;
		protected final int deep;
		protected final double space;
		protected final double radius;

		public NeighbourCache(final Builder builder) {
			this.cache = new HashMap<>();
			this.deep = builder.deep;
			this.space = builder.space;
			this.radius = builder.radius;
		}

		public <T extends Particle> Map<Particle, List<Particle>> neighbours(final List<T> state) {
			final int key = state.hashCode();
			if (cache.containsKey(key)) {
				return cache.get(key);
			}
			else {
				final Map<Particle, List<Particle>> nnl = NearNeighbourList
						.from(new StaticGenerator<>(state))
						.with(CellIndexMethod
								.by(OptimalGrid.AUTOMATIC)
								.build())
						.over(SquareSpace.of(space)
								.periodicBoundary(false)
								.build())
						.interactionRadius(radius)
						.cluster();
				if (1 + deep < cache.size()) {
					cache.clear();
				}
				cache.put(key, nnl);
				return nnl;
			}
		}

		public static Builder ofDeep(final int deep) {
			return new Builder(deep);
		}

		public static class Builder {

			protected final int deep;
			protected double space;
			protected double radius;

			public Builder(final int deep) {
				this.deep = deep;
			}

			public Builder space(final double width, final double height) {
				this.space = Math.max(width, height);
				return this;
			}

			public Builder interactionRadius(final double radius) {
				this.radius = radius;
				return this;
			}

			public NeighbourCache build() {
				return new NeighbourCache(this);
			}
		}

		protected static class StaticGenerator<T extends Particle>
			implements ParticleGenerator {

			protected final List<T> particles;

			public StaticGenerator(final List<T> particles) {
				this.particles = particles;
			}

			@Override
			public Stream<? extends Particle> generate() {
				return particles.stream();
			}

			@Override
			public int size() {
				return particles.size();
			}

			@Override
			public double maxRadius() {
				return 0.0;
			}
		}
	}
