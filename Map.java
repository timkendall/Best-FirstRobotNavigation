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
	private char[][] cells;

	// Custom constructor
	public Map(String _filepath)
	{
		this.filepath = _filepath;

		// Parse File
		this.parseMapFile(this.filepath);
	}

	public int getDimension()
	{
		return this.dimension;
	}

	static String readFile(String path, Charset encoding) throws IOException
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}

	private void parseMapFile(String _filepath)
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

		// Get Dimension, Initialize Array
		this.dimension = Integer.parseInt(rawLines[0]);
		this.cells = new char[this.dimension][this.dimension];

		// Generate Map Array
		for(int i = 1; i < rawLines.length; ++i)
		{
			for (int j = 0; j < rawLines[i].length(); ++j)
			{
				char c = rawLines[i].charAt(j);
				// Assign to Position in Array
				this.cells[i-1][j] = c;
			}
		}
	}

	private void generateMap(int _dimension)
	{

	}

	public void print()
	{
		for(int i = 0; i < this.cells.length; ++i)
		{
			for (int j = 0; j < this.cells.length; ++j)
			{
				System.out.print(this.cells[i][j]);
			}

			System.out.println("");
		}
	}

}