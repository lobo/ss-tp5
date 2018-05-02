
	package ar.edu.itba.ss.tp5.core;

	import java.util.List;

	import ar.edu.itba.ss.tp4.core.Vector;
	import ar.edu.itba.ss.tp4.interfaces.ForceField;
	import ar.edu.itba.ss.tp5.config.Configuration;

	public class ContactForce<T extends GranularParticle>
		implements ForceField<T> {

		protected final NeighbourCache cache;
		protected final double [] space;
		protected final double drain;
		protected final double k;
		protected final double γ;

		public ContactForce(
				final Configuration configuration,
				final NeighbourCache cache) {
			this.space = new double [] {
				configuration.getWidth(),
				configuration.getHeight()
			};
			this.cache = cache;
			this.drain = configuration.getDrain();
			this.k = configuration.getElasticNormal();
			this.γ = configuration.getViscousDamping();
		}

		@Override
		public Vector apply(final List<T> state, final T body) {
			final double leftξ0 = body.leftξ0();
			final double rightξ0 = body.rightξ0(space[0]);
			final double floorξ0 = body.floorξ0();
			final double leftξ1 = body.leftξ1(leftξ0);
			final double rightξ1 = body.rightξ1(rightξ0);
			final double floorξ1 = body.floorξ1(floorξ0);
			return Vector.of(k * leftξ0 + γ * leftξ1, 0.0)
				.add(Vector.of(-k * rightξ0 - γ * rightξ1, 0.0))
				.add(Vector.of(0.0, k * floorξ0 + γ * floorξ1))
				.add(cache.neighbours(state)
						.get(body)
						.stream()
						.map(p -> {
							final double ξ0 = body.ξ0(p);
							final double ξ1 = body.ξ1((GranularParticle) p);
							return body.normal(p).multiplyBy(-k * ξ0 - γ * ξ1);
						})
						.reduce(Vector.ZERO, (F1, F2) -> F1.add(F2)));
		}

		@Override
		public boolean isVelocityDependent() {
			return true;
		}

		@Override
		public boolean isConservative() {
			return false;
		}

		@Override
		public Vector derivative1(final List<T> state, final T body) {
			return Vector.ZERO;
		}

		@Override
		public Vector derivative2(final List<T> state, final T body) {
			return Vector.ZERO;
		}

		@Override
		public Vector derivative3(final List<T> state, final T body) {
			return Vector.ZERO;
		}

		@Override
		public double energyLoss(final double time) {
			return 0.0;
		}

		@Override
		public double potentialEnergy(final T body) {
			return 0.0;
		}
	}
