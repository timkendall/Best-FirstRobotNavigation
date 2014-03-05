import java.util.Scanner;
import java.nio.charset.Charset;
import java.io.IOException;

public class Main {
	// Main method
	public static void main (String[] args) {
		// Quick Naive Check For Filepath
		if (args.length == 0) {
			System.out.println("Please provide a map file.");
			System.exit(0);
		}

		// Generate World Map
		Map world = new Map(args[0]);
		System.out.println("Initial map: ");
		world.print();

		// Power Up Robots
		Robot buster1 = new Robot(world);
		Robot buster2 = new Robot(world);
		Robot buster3 = new Robot(world);
		Robot buster4 = new Robot(world);

		// Solve Using 4 Functions
		buster1.solveBestFirst(1);
		System.out.println("Solution (Euclidean Distance): ");
		buster1.printSolution();
		buster1.clean();

		buster2.solveBestFirst(2);
		System.out.println("Solution (Manhattan Distance): ");
		buster2.printSolution();
		buster2.clean();

		buster3.solveBestFirst(3);
		System.out.println("Solution (Euclidean Distance + Total Cost): ");
		buster3.printSolution();
		buster3.clean();

		buster4.solveBestFirst(4);
		System.out.println("Solution (Manhattan Distance + Total Cost): ");
		buster4.printSolution();
		buster4.clean();

		// Done.
	}
}