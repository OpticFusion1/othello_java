//Simon Gao
import java.util.ArrayList;

public class BoundedGrid implements Grid
{
	private String[][] board;

	//----------------------------------------------------------------------------
	public BoundedGrid(int size)
	{
		//your code here
		board = new String[size][size];
	}
	//----------------------------------------------------------------------------
	//Precondition:
	//Postcondition: Returns the number of rows in this grid.
	public int getNumRows()
	{
		return board.length;
	}

	//----------------------------------------------------------------------------
	//Precondition:
	//Postcondition: Returns the number of columns in this grid.
	public int getNumCols()
	{
		return board[0].length;
	}

	//----------------------------------------------------------------------------
	//Precondition:  loc is not null
	//Postcondition: Returns true if loc is valid in this grid, false otherwise
	public boolean isValid(Location loc)
	{
		if ((loc.getRow() >= 0 && loc.getRow() < board.length) && (loc.getCol() >= 0 && loc.getCol() < board[0].length))
			return true;
		return false;
	}
	//----------------------------------------------------------------------------
	//Precondition:  (1) loc is valid in this grid
	//				 (2) theColor is not null
	//Postcondition:  Puts the String object at loc.
	//				  Returns the object previously at that location.
	public String put(Location loc, String theColor)
	{
		int row = loc.getRow();
		int col = loc.getCol();
		String toReturn = board[row][col];
		
		board[row][col] = theColor;
		return toReturn;
	}
	//----------------------------------------------------------------------------
	//Precondition:  (1) loc is valid in this grid
    //Postcondition: Removes the object at loc.
    //				 returns the object that was removed or null if the location is unoccupied
    public String remove(Location loc)
    {
    	int row = loc.getRow();
    	int col = loc.getCol();   	
    	String toReturn = board[row][col];
 
    	board[row][col] = null;
    	return toReturn;
    }

	//----------------------------------------------------------------------------
	//Precondition:  loc is valid in this grid
	//Postcondition: Returns the String at loc or null if the location is unoccupied.
	public String get(Location loc)
	{
		return board[loc.getRow()][loc.getCol()];
	}
	//----------------------------------------------------------------------------
	//Precondition:
	//Postcondition:  returns an ArrayList of all occupied locations in this grid
    public ArrayList<Location> getOccupiedLocs()
	{
    	ArrayList<Location> occupiedLocs = new ArrayList<Location>();
    	
    	for (int curRow = 0; curRow < board.length; curRow++)
    	{
    		for (int curCol = 0; curCol < board[0].length; curCol++)
    		{
    			if (board[curRow][curCol] != null)
    				occupiedLocs.add(new Location (curRow, curCol));
    		}
    	}
    	return occupiedLocs;
	}

	//----------------------------------------------------------------------------
	//Precondition:  loc is valid in this grid
	//Postcondition: returns an ArrayList of valid locations adjacent to loc in
	//				 all eight compass directions (north, northeast, east, southeast,
	//				 south, southwest,west, and northwest).
	public ArrayList<Location> getValidAdjacentLocations(Location loc)
    {
        ArrayList<Location> validLocs = new ArrayList<Location>();
        
        Location north = loc.getAdjacentLoc(Location.NORTH);
        Location northEast = loc.getAdjacentLoc(Location.NORTHEAST);
        Location east = loc.getAdjacentLoc(Location.EAST);
        Location southEast = loc.getAdjacentLoc(Location.SOUTHEAST);
        Location south = loc.getAdjacentLoc(Location.SOUTH);
        Location southWest = loc.getAdjacentLoc(Location.SOUTHWEST);
        Location west = loc.getAdjacentLoc(Location.WEST);
        Location northWest = loc.getAdjacentLoc(Location.NORTHWEST);
        
        if (isValid(north))
        	validLocs.add(north);
        if (isValid(northEast))
        	validLocs.add(northEast);
        if (isValid(east))
        	validLocs.add(east);
        if (isValid(southEast))
        	validLocs.add(southEast);
        if (isValid(south))
        	validLocs.add(south);
        if (isValid(southWest))
        	validLocs.add(southWest);
        if (isValid(west))
        	validLocs.add(west);
        if (isValid(northWest))
        	validLocs.add(northWest);
        
        return validLocs;
    }

	//----------------------------------------------------------------------------

    //Precondition:  loc is valid in this grid
	//Postcondition: returns an ArrayList of valid empty locations adjacent to loc
	//				 in all eight compass directions (north, northeast, east,
	//				 southeast, south, southwest, west, and northwest).
	public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
    {
		//failed
        ArrayList<Location> emptyLocs = new ArrayList <Location>();
        ArrayList<Location> validLocs = getValidAdjacentLocations(loc);
        Location currentLoc;
        
        for (int i = 0; i < validLocs.size(); i++)
        {
        	currentLoc = validLocs.get(i);
        	if (board[currentLoc.getRow()][currentLoc.getCol()] == null)
        		emptyLocs.add(currentLoc);
        }
        return emptyLocs;
    }

	//----------------------------------------------------------------------------
	//Precondition:	  loc is valid in this grid
	//Postcondition:  returns an ArrayList of valid occupied locations adjacent to
	//				  loc in all eight compass directions (north, northeast, east,
	//				  southeast, south, southwest, west, and northwest).
   	public ArrayList<Location> getOccupiedAdjacentLocs(Location loc)
	{
   		ArrayList<Location> occupiedAdjacentLocs = new ArrayList<Location>();
   		ArrayList<Location> adjacentLocs = getValidAdjacentLocations(loc);
   		ArrayList<Location> occupiedLocs = getOccupiedLocs();
   		Location curAdjacent;
   		Location curOccupied;
   		
   		for (int i = 0; i < adjacentLocs.size(); i++)
   		{
   			curAdjacent = adjacentLocs.get(i);
   			for (int x = 0; x < occupiedLocs.size(); x++)
   			{
   				curOccupied = occupiedLocs.get(x);
   				if (curAdjacent.equals(curOccupied))
   					occupiedAdjacentLocs.add(curAdjacent);
   			}
   		}
   		return occupiedAdjacentLocs;
	}

	//----------------------------------------------------------------------------
	//Postcondition: displays the board and its values onto the screen.
	public void display()
	{
		System.out.println();
		System.out.print(String.format("%4s", " "));
		for (int i = 0; i < board[0].length; i++)
			System.out.print(String.format("%4d", i));

		System.out.println("\n");

		for (int i = 0; i < board[0].length; i++)
		{
			System.out.print(String.format("%4d", i));
			for (int j = 0; j < board.length; j++)
			{
				if (board[i][j] != null)
					System.out.print(String.format("%4s", board[i][j]));
				else
					System.out.print(String.format("%4s", "."));
			}
			System.out.println("\n");
		}

		System.out.println();
	}
	
	//----------------------------------------------------------------------------
	//Precondition: must receive a Location variable and ArrayList
	//Postcondition: returns true if loc is already in the List curLegalMoves, false otherwise
	public static boolean isDuplicate (Location loc, ArrayList<Location> curLegalMoves)
	{
		if (curLegalMoves.size() <= 0)
			return false;
		
		for (int i = 0; i < curLegalMoves.size(); i++)
		{
			Location curLoc = curLegalMoves.get(i);
			if (curLoc.equals(loc))
				return true;
		}
		return false;
	}
}