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
	private PriorityQueue<State> frontier;
	private LinkedList<State> solution;
	private int totalCost;

	// Custom constructor
	public Robot (Map _world)
	{
		this.world = _world;
		this.initial = new State(_world.getStartingX(), _world.getStartingY());
		this.goal = new State(_world.getGoalX(), _world.getGoalY());
		this.current = new State(_world.getStartingX(), _world.getStartingY());
		this.totalCost = 0;

		// Setup Solution Queue
		this.solution = new LinkedList<State>();
		this.solution.addFirst(this.current);

		// Setup Frontier (Priority) Queue
		int initialCapacity = 4;
		Comparator<State> comparator = new StateGoodnessComparator();
		this.frontier = new PriorityQueue<State>(initialCapacity, comparator);
	}

	public void solve (int _function)
	{

		do
		{
			this.move(_function);
			//this.world.print();
		} while (this.current.equals(this.goal) != true);

		// Really should handle if there is no solution
	}

	private void move (int _function)
	{
		int currentX = this.current.getX();
		int currentY = this.current.getY();
		int xNext;
		int yNext;

		// Generate Successors
		for (int i = 0; i < 4; ++i)
		{
			// Try up, down, left, right
			if (i == 0)
			{
				xNext = currentX;
				yNext = currentY + 1;
			} else if (i == 1)
			{
				xNext = currentX;
				yNext = currentY - 1;
			} else if (i == 2)
			{
				xNext = currentX + 1;
				yNext = currentY;
			} else
			{
				xNext = currentX - 1;
				yNext = currentY;
			}

			// Ignore state if not transversable
			if (!this.world.isTraversable(xNext, yNext)) continue;

			// Ignore state if visited
			if (this.world.isVisited(xNext, yNext)) continue;

			// Otherwise add the state to queue
			this.frontier.add(new State(xNext, yNext, this.initial, this.goal, this.totalCost,  _function));
		}

		// If stuck, go back one
		if (this.frontier.size() == 0 && !this.current.equals(this.goal))
		{
			this.totalCost--;
			this.current = this.solution.removeFirst();
			this.world.markVisited(this.current.getX(), this.current.getY());
		}
		else
		{
			// Pop first state of queue, this is our new state
			this.current = this.frontier.poll();
			// Add node to solution stack
			this.solution.addFirst(this.current);
			// Mark state as visited
			this.world.markVisited(this.current.getX(), this.current.getY());
			this.totalCost++;
		}

		// Clear frontier queue
		this.frontier.clear();
	}

	public void printSolution ()
	{
		this.world.clean();

		// Mark our final path
		for(State state : this.solution)
		{
			this.world.markVisited(state.getX(), state.getY());
		}

		System.out.println("Path Cost: " + this.totalCost);
		System.out.println("Nodes in Tree: " + this.frontier.size());
		this.world.print();
	}

	public void clean ()
	{
		this.world.clean();
		this.frontier.clear();
		this.solution.clear();
		this.current = this.initial;
		this.totalCost = 0;
	}
}