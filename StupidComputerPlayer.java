//Simon Gao
import java.util.ArrayList;

public class StupidComputerPlayer extends OthelloPlayer
{
	public StupidComputerPlayer(String inName, String inColor)
	{
		super(inName, inColor);
	}
	
	public Location getMove(ArrayList<Location> legalLocs)
	{
		Location toReturn = null;
		if (legalLocs.size() > 0)
		{
			int index = (int) (Math.random() * legalLocs.size());
			toReturn = legalLocs.get(index);
		}
		return toReturn;
	}
}
