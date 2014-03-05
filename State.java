import java.lang.Math;
import java.util.LinkedList;

public class State {
  // Fields
  private int x;
  private int y;
  private double goodness;
  private LinkedList<State> children;

  // Constructor
  public State (int _x, int _y) {
    this.x = _x;
    this.y = _y;
  }

  // Constructor for intermediate states
  public void calculateGoodness(int _function, State _goal, int _totalCost) {
    switch (_function) {
      case 1: this.goodness = this.euclideanDistance(_goal);
              break;
      case 2: this.goodness = this.manhattanDistance(_goal);
              break;
      case 3: this.goodness = this.euclideanDistance(_goal) + _totalCost + 1;
              break;
      case 4: this.goodness = this.manhattanDistance(_goal) + _totalCost + 1;
              break;
      default: System.out.println("Invalid evaluation function choice.");
               System.exit(0);
    }
  }

  public LinkedList<State> getChildren () {
    return this.children;
  }

  public void setChildren (LinkedList<State> _children) {
    this.children = _children;
  }

  public int getX () {
    return this.x;
  }

  public int getY () {
    return this.y;
  }

  public double getGoodness () {
    return this.goodness;
  }

  public void setGoodness (double _goodness) {
    this.goodness = _goodness;
  }

  public boolean equals (State _other) {
    if (_other.getX() != this.x || _other.getY() != this.y)
      return false;
    else
      return true;
  }

  public double euclideanDistance(State _goal) {
    // Cost of the path from current node to goal
    int xl = this.x - _goal.getX();
    int yl = this.y - _goal.getY();

    return Math.sqrt( (double)((xl*xl)+(yl*yl)) );
  }

  public int manhattanDistance(State _goal) {
    // Cost of the path from current node to goal
    return Math.abs(this.x - _goal.getX()) + Math.abs(this.y - _goal.getY());
  }

  public void print () {
    System.out.println("Node - x: " + this.x + ", y: " + this.y);
  }
}