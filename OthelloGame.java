//Simon Gao
import java.util.ArrayList;

public class OthelloGame
{
	private OthelloPlayer humanPlayer;		//human Othello player
	private OthelloPlayer compPlayer;		//computer Othello player
	private Grid board;						//board

	//----------------------------------------------------------------------------------
	public OthelloGame()
	{
	
		//testing entire game with a 4 x 4
		//board = new BoundedGrid(4);

		//testing entire game with an 8 x8 instead of 4 x 4. Try this after 4 x 4 works.
		board = new BoundedGrid(8);
		
		//testing computer vs computer
		humanPlayer = new StupidComputerPlayer("Computer2", "B");
		//humanPlayer = new HumanOthelloPlayer("Human", "B");
		compPlayer = new StupidComputerPlayer("Computer", "W");
		
		initializeBoard();
	}
	//----------------------------------------------------------------------------------
	//Description:  This method places the "B"'s and "W"'s in the appropriate 
	// 				place on the game board. Pretend you don't know the size of 
	// 				the board - i.e. write generically.

	//Postcondition: "B"'s and "W"'s are placed in the appropriate place on the
	//				 board.

	private void initializeBoard()
	{
		int middle = (board.getNumRows() - 1) / 2 ;
		
		board.put(new Location(middle, middle), "B");
		board.put(new Location(middle, middle + 1), "W");
		
		board.put(new Location(middle + 1, middle), "W");
		board.put(new Location(middle + 1, middle + 1), "B");
	}
	//----------------------------------------------------------------------------------
	/*
	The playGame method (below) has the players alternate moves until the board is filled or both
	players have no legal moves.  A winner is then declared.

	When it's the human's turn, all legal moves are displayed on the screen.  If there are legal
	moves, the human is asked for a move (in another method).  The move is made and the board is
	reconfigured according to the rules of the game.  If there are no legal moves, display a message
	stating there are no legal moves. Switch the player.

	When it's the computer's turn, all legal moves are displayed on the screen.  If there are legal
	moves, a random move is selected (not in this method).   The move is made and the board is
	reconfigured according to the rules of the game.  If there are no legal moves display a message
	stating there are no legal moves. Switch the player.

	This method calls other methods to help do its job.
	*/

	public void playGame()
	{	
		//your local variables that are needed go here.
		ArrayList<Location> curColorLegalLocs;
		OthelloPlayer curPlayer;
		OthelloPlayer winner = null;
		Location nextMove;
		int playerTurn = (int)(Math.random() * 2);	//0 represents computerPlayer's turn; 1 represents humanPlayer's turn
		int humanPieces = 0;
		int computerPieces = 0;
		
		while (!(gameIsOver()))	//keep playing while the game is not over
		{
			board.display();
			
			if (playerTurn == 0)
				curPlayer = compPlayer;
			else
				curPlayer = humanPlayer;
			
			System.out.println(curPlayer.getName() + " - your turn.");
			curColorLegalLocs = getLegalMoves(curPlayer.getColor());
			System.out.println("legalMoves: " + curColorLegalLocs);
			
			nextMove = curPlayer.getMove(curColorLegalLocs);
			
			if (nextMove == null)
			{
				System.out.println("There are no moves possible for " + curPlayer.getName());
			}
			else
			{
				System.out.println();
				System.out.println("MOVING TO: " + nextMove);
				updateBoard(nextMove, curPlayer.getColor());
			}
			
			if (gameIsOver())
			{
				humanPieces = numberOfPieces(humanPlayer.getColor());
				computerPieces = numberOfPieces(compPlayer.getColor());
				
				if (humanPieces > computerPieces)
					winner = humanPlayer;
				else if (computerPieces > humanPieces)
					winner = compPlayer;
				else
					winner = null;
			}
			else
			{
				if (playerTurn == 0)
					playerTurn++;
				else
					playerTurn--;
			}
		}
		board.display();
		if (winner == null)
			System.out.println("The game ended in a tie!");
		else
			System.out.println(winner.getName() + " wins!");
		
		System.out.println();
		System.out.println("Process completed.");
	}
	//----------------------------------------------------------------------------------
	public ArrayList<Location> getLegalMoves(String curColor)
	{
		ArrayList<Location> legalMoves = new ArrayList<Location>();
		ArrayList<Location> occupiedLocs = board.getOccupiedLocs();
		
		for (int i = 0; i < occupiedLocs.size(); i++)
		{
			Location curLoc = occupiedLocs.get(i);
			if (board.get(curLoc).equals(curColor))
			{
				ArrayList<Location> occupiedAdjacents = board.getOccupiedAdjacentLocs(curLoc);
				
				for (int x = 0; x < occupiedAdjacents.size(); x++)
				{
					Location adjacentLoc = occupiedAdjacents.get(x);
					String adjacentLocValue = board.get(adjacentLoc);
					
					if (!(adjacentLocValue.equals(curColor)))
					{
						int direction = curLoc.getDirectionToward(adjacentLoc);
						do
						{
							adjacentLoc = adjacentLoc.getAdjacentLoc(direction);
						}	while ((board.isValid(adjacentLoc)) &&  (board.get(adjacentLoc) != null) && (!(board.get(adjacentLoc).equals(curColor))));
						
						if ((board.isValid(adjacentLoc)) && (board.get(adjacentLoc) == null))
						{
								boolean isDuplicate = BoundedGrid.isDuplicate(adjacentLoc, legalMoves);
								if (!isDuplicate)
									legalMoves.add(adjacentLoc);
						}
					}
				}
			}
		}
		return legalMoves;
	}
	//----------------------------------------------------------------------------------
	public void updateBoard(Location nextMove, String targetString)
	{
		board.put(nextMove, targetString);
		
		ArrayList<Location> adjacentLocs = board.getOccupiedAdjacentLocs(nextMove);
		
		for (int i = 0; i < adjacentLocs.size(); i++)
		{
			Location curLoc = adjacentLocs.get(i);
			if (!(board.get(curLoc).equals(targetString)))
			{
				int direction = nextMove.getDirectionToward(curLoc);
				do
				{
					curLoc = curLoc.getAdjacentLoc(direction);
				}	while (board.isValid(curLoc) && board.get(curLoc) != null && !(board.get(curLoc).equals(targetString)));
				
				if (board.isValid(curLoc) && board.get(curLoc) != null && board.get(curLoc).equals(targetString))
				{
					flipOpponentPieces(direction, targetString, nextMove, curLoc);
				}
			}
		}
	}
	//----------------------------------------------------------------------------------
	//Precondition:	direction is one of the eight cordial directions, targetColor is one of the colors on the board, startLoc and endLoc are valid and not null
	//Postcondition: flips pieces between startLoc and endLoc to targetColor
	public void flipOpponentPieces(int direction, String targetColor, Location startLoc, Location endLoc)
	{
		Location nextLoc = startLoc.getAdjacentLoc(direction);
		
		while (!(nextLoc.equals(endLoc)))
		{
			board.put(nextLoc, targetColor);
			nextLoc = nextLoc.getAdjacentLoc(direction);
		}
	}
	//----------------------------------------------------------------------------------
	//Precondition:	none
	//Postcondition: returns true if the entire board is occupied OR both players do no have any legal moves; return false otherwise
	public boolean gameIsOver()
	{
		ArrayList<Location> humanLegalMoves = getLegalMoves(humanPlayer.getColor());
		ArrayList<Location> compLegalMoves = getLegalMoves(compPlayer.getColor());
		ArrayList<Location> occupiedLocs = board.getOccupiedLocs();
		
		if ((occupiedLocs.size() == board.getNumRows() * board.getNumCols()) || (humanLegalMoves.size() <= 0 && compLegalMoves.size() <= 0))
			return true;
		return false;
	}
	//----------------------------------------------------------------------------------
	//Precondition: receive a one of the colors on the board in form of a String
	//Postcondition: returns the number of pieces that are targetColor
	public int numberOfPieces (String targetColor)
	{
		ArrayList<Location> occupiedLocs = board.getOccupiedLocs();
		int amount = 0;
		
		for (int i = 0; i < occupiedLocs.size(); i++)
		{
			if (board.get(occupiedLocs.get(i)).equals(targetColor))
			{
				amount++;
			}
		}
		return amount;
	}
}
