
	package ar.edu.itba.ss.tp5.core;

	import java.util.List;

	import ar.edu.itba.ss.tp3.core.MassiveParticle;
	import ar.edu.itba.ss.tp4.core.Vector;
	import ar.edu.itba.ss.tp4.interfaces.ForceField;

	public class DragForce implements ForceField<MassiveParticle> {

		@Override
		public Vector apply(
				final List<MassiveParticle> state,
				final MassiveParticle body) {
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
			return 0.0;
		}

		@Override
		public double potentialEnergy(final MassiveParticle body) {
			return 0.0;
		}
	}
