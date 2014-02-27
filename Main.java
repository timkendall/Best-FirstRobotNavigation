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
		world.print();

		// Power Up Robot
		Robot buster = new Robot(world);
		buster.solve();
		buster.printSolution();

		// Done.
	}

}