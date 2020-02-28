//Simon Gao
import java.util.ArrayList;

public abstract class OthelloPlayer 
{
	private String name;
	private String color;
	
	public OthelloPlayer(String inName, String inColor)
	{
		name = inName;
		color = inColor;
	}
	
	public abstract Location getMove(ArrayList<Location> legalLocs);
	
	public String getName()
	{
		return name;
	}
	
	public String getColor()
	{
		return color;
	}
}
