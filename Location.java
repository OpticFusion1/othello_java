//Simon Gao 

public class Location 
{
	private int row;
	private int col;

	//COMPASS DIRECTIONS
	public static final int NORTH = 0;
	public static final int EAST = 90;
	public static final int SOUTH = 180;
	public static final int WEST = 270;
	public static final int NORTHEAST = 45;
	public static final int SOUTHEAST = 135;
	public static final int SOUTHWEST = 225;
	public static final int NORTHWEST = 315;

	public Location(int inRow, int inCol)
	{
		row = inRow;
		col = inCol;
	}


	//----------------------------------------------------------------------
	//Description:  Gets the row coordinate.
	//Postcondition:  return the row of this location

	public int getRow()
	{
		return row;
	}


	//----------------------------------------------------------------------
	//Description:  Gets the column coordinate.
	//Postcondition: returns the column for this Location
	public int getCol()
	{
		return col;
	}


	//----------------------------------------------------------------------
	//Description:  Gets the adjacent location in any one of the eight
	//			    compass directions.
	//			    It is possible to return a Location that is out of bounds.
	//Precondition:  dir is one of the 8 compass directions.
	//Postcondition: returns the adjacent location in the direction that is closest to dir
	public Location getAdjacentLoc(int dir)
	{
		if (dir == NORTH)
			return new Location(row - 1, col);
		else if (dir == NORTHEAST)
			return new Location(row - 1, col + 1);
		else if (dir == EAST)
			return new Location(row, col + 1);
		else if (dir == SOUTHEAST)
			return new Location(row + 1, col + 1);
		else if (dir == SOUTH)
			return new Location(row + 1, col);
		else if (dir == SOUTHWEST)
			return new Location(row + 1, col - 1);
		else if (dir == WEST)
			return new Location(row, col - 1);
		else 
			return new Location (row - 1, col - 1);
	}

	//----------------------------------------------------------------------
	//Description: Returns the direction from this location toward another
	//             location. The direction is one of the eight compass
	//             directions.
	//Precondition:  target - a location that is different from this location.
	//			 	 target is an adjacent Location.
	//				 target is valid in the matrix
	//Postcondition: returns the closest compass direction from this location
	//				 toward target
	public int getDirectionToward(Location target)
	{
		int targetRow = target.getRow();
		int targetCol = target.getCol();
		
		if (targetRow == row - 1 && targetCol == col)
			return NORTH;
		else if (targetRow == row - 1 && targetCol == col + 1)
			return NORTHEAST;
		else if (targetRow == row && targetCol == col + 1)
			return EAST;
		else if (targetRow == row + 1 && targetCol == col + 1)
			return SOUTHEAST;
		else if (targetRow == row + 1 && targetCol == col)
			return SOUTH;
		else if (targetRow == row + 1 && targetCol == col - 1)
			return SOUTHWEST;
		else if (targetRow == row && targetCol == col - 1)
			return WEST;
		else
			return NORTHWEST;
	}

	//----------------------------------------------------------------------
	//override .equals method
	//Description: Determines if this Location is equal to otherLoc
	//Precondition: otherLoc is a Location object and is valid in the matrix
	//Postcondition: return true if otherLoc is a Location with the same row
	//               and column as this location; false otherwise.
	public boolean equals(Object otherLoc)
	{
		Location other = (Location) otherLoc;
		if (other.getRow() == row && other.getCol() == col)
		{
			return true;
		}
		return false;
	}


	//----------------------------------------------------------------------
	//Postcondition: returns a String with the row and col of this location
	//				 in the format: (row, col)
	public String toString()
	{
		return new String("(" + row + ", " + col + ")");
	}
	
}