
	package ar.edu.itba.ss.tp5.core;

	import java.util.List;

	import ar.edu.itba.ss.tp3.core.MassiveParticle;
	import ar.edu.itba.ss.tp4.core.Vector;
	import ar.edu.itba.ss.tp4.interfaces.ForceField;
	import ar.edu.itba.ss.tp5.config.Configuration;

	public class GranularFlow implements ForceField<MassiveParticle> {

		protected final EarthGravity gravity;
		protected final ContactForce contact;
		protected final DragForce drag;

		public GranularFlow(final Configuration configuration) {
			this.gravity = new EarthGravity();
			this.contact = new ContactForce();
			this.drag = new DragForce();
		}

		@Override
		public Vector apply(
				final List<MassiveParticle> state,
				final MassiveParticle body) {
			return gravity.apply(state, body)
					.add(contact.apply(state, body))
					.add(drag.apply(state, body));
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
			return gravity.derivative1(state, body)
					.add(contact.derivative1(state, body))
					.add(drag.derivative1(state, body));
		}

		@Override
		public Vector derivative2(
				final List<MassiveParticle> state,
				final MassiveParticle body) {
			return gravity.derivative2(state, body)
					.add(contact.derivative2(state, body))
					.add(drag.derivative2(state, body));
		}

		@Override
		public Vector derivative3(
				final List<MassiveParticle> state,
				final MassiveParticle body) {
			return gravity.derivative2(state, body)
					.add(contact.derivative2(state, body))
					.add(drag.derivative2(state, body));
		}

		@Override
		public double energyLoss(final double time) {
			return gravity.energyLoss(time) +
					contact.energyLoss(time) +
					drag.energyLoss(time);
		}

		@Override
		public double potentialEnergy(final MassiveParticle body) {
			return gravity.potentialEnergy(body) +
					contact.potentialEnergy(body) +
					drag.potentialEnergy(body);
		}
	}
