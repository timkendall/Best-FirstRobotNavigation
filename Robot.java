import java.util.Comparator;
import java.util.PriorityQueue;
import java.lang.Math;

public class Robot
{
	// Fields
	private Map world;
	private PriorityQueue<State> frontier;
	private State initial;
	private State goal;
	private State current;
	private int totalCost;

	// Custom constructor
	public Robot (Map _world)
	{
		this.world = _world;
		this.initial = new State(_world.getStartingX(), _world.getStartingY());
		this.goal = new State(_world.getGoalX(), _world.getGoalY());
		this.current = new State(_world.getStartingX(), _world.getStartingY());
		this.totalCost = 0;

		// Setup Frontier (Priority) Queue
		int initialCapacity = 4;
		Comparator<State> comparator = new StateGoodnessComparator();
		frontier = new PriorityQueue<State>(initialCapacity, comparator);

		// Setup Solution Queue

	}

	public void solve ()
	{
		do
		{
			this.move();
			//this.world.print();
		} while (this.current.equals(this.goal) != true);

		// Really should handle if there is no solution
	}

	private void move ()
	{
		int currentX = this.current.getX();
		int currentY = this.current.getY();

		//System.out.println("currentX: " + currentX);
		//System.out.println("currentY: " + currentY);

		int xNext;
		int yNext;

		// Push all viable states (up to 4) to priority queue
		for (int i = 0; i < 4; ++i)
		{
			// Try up, down, left, right
			if (i == 0)
			{
				//System.out.println("Up");
				xNext = currentX;
				yNext = currentY + 1;
			}

			else if (i == 1)
			{
				//System.out.println("Down");
				xNext = currentX;
				yNext = currentY - 1;
			}

			else if (i == 2)
			{
				//System.out.println("Left");
				xNext = currentX + 1;
				yNext = currentY;
			}

			else
			{
				//System.out.println("Right");
				xNext = currentX - 1;
				yNext = currentY;
			}

			// Ignore state if not transversable
			if (!this.world.isTraversable(xNext, yNext))
			{
				//System.out.println("Non-transversable");
				continue;
			}

			// Ignore state if visited
			if (this.world.isVisited(xNext, yNext))
			{
				//System.out.println("xNext: " + xNext);
				//System.out.println("yNext: " + yNext);

				//System.out.println("Already visited");
				continue;
			}

			// Otherwise add the state to queue
			this.frontier.add(new State(xNext, yNext, this.initial, this.goal, this.totalCost));
			//System.out.println("Length: " + this.frontier.size());
		}

		// Pop first state of queue, this is our new state
		this.current = this.frontier.poll();
		//this.solution.add(this.currrent);
		this.world.markVisited(this.current.getX(), this.current.getY());
		this.totalCost++;

	}

	public void printSolution ()
	{
		this.world.print();
		System.out.println("Path Cost: " + this.totalCost);
	}
}