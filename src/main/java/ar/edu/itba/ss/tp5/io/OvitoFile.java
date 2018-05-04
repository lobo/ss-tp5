
	package ar.edu.itba.ss.tp5.io;

	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.Scanner;

	import ar.edu.itba.ss.tp5.config.Configuration;

	public class OvitoFile {

		public static boolean generate(final Configuration configuration) {
			final String filename = configuration.getOutput();
			final int FPS = configuration.getFPS();
			final int N = configuration.getN();
			final double Δt = 1.0 / configuration.getSamplesPerSecond();
			final double playbackSpeed = configuration.getPlaybackSpeed();
			try (
				final Scanner input = new Scanner(new File(filename));
				final PrintWriter output = new PrintWriter(new FileWriter(filename + ".xyz"));
			) {
				if (configuration.getSamplesPerSecond() < FPS)
					throw new IllegalArgumentException();
				final int jump = (int) (playbackSpeed / (Δt * FPS));
				int line = 0;
				int chunk = 0;
				while (input.hasNext()) {

					if (chunk % jump == 0) {
						// Header...
						if (line % N == 0) {
							output.println(N);
							output.println(chunk * Δt);
						}
						// Chunk...
						for (int i = 0; i < N; ++i) {
							output.write(input.nextLine() + "\n");
						}
					}
					else {
						// Ignore...
						for (int i = 0; i < N; ++i) {
							input.nextLine();
						}
					}
					line += N;
					++chunk;
				}
				return true;
			}
			catch (final ArithmeticException exception) {
				System.out.println(
					"Se necesitan más muestras. Ajuste 'playbackSpeed' o simule con mayor SPS.");
			}
			catch (final IllegalArgumentException exception) {
				System.out.println(
					"Se necesitan más muestras. Verifique que FPS <= SPS.");
			}
			catch (final FileNotFoundException exception) {
				System.out.println(
					"El archivo de simulación no existe.");
			}
			catch (final IOException exception) {
				System.out.println(
					"No se pudo crear el archivo de animación.");
			}
			return false;
		}
	}
