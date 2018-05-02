
	package ar.edu.itba.ss.tp5.core;

	import ar.edu.itba.ss.core.Particle;
	import ar.edu.itba.ss.tp2.core.MobileParticle;
	import ar.edu.itba.ss.tp3.core.MassiveParticle;
	import ar.edu.itba.ss.tp4.core.Vector;

	public class GranularParticle extends MassiveParticle {

		public GranularParticle(
				final double x, final double y, final double radius,
				final double vx, final double vy,
				final double mass) {
			super(x, y, radius, vx, vy, mass);
		}

		public double leftξ0() {
			return x < radius? radius - x : 0.0;
		}

		public double rightξ0(final double width) {
			return width - x < radius? radius + x - width : 0.0;
		}

		public double floorξ0() {
			return y < radius? radius - y : 0.0;
		}

		public double leftξ1(final double leftξ0) {
			return 0.0 < leftξ0? -Math.abs(vx) : 0.0;
		}

		public double rightξ1(final double rightξ0) {
			return 0.0 < rightξ0? -Math.abs(vx) : 0.0;
		}

		public double floorξ1(final double floorξ0) {
			return 0.0 < floorξ0? -Math.abs(vy) : 0.0;
		}

		public <T extends Particle> double ξ0(final T particle) {
			final double ξ0 = -distance(particle);
			//return 0 < ξ0? ξ0 : 0.0;
			return ξ0;
		}

		public <T extends MobileParticle> double ξ1(final T particle) {
			return -Math.hypot(particle.getVx() - vx, particle.getVy() - vy);
		}

		public <T extends Particle> Vector normal(final T particle) {
			return Vector
					.of(particle.getX() - x, particle.getY() - y)
					.versor();
		}

		public <T extends MobileParticle> Vector relativeVelocity(final T particle) {
			return Vector.of(vx - particle.getVx(), vy - particle.getVy());
		}
	}
