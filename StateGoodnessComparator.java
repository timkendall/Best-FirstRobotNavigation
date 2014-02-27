import java.util.Comparator;

public class StateGoodnessComparator implements Comparator<State>
{
  @Override
  public int compare (State _a, State _b)
  {
    // Check if either state is null

    if (_a.getGoodness() < _b.getGoodness())
    {
      return -1;
    }
    if (_a.getGoodness() > _b.getGoodness())
    {
      return 1;
    }
    return 0;
  }
}