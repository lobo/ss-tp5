
	package ar.edu.itba.ss.tp5.mode;

	import java.util.List;

	import ar.edu.itba.ss.tp4.core.MassiveParticleFactory;
	import ar.edu.itba.ss.tp4.core.TimeDrivenSimulation;
	import ar.edu.itba.ss.tp4.integrators.BeemanIntegrator;
	import ar.edu.itba.ss.tp4.integrators.GearIntegrator;
	import ar.edu.itba.ss.tp4.integrators.VelocityVerlet;
	import ar.edu.itba.ss.tp4.interfaces.ForceField;
	import ar.edu.itba.ss.tp4.interfaces.Integrator;
	import ar.edu.itba.ss.tp5.config.Configuration;
	import ar.edu.itba.ss.tp5.core.Generator;
	import ar.edu.itba.ss.tp5.core.GranularFlow;
	import ar.edu.itba.ss.tp5.core.GranularParticle;
	import ar.edu.itba.ss.tp5.core.GranularParticleFactory;
	import ar.edu.itba.ss.tp5.interfaces.Mode;
	import ar.edu.itba.ss.tp5.io.OutputFile;

	public class Simulate implements Mode {

		@Override
		public void run(final Configuration configuration) {
			System.out.println("Simulation...");
			OutputFile.in(configuration.getOutput())
				.ifPresent(output -> {

					/*
						Agregar presión en el output.
					*/
					try {
						TimeDrivenSimulation.of(
								buildIntegrator(
									configuration,
									new GranularParticleFactory<GranularParticle>(),
									new GranularFlow<GranularParticle>(configuration),
									Generator.with(configuration.getGenerator(), configuration.getN())
										.in(configuration.getWidth(), configuration.getHeight())
										.mass(configuration.getMass())
										.minRadius(configuration.getRadius()[0])
										.maxRadius(configuration.getRadius()[1])
											.build()
										.getParticles()))
							.reportEnergy(configuration.getReportEnergy())
							.reportTime(configuration.getReportTime())
							.maxTime(configuration.getTime())
							.by(configuration.getDelta())
							.spy(output::write)
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
