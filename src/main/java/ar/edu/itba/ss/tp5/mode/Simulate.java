
	package ar.edu.itba.ss.tp5.mode;

	import java.util.List;

	import ar.edu.itba.ss.tp3.core.MassiveParticle;
	import ar.edu.itba.ss.tp4.core.TimeDrivenSimulation;
	import ar.edu.itba.ss.tp4.integrators.BeemanIntegrator;
	import ar.edu.itba.ss.tp4.integrators.GearIntegrator;
	import ar.edu.itba.ss.tp4.integrators.VelocityVerlet;
	import ar.edu.itba.ss.tp4.interfaces.ForceField;
	import ar.edu.itba.ss.tp4.interfaces.Integrator;
	import ar.edu.itba.ss.tp5.config.Configuration;
	import ar.edu.itba.ss.tp5.core.Generator;
	import ar.edu.itba.ss.tp5.core.GranularFlow;
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
									new GranularFlow(configuration),
									Generator.with(configuration.getGenerator(), configuration.getN())
										.in(configuration.getWidth(), configuration.getHeight())
										.mass(configuration.getMass())
										.minRadius(configuration.getRadius()[0])
										.maxRadius(configuration.getRadius()[1])
											.build()
										.getParticles()))
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

		protected static Integrator<MassiveParticle> buildIntegrator(
				final Configuration configuration,
				final ForceField<MassiveParticle> force,
				final List<MassiveParticle> state)
					throws ClassNotFoundException {

			switch (configuration.getIntegrator()) {
				case "VelocityVerlet" : {
					return VelocityVerlet.of(force)
							.withInitial(state)
							.build();
				}
				case "BeemanIntegrator" : {
					return BeemanIntegrator.of(force)
							.withInitial(state)
							.build();
				}
				case "GearIntegrator" : {
					return GearIntegrator.of(force)
							.Δt(configuration.getDelta())
							.withInitial(state)
							.build();
				}
				default : {
					throw new ClassNotFoundException();
				}
			}
		}
	}
