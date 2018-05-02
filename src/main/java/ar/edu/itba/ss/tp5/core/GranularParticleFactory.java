
	package ar.edu.itba.ss.tp5.core;

	import ar.edu.itba.ss.tp4.core.MassiveParticleFactory;

	public class GranularParticleFactory<T extends GranularParticle>
		extends MassiveParticleFactory<T> {

		@Override
		@SuppressWarnings("unchecked")
		public T create() {
			return (T) new GranularParticle(x, y, radius, vx, vy, mass);
		}
	}
