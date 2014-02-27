import java.lang.Math;

public class State
{
  // Fields
  private int x;
  private int y;
  private double goodness;

  // Constructor for initial and goal states
  public State (int _x, int _y)
  {
    this.x = _x;
    this.y = _y;
  }

  // Constructor for intermediate states
  public State (int _x, int _y, State _initial, State _goal, int _totalCost)
  {
    this.x = _x;
    this.y = _y;

    this.goodness = this.euclideanDistance(_goal);
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
    int x = this.x - _goal.getX();
    int y = this.y - _goal.getY();

    return Math.abs(x+y);
  }

  public int totalCost(State _initial)
  {
    // Cost of the path found so far from the initial node to current
    int x = this.x- _initial.getX();
    int y = this.x - _initial.getY();

    return 2;
  }
}