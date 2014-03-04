import java.lang.Math;

public class State
{
  // Fields
  private int x;
  private int y;
  private double goodness;
  private int function;

  // Constructor for initial and goal states
  public State (int _x, int _y)
  {
    this.x = _x;
    this.y = _y;
  }

  // Constructor for intermediate states
  public State (int _x, int _y, State _initial, State _goal, int _totalCost, int _function)
  {
    this.x = _x;
    this.y = _y;
    this.function = _function;

    switch (_function)
    {
      case 1: this.goodness = this.euclideanDistance(_goal);
              break;
      case 2: this.goodness = this.manhattanDistance(_goal);
              break;
      case 3: this.goodness = this.euclideanDistance(_goal) + _totalCost;
              break;
      case 4: this.goodness = this.manhattanDistance(_goal) + _totalCost;
              break;
      default: System.out.println("Invalid evaluation function choice.");
               System.exit(0);
    }


  }

  public int getX ()
  {
    return this.x;
  }

  public int getY ()
  {
    return this.y;
  }

  public double getGoodness ()
  {
    return this.goodness;
  }

  public void setGoodness (double _goodness)
  {
    this.goodness = _goodness;
  }

  public int getFunction ()
  {
    return this.function;
  }

  public boolean equals (State _other)
  {
    if (_other.getX() != this.x || _other.getY() != this.y)
      return false;
    else
      return true;
  }

  public double euclideanDistance(State _goal)
  {
    // Cost of the path from current node to goal
    int x = this.x - _goal.getX();
    int y = this.y - _goal.getY();

    return Math.sqrt( (double)((x*x)+(y*y)) );
  }

  public int manhattanDistance(State _goal)
  {
    // Cost of the path from current node to goal
    int x = Math.abs(this.x - _goal.getX());
    int y = Math.abs(this.y - _goal.getY());

    return (x+y);
  }

  public int totalCost(State _initial)
  {
    // Cost of the path found so far from the initial node to current
    int x = Math.abs(this.x- _initial.getX());
    int y = Math.abs(this.x - _initial.getY());

    return (x+y);
  }
}