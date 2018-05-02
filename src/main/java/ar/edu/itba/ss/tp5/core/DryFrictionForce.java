
	package ar.edu.itba.ss.tp5.core;

	import java.util.List;

	import ar.edu.itba.ss.tp4.core.Vector;
	import ar.edu.itba.ss.tp4.interfaces.ForceField;

	public class DryFrictionForce<T extends GranularParticle>
		implements ForceField<T> {

		@Override
		public Vector apply(final List<T> state, final T body) {
			return Vector.ZERO;
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
