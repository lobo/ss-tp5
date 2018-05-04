
	package ar.edu.itba.ss.tp5.core.field;

	import java.util.List;

	import ar.edu.itba.ss.tp3.core.MassiveParticle;
	import ar.edu.itba.ss.tp4.core.Vector;
	import ar.edu.itba.ss.tp4.interfaces.ForceField;

	public class EarthGravity<T extends MassiveParticle>
		implements ForceField<T> {

		public static final double g = 9.8196;

		@Override
		public Vector apply(final List<T> state, final T body) {
			if (0.0 < body.getRadius()) {
				return Vector.of(0.0, -g * body.getMass());
			}
			else return Vector.ZERO;
		}

		@Override
		public boolean isVelocityDependent() {
			return false;
		}

		@Override
		public boolean isConservative() {
			return true;
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
			return 0;
		}

		@Override
		public double potentialEnergy(final T body) {
			return g * body.getMass() * body.getY();
		}
	}
