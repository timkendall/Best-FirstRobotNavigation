import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.lang.Math;

public class Robot
{
	// Fields
	private Map world;
	private State initial;
	private State goal;
	private State current;
	private LinkedList<State> closed;
	private PriorityQueue<State> frontier;
	private LinkedList<State> path;

	// Custom constructor
	public Robot (Map _world) {
		// Initialize references
		this.world = _world;
		this.initial = this.world.getInitial();
		this.goal = this.world.getGoal();
		this.current = this.initial;

		// Setup Closed Queue
		this.closed = new LinkedList<State>();
		this.closed.addLast(this.current);

		// Setup Path Stack
		this.path = new LinkedList<State>();
		this.path.addFirst(this.current);

		// Setup Frontier (Priority) Queue
		int initialCapacity = 4;
		Comparator<State> comparator = new StateGoodnessComparator();
		this.frontier = new PriorityQueue<State>(initialCapacity, comparator);
	}

	public void solveBestFirst (int _function) {
		// Run Best-First search algorithm
		while (this.closed.size() != this.world.getNumNodes() && this.current != this.goal) {
			this.current.print();
			// Get current's children
			LinkedList<State> currentsChildren = this.current.getChildren();

			// Backtrack if stuck
			if (currentsChildren.size() == 0) {
				System.out.println("Backtracking 1");
				this.current = this.path.removeFirst();
			// Continue
			} else {
				// Push onto frontier (priority queue)
				for (State state : currentsChildren) {
					// Ignore closed states
					if(this.closed.contains(state)) continue;

					// Calculate goodness
					state.calculateGoodness(_function, this.initial, this.goal, this.path.size());
					this.frontier.add(state);
				}

				if (this.frontier.size() == 0) {
					System.out.println("Backtracking 2");
					this.current = this.path.removeFirst();
					continue;
				}

				// Pull of best state
				this.current = this.frontier.poll();

				// Add to Closed
				this.closed.addLast(this.current);

				// Push onto our path stack
				this.path.addFirst(this.current);
				this.frontier.clear();
			}
		}

		// Handle failure
		if (this.current != this.goal)
			System.out.println("Failed to find solution path.");
	}

	public void printSolution () {
		// Mark our final path
		for(State state : this.path) {
			this.world.markVisited(state.getX(), state.getY());
		}

		System.out.println("Path Cost: " + this.path.size());
		System.out.println("Nodes in Tree: " + this.closed.size());
		this.world.print();
	}
}