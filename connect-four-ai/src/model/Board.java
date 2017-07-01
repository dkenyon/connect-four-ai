package model;

import java.util.Scanner;

public class Board {
	private char[][] myBoard;

	
	public static void main(String[] theArgs) throws InterruptedException {
		System.out.println("CHECKERS");
		char x = 'x';
		char o = 'o';
		char currentMove = 'x';
		Scanner scanner = new Scanner(System.in);
		Board board = new Board();
		while(!(board.hasWon(x) || board.hasWon(o))) { 
			board.printBoard();
			
			int nextMove = scanner.nextInt();
			if (currentMove == 'x') {
				board.move(x, nextMove);
				currentMove = o;
			} else {
				board.move(o, nextMove);
				currentMove = x;
			}
		}
		board.printBoard();
		board.hasWon(x);
		board.hasWon(o);
		scanner.close();
	}
	
	public Board() {
		myBoard = new char[6][7];
		initialize();
	}

	public void move(char thePlayer, int theColumn) {
		if (!isValidMove(theColumn)) {
			System.out.println("CAN'T MOVE IN COLUMN " + theColumn + "; MAKE ANOTHER MOVE");
		} else {
			dropPiece(thePlayer, theColumn);
		}
	}
	
	
	/*
	 * ****************************
	 * NEEDS TO HAVE HORIZONTAL ROW CHECK FIXED
	 * ****************************
	 * 
	 */
	public boolean hasWon(char thePlayer) {
		for (int i= 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if ((myBoard[i][j] == thePlayer && myBoard[i][j + 1] == thePlayer && myBoard[i][j + 2] == thePlayer && myBoard[i][j + 3] == thePlayer) || // horizontal
						(myBoard[i][j] == thePlayer && myBoard[i + 1][j] == thePlayer && myBoard[i + 2][j] == thePlayer && myBoard[i + 3][j] == thePlayer) || // vertical
						(myBoard[i][j] == thePlayer && myBoard[i + 1][j + 1] == thePlayer && myBoard[i + 2][j + 2] == thePlayer && myBoard[i + 3][j + 3] == thePlayer)) { // diag top left -> bottom right
					System.out.println("Player '" + thePlayer + "' wins");
					return true;
				}
			}
		}
		
		//special check for diag win condition (bottom left -> top right)
		for (int i = 5; i <= 2; i--) {
			for (int j = 0; j < 4; j++) {
				if (myBoard[i][j] == thePlayer && myBoard[i - 1][j + 1] == thePlayer && myBoard[i - 2][j + 2] == thePlayer && myBoard[i - 3][j + 3] == thePlayer) {
					System.out.println("Player '" + thePlayer + "' wins");
				}
			}
		}
		return false;
	}
	
	private void dropPiece(char thePlayer, int theColumn) {
		for (int i  = myBoard.length -1 ; i >= 0; i--) { // start from bottom of board and move up
			if (myBoard[i][theColumn] == ' ') {
				myBoard[i][theColumn] = thePlayer;
				return;
			}
		}
	}
	
	/**
	 * Checks if the passed column is able to be moved in.
	 * @param theColumn the column to check
	 * @return whether or not the move is valid
	 */
	public boolean isValidMove(int theColumn) {
		if (myBoard[0][theColumn] == ' ') {
			return true;
		}
		return false;
	}
	
	/**
	 * Prints the board's current state to the console.
	 * @throws InterruptedException 
	 */
	public void printBoard() throws InterruptedException {
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
		for (int i = 0; i < myBoard.length; i++) {
			System.out.print("\t\t\t\t| ");
			for (int j = 0; j < myBoard[1].length; j++) {
				System.out.print(myBoard[i][j] + " ");
			}
			System.out.print("|");
			System.out.println();
		}
		System.out.println("\t\t\t\t  0 1 2 3 4 5 6");
		System.out.println();
		System.out.println();
	}
	
	/**
	 * Helper method to fill the board with blank characters.	 
	 */
	private void initialize() {
		for (int i = 0; i < myBoard.length; i++) {
			for (int j = 0; j < myBoard[1].length; j++) {
				myBoard[i][j] = ' ';
			}
		}
	}
}
