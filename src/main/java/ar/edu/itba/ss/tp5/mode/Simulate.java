
	package ar.edu.itba.ss.tp5.mode;

	import java.util.List;
	import java.util.Random;

	import ar.edu.itba.ss.tp4.core.MassiveParticleFactory;
	import ar.edu.itba.ss.tp4.core.TimeDrivenSimulation;
	import ar.edu.itba.ss.tp4.integrators.BeemanIntegrator;
	import ar.edu.itba.ss.tp4.integrators.GearIntegrator;
	import ar.edu.itba.ss.tp4.integrators.VelocityVerlet;
	import ar.edu.itba.ss.tp4.interfaces.ForceField;
	import ar.edu.itba.ss.tp4.interfaces.Integrator;
	import ar.edu.itba.ss.tp5.config.Configuration;
	import ar.edu.itba.ss.tp5.core.Generator;
	import ar.edu.itba.ss.tp5.core.GranularParticle;
	import ar.edu.itba.ss.tp5.core.GranularParticleFactory;
	import ar.edu.itba.ss.tp5.core.field.GranularFlow;
	import ar.edu.itba.ss.tp5.interfaces.Mode;
	import ar.edu.itba.ss.tp5.io.OutputFile;

	public class Simulate implements Mode {

		@Override
		public void run(final Configuration configuration) {
			System.out.println("Simulation...");
			OutputFile.in(configuration)
				.ifPresent(output -> {
					try {
						final Random random = new Random(configuration.getGenerator());
						final double Δt = configuration.getDelta();
						final double Δs = 1.0 / configuration.getSamplesPerSecond();
						final long Δ = Δs < Δt? 1 : Math.round(Δs/Δt);
						TimeDrivenSimulation.of(
								buildIntegrator(
									configuration,
									new GranularParticleFactory<GranularParticle>(),
									new GranularFlow<GranularParticle>(configuration),
									Generator.with(configuration.getGenerator(), configuration.getN())
										.in(configuration.getWidth(), configuration.getHeight())
										.withDrain(configuration.getDrain())
										.mass(configuration.getMass())
										.minRadius(configuration.getRadius()[0])
										.maxRadius(configuration.getRadius()[1])
											.build()
										.getParticles()))
							.reportEnergy(configuration.getReportEnergy())
							.reportTime(configuration.getReportTime())
							.maxTime(configuration.getTime())
							.by(Δt)
							.spy((time, ps) -> {
								injector(configuration, random, time, ps);
								if (Math.round(time/Δt) % Δ == 0) {
									output.write(time, ps);
								}
							})
							.build()
							.run();
					}
					catch (final ClassNotFoundException exception) {
						System.out.println(
							"El integrador es desconocido. Especifique otro.");
					}
					System.out.println(
						"\tLa simulación fue almacenada con éxito.");
					output.close();
				});
		}

		protected void injector(
				final Configuration configuration, final Random random,
				final double time, final List<GranularParticle> particles) {
			for (int i = 0; i < particles.size(); ++i) {
				final GranularParticle p = particles.get(i);
				if (p.getY() < -0.1 * configuration.getHeight()) {
					final double x = p.getRadius()
							+ (configuration.getWidth() - 2.0 * p.getRadius())
							* random.nextDouble();
					final GranularParticle pNew = new GranularParticle(
							x, configuration.getHeight(), p.getRadius(),
							0.0, 0.0, p.getMass());
					if (!particles.stream().anyMatch(pNew::overlap)) {
						particles.set(i, pNew);
					}
				}
			}
		}

		protected static <T extends GranularParticle> Integrator<T> buildIntegrator(
				final Configuration configuration,
				final MassiveParticleFactory<T> factory,
				final ForceField<T> force,
				final List<T> state)
					throws ClassNotFoundException {

			switch (configuration.getIntegrator()) {
				case "VelocityVerlet" : {
					return VelocityVerlet.of(force)
							.withInitial(state)
							.factory(factory)
							.build();
				}
				case "BeemanIntegrator" : {
					return BeemanIntegrator.of(force)
							.withInitial(state)
							.factory(factory)
							.build();
				}
				case "GearIntegrator" : {
					return GearIntegrator.of(force)
							.Δt(configuration.getDelta())
							.withInitial(state)
							.factory(factory)
							.build();
				}
				default : {
					throw new ClassNotFoundException();
				}
			}
		}
	}
