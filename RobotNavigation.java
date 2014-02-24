import java.util.Scanner;
import java.nio.charset.Charset;
import java.io.IOException;

public class RobotNavigation
{
	// Main method
	public static void main(String[] args)
	{
		// Generate World Map
		Map world = new Map(args[0]);
		System.out.println("Dimension: " + world.getDimension());
		world.print();

	}

}