
	package ar.edu.itba.ss.tp5.core;

	import java.util.ArrayList;
	import java.util.List;
	import java.util.Random;

	import ar.edu.itba.ss.tp4.core.Vector;

	public class Generator {

		protected final List<GranularParticle> particles;
		protected final Random random;

		public Generator(final Builder builder) {
			this.random = new Random(builder.seed);
			this.particles = new ArrayList<>(builder.size);
			while (particles.size() < builder.size) {
				if (addWithoutCollision(builder)) {
					System.out.print(
						"\t\tLoaded particles: " + particles.size() + "\r");
				}
			}
			if (0.0 < builder.drain) addDrain(builder);
		}

		protected Generator addDrain(final Builder builder) {
			final double drain0 = 0.5 * (builder.space.getX() - builder.drain);
			particles.add(new GranularParticle(
					drain0, 0.0, 0.0,
					0.0, 0.0, 1.0E+20));
			particles.add(new GranularParticle(
					drain0 + builder.drain, 0.0, 0.0,
					0.0, 0.0, 1.0E+20));
			return this;
		}

		public static Builder with(final long seed, final int size) {
			return new Builder(seed, size);
		}

		public List<GranularParticle> getParticles() {
			return particles;
		}

		protected GranularParticle newParticle(final Builder builder) {
			final double Δradius = builder.maxRadius - builder.minRadius;
			final double radius = Δradius * random.nextDouble() + builder.minRadius;
			final Vector r0 = Vector.of(radius, radius)
					.add(Vector.of(
						(builder.space.getX() - 2.0 * radius) * random.nextDouble(),
						(builder.space.getY() - 2.0 * radius) * random.nextDouble()));
			return new GranularParticle(r0.getX(), r0.getY(), radius, 0.0, 0.0, builder.mass);
		}

		protected boolean addWithoutCollision(final Builder builder) {
			final GranularParticle particle = newParticle(builder);
			final boolean collide = particles.stream()
					.anyMatch(particle::overlap);
			if (!collide) {
				particles.add(particle);
			}
			return !collide;
		}

		public static class Builder {

			protected final long seed;
			protected final int size;
			protected double mass;
			protected double minRadius;
			protected double maxRadius;
			protected double drain;
			protected Vector space;

			public Builder(final long seed, final int size) {
				this.seed = seed;
				this.size = size;
			}

			public Generator build() {
				return new Generator(this);
			}

			public Builder mass(final double mass) {
				this.mass = mass;
				return this;
			}

			public Builder minRadius(final double minRadius) {
				this.minRadius = minRadius;
				return this;
			}

			public Builder maxRadius(final double maxRadius) {
				this.maxRadius = maxRadius;
				return this;
			}

			public Builder in(final double width, final double height) {
				this.space = Vector.of(width, height);
				return this;
			}

			public Builder withDrain(final double drain) {
				this.drain = drain;
				return this;
			}
		}
	}
