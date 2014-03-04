import java.util.Scanner;
import java.nio.charset.Charset;
import java.io.IOException;

public class Main
{
	// Main method
	public static void main (String[] args)
	{

		// Quick Naive Check For Filepath
		if (args.length == 0)
		{
			System.out.println("Please provide a map file.");
			System.exit(0);
		}

		// Generate World Map
		Map world = new Map(args[0]);
		System.out.println("Initial map: ");
		world.print();

		// Power Up Robot
		Robot buster = new Robot(world);

		// Solve Using 4 Functions
		buster.solveBestFirst(1);
		System.out.println("Solution (Euclidean Distance): ");
		buster.printSolution();
/*
		buster.solve(2);
		System.out.println("Solution (Manhattan Distance): ");
		buster.printSolution();
		buster.clean();

		buster.solve(3);
		System.out.println("Solution (Euclidean Distance + Total Cost): ");
		buster.printSolution();
		buster.clean();

		buster.solve(4);
		System.out.println("Solution (Manhattan Distance + Total Cost): ");
		buster.printSolution();
		buster.clean();
*/
		// Done.
	}

}