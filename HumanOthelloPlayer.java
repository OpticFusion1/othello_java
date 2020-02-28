//Simon Gao
import java.util.ArrayList;
import java.util.Scanner;

public class HumanOthelloPlayer extends OthelloPlayer
{
	public HumanOthelloPlayer(String inName, String inColor)
	{
		super(inName, inColor);
	}
	
	public Location getMove(ArrayList<Location> legalLocs)
	{
		Location toReturn = null;
		Location inputLoc;
		Scanner input = new Scanner(System.in);
		int row;
		int col;
		
		if (legalLocs.size() > 0)
		{
			//do a loop until a valid move is entered
			do
			{
				System.out.print("Enter row: ");
				row = input.nextInt();
				System.out.print("Enter col: ");
				col = input.nextInt();
				inputLoc = new Location(row, col);
				
				for (int i = 0; i < legalLocs.size(); i++)
				{
					if (legalLocs.get(i).equals(inputLoc))
						toReturn = new Location(row, col);
				}
				
				if (toReturn == null)
					System.out.println("Invalid move. Please re-enter");
			}	while (toReturn == null);		//while next move is not valid
		}
		return toReturn;
	}
}
