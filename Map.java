import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.io.IOException;


public class Map
{
	// Fields
	private String filepath;
	private int dimension;
	private int startingX;
	private int startingY;
	private int goalX;
	private int goalY;
	private char[][] cells;

	// Custom constructor
	public Map (String _filepath)
	{
		this.filepath = _filepath;

		// Parse File
		this.parseMapFile(this.filepath);
	}

	public int getDimension ()
	{
		return this.dimension;
	}

	public int getStartingX ()
	{
		return this.startingX;
	}

	public int getStartingY ()
	{
		return this.startingY;
	}

	public int getGoalX ()
	{
		return this.goalX;
	}

	public int getGoalY ()
	{
		return this.goalY;
	}

	public boolean isTraversable (int _x, int _y)
	{
		// Check if coordinates are within bounds
		if (_x < 0 || _x >= this.dimension)
			return false;

		if (_y < 0 || _y >= this.dimension)
			return false;

		// Check if there is an obstacle
		if (this.cells[_y][_x] == '+')
			return false;

		return true;
	}

	public boolean isVisited (int _x, int _y)
	{
		if (this.cells[_y][_x] == 'o' || this.cells[_y][_x] == 'i')
			return true;

		//System.out.println("(" + _x + "," + _y +") contains:" + this.cells[_y][_x]);

		return false;
	}

	public void markVisited (int _x, int _y)
	{
		if (this.cells[_y][_x] != 'g' && this.cells[_y][_x] != this.cells[this.startingY][this.startingX])
			this.cells[_y][_x] = 'o';
	}

	static String readFile (String _path, Charset _encoding) throws IOException
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(_path));
	  return _encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}

	private void parseMapFile (String _filepath)
	{
		/**
		 * Attempt To Open Map File
		 **/

		String raw = "";

		try
		{
			raw = Map.readFile(_filepath, Charset.defaultCharset());
		}
		catch(IOException ioe)
		{
			System.out.print(ioe.toString());
		}

		/**
		 * Interpret Contents
		 **/

		// Split Raw String At Line Breaks
		String[] rawLines = raw.split(System.getProperty("line.separator"));

		// Get Dimension, Xnitialize Array
		this.dimension = Integer.parseInt(rawLines[0]);
		this.cells = new char[this.dimension][this.dimension];

		// Generate Map Array
		for (int y = 1; y < rawLines.length; ++y)
		{
			for (int x = 0; x < rawLines[y].length(); ++x)
			{
				char c = rawLines[y].charAt(x);
				// Assign to Position in Array
				this.cells[y-1][x] = c;

				// Save Position If Xnitial
				if (c == 'i')
				{
					this.startingY = y-1;
					this.startingX = x;
				}

				// Save Position If Goal
				if (c == 'g')
				{
					this.goalY = y-1;
					this.goalX = x;
				}
			}
		}
	}

	public void clean ()
	{
		for (int y = 0; y < this.cells.length; ++y)
		{
			for (int x = 0; x < this.cells.length; ++x)
			{
				if (this.cells[y][x] == 'o')
					this.cells[y][x] = '.';
			}
		}
	}

	public void print ()
	{
		for (int y = 0; y < this.cells.length; ++y)
		{
			for (int x = 0; x < this.cells.length; ++x)
			{
				System.out.print(this.cells[y][x]);
			}

			System.out.println("");
		}

		System.out.println("");
	}

}