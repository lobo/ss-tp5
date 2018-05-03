
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
			return width - x < radius? radius - width + x : 0.0;
		}

		public double floorξ0(final double width, final double drain) {
			final double drain0 = 0.5 * (width - drain);
			if (drain0 < x && x < drain0 + drain) return 0.0;
			else return y < radius? radius - y : 0.0;
		}

		public double leftξ1(final double leftξ0) {
			return 0.0 < leftξ0? -vx : 0.0;
		}

		public double rightξ1(final double rightξ0, final double width) {
			return 0.0 < rightξ0? vx : 0.0;
		}

		public double floorξ1(final double floorξ0) {
			return 0.0 < floorξ0? -vy : 0.0;
		}

		public <T extends Particle> double ξ0(final T particle) {
			final double ξ0 = -distance(particle);
			return 0.0 < ξ0? ξ0 : 0.0;
		}

		public <T extends MobileParticle> double ξ1(final double ξ0, final T particle) {
			if (0.0 < ξ0) {
				final double Δx = x - particle.getX();
				final double Δy = y - particle.getY();
				return -((Δx * (vx - particle.getVx()) + Δy * (vy - particle.getVy()))
						/ Math.hypot(Δx, Δy));
			}
			else return 0.0;
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
