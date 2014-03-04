import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.io.IOException;
import java.util.LinkedList;


public class Map {
	// Fields
	private String filepath;
	private int dimension;
	private State initial;
	private State goal;
	private char[][] visual;
	private LinkedList<State> nodes;

	// Custom constructor
	public Map (String _filepath) {
		this.filepath = _filepath;
		this.nodes = new LinkedList<State>();

		// Parse file
		this.parseMapFile(this.filepath);

		// Generate node children
		this.generateChildren();
	}

	public int getDimension () {
		return this.dimension;
	}

	static String readFile (String _path, Charset _encoding) throws IOException {
	  byte[] encoded = Files.readAllBytes(Paths.get(_path));
	  return _encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}

	private void parseMapFile (String _filepath) {
		/**
		 * Attempt To Open Map File
		 **/

		String raw = "";

		try {
			// Get string of file contents
			raw = Map.readFile(_filepath, Charset.defaultCharset());
		} catch (IOException ioe) {
			System.out.print(ioe.toString());
		}

		/**
		 * Interpret Contents
		 **/

		// Split Raw String At Line Breaks
		String[] rawLines = raw.split(System.getProperty("line.separator"));

		// Get Dimension, Initialize Arrays
		this.dimension = Integer.parseInt(rawLines[0]);
		this.visual = new char[this.dimension][this.dimension];
		this.nodes = new State[this.dimension][this.dimension];

		/**
		 * Generate Nodes
		 **/

		// Loop through lines
		for (int y = 1; y < rawLines.length; ++y) {
			// Loop through characters
			for (int x = 0; x < rawLines[y].length(); ++x) {
				// Get char
				char c = rawLines[y].charAt(x);
				this.visual[y-1][x] = c;

				// Continue if non-valid state
				if (c == '+') continue;

				// Create node
				if (c == 'i') {
					// Save reference to initial state
					this.initial = new State(x, y-1);
					this.nodes.addLast(this.initial)
				} else if (c == 'g') {
					// Save reference to goal state
					this.goal = new State(x, y-1);
					this.nodes.addLast(this.goal);
				} else {
					this.nodes.addLast(new State(x, y-1));
				}
			}
		}
	}

	private void generateChildren () {
		// Loop through visual array
		for (int y = 0; y < this.visual.length; ++y) {
			for (int x = 0; x < this.visual.length; ++x) {
				// Get state reference
				State state = this.node[y][x];
				LinkedList<State> children = new LinkedList<State>();

				if (this.isOnMap(x+1, y) && this.visual[y][x+1] != '+')
					children.addLast(this.nodes[y][x+1])

				if (this.isOnMap(x-1, y) && this.visual[y][x-1] != '+')
					children.addLast(this.nodes[y][x-1])

				if (this.isOnMap(x, y+1) && this.visual[y+1][x] != '+')
					children.addLast(this.nodes[y+1][x])

				if (this.isOnMap(x, y-1) && this.visual[y-1][x] != '+')
					children.addLast(this.nodes[y-1][x])

				// Save children to reference
				state.setChildren(children);
			}
		}
	}

	public boolean isOnMap (int _x, int _y) {
		if (_x < 0 || _y < 0)
			return false;

		if (_x > this.dimension || _y > this.dimension)
			return false;

		return true;
	}

	public LinkedList<State> getNodes () {
		return this.nodes;
	}

	public State getInitial () {
		return this.initial;
	}

	public State getGoal () {
		return this.goal;
	}

	public void markVisited (int _x, int _y) {
		if (this.visual[_y][_x] != 'g' && this.visual[_y][_x] != this.visual[this.startingY][this.startingX])
			this.visual[_y][_x] = 'o';
	}

	public void print ()
	{
		for (int y = 0; y < this.visual.length; ++y)
		{
			for (int x = 0; x < this.visual.length; ++x)
			{
				System.out.print(this.visual[y][x]);
			}

			System.out.println("");
		}

		System.out.println("");
	}

}