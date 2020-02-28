//Simon Gao 
import java.util.ArrayList;

public interface Grid
{
	//Precondition:
	//Postcondition: Returns the number of rows in this grid.
	public abstract int getNumRows();


	//Precondition:
	//Postcondition: Returns the number of columns in this grid.
	public abstract int getNumCols();


	//Precondition:  loc is not null
	//Postcondition: Returns true if loc is valid in this grid, false otherwise
	public abstract boolean isValid(Location loc);


	//Precondition:  (1) loc is valid in this grid
	//				 (2) theColor is not null
	//Postcondition:  Puts an object at a given location in this grid.  Returns the object
	//				  previously at that location.
	public abstract String put(Location loc, String theColor);



    //Precondition:  (1) loc is valid in this grid
    //Postcondition: Removes the object at a given location from this grid.
    //				 returns the object that was removed or null if the location is unoccupied
    public String remove(Location loc);



	//Precondition:  loc is valid in this grid
	//Postcondition: Returns the String at a given location in this grid or null if the
	//				 location is unoccupied.
	public abstract String get(Location loc);



	//Precondition:
	//Postcondition:  gets the locations in this grid that contain objects.
    //				  returns an array list of all occupied locations in this grid
	public abstract ArrayList<Location> getOccupiedLocs();



    //Precondition:  loc is valid in this grid
	//Postcondition: Gets the valid locations adjacent to a given location in all eight
    //				 compass directions (north, northeast, east, southeast, south, southwest,
    //				 west, and northwest).
    public abstract ArrayList<Location> getValidAdjacentLocations(Location loc);




    //Precondition:  loc is valid in this grid
	//Postcondition: Gets the valid empty locations adjacent to a given location in all eight
    //				 compass directions (north, northeast, east, southeast, south, southwest,
    //				 west, and northwest).
    public abstract ArrayList<Location> getEmptyAdjacentLocations(Location loc);



	//Precondition:	  loc is valid in this grid
	//Postcondition:  gets the valid occupied locations adjacent to a given location in all
    //				  eight compass directions (north, northeast, east, southeast, south,
    //				  southwest, west, and northwest).
    //				  returns an array list of the valid occupied locations adjacent to loc
	public abstract ArrayList<Location> getOccupiedAdjacentLocs(Location loc);

	//Displays the board 
	public abstract void display();

}