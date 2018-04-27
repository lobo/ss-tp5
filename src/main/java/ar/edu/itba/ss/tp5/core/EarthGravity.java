
	package ar.edu.itba.ss.tp5.core;

	import java.util.List;

	import ar.edu.itba.ss.tp3.core.MassiveParticle;
	import ar.edu.itba.ss.tp4.core.Vector;
	import ar.edu.itba.ss.tp4.interfaces.ForceField;

	public class EarthGravity implements ForceField<MassiveParticle> {

		public static final double g = 9.8196;

		@Override
		public Vector apply(
				final List<MassiveParticle> state,
				final MassiveParticle body) {
			return Vector.of(0.0, -g * body.getMass());
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
		public Vector derivative1(
				final List<MassiveParticle> state,
				final MassiveParticle body) {
			return Vector.ZERO;
		}

		@Override
		public Vector derivative2(
				final List<MassiveParticle> state,
				final MassiveParticle body) {
			return Vector.ZERO;
		}

		@Override
		public Vector derivative3(
				final List<MassiveParticle> state,
				final MassiveParticle body) {
			return Vector.ZERO;
		}

		@Override
		public double energyLoss(final double time) {
			return 0;
		}

		@Override
		public double potentialEnergy(final MassiveParticle body) {
			return g * body.getMass() * body.getY();
		}
	}
