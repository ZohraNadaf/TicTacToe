package com.tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Board {
	private char[][] board;
	private int[] moves = new int[Constants.SIDE*Constants.SIDE];

	/**
	 * Initializing the board
	 */
	public Board() {
		setBoard(new char[Constants.SIDE][Constants.SIDE]);
		for(int row = 0; row < board.length; row++){
			Arrays.fill(board[row], ' ');
		}
		
		for (int cellNum = 0; cellNum < Constants.SIDE*Constants.SIDE; cellNum++){
			moves[cellNum] = cellNum;
		}	
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}
	
	/**
	 * Display the board
	 */
	public void displayBoard(){
		for(int row = 0; row < board.length; row++){
			for(int col = 0; col < board[row].length; col++){
				System.out.print("   "+board[row][col]);
				if(col == 0 || col == 1)
					System.out.print("   |");
			}
			if(row == 0 || row == 1)
				System.out.print("\n-------------------------\n");
		}
		System.out.println("\n");
	}
	
	/**
	 * Show instructions as to which cell to enter
	 */
	public void showInstructions(){
		System.out.println("Enter the cell numbered below");
		int side = 1;
		for(int row = 0; row < board.length; row++){
			for(int col = 0; col < board[row].length; col++){
				System.out.print("    "+side++);
				if(col == 0 || col == 1)
					System.out.print("   |");
			}
			if(row == 0 || row == 1)
				System.out.print("\n-------------------------\n");
		}
		System.out.println("\n");
	}
	
	/**
	 *  To enter the value into the matrix
	 * @param value
	 * @param row
	 * @param col
	 */
	public void makeMove(char value,int row,int col){
		board[row][col] = value; 
	}

	/**
	 * Play the game with initial player: HUMAN
	 * @param player
	 */
	public void playGame(int player) {
		int moveIndex = 0, x, y;
		Scanner in = new Scanner(System.in);
		
		while(gameOver() == false && moveIndex != Constants.SIDE*Constants.SIDE){
			if (player == Constants.COMPUTER_PLAYER){
				moveIndex = new Random().nextInt(9) + 1;
				x = moves[moveIndex] / Constants.SIDE;
	            y = moves[moveIndex] % Constants.SIDE;
	            System.out.println("COMPUTER has entered a " + Constants.COMPUTER_MOVE +" in " + (moves[moveIndex]+1));
	            if(board[x][y] == ' '){
	            	makeMove(Constants.COMPUTER_MOVE,x,y);
	            }
	            else{
	            	continue;
	            }
	            displayBoard();
	            player = Constants.HUMAN_PLAYER;
	        } else if (player == Constants.HUMAN_PLAYER){
	        	
	        	System.out.println("Hey HUMAN..!! Enter the cell");
	        	moveIndex = in.nextInt() - 1;
	        	x = moves[moveIndex] / Constants.SIDE;
	            y = moves[moveIndex] % Constants.SIDE;
	            if(board[x][y] == ' '){
	            	makeMove(Constants.HUMAN_MOVE,x,y);
	            }
	            else{
	            	continue;
	            }
	         	System.out.println("HUMAN has entered a " + Constants.HUMAN_MOVE +" in " + (moves[moveIndex]+1));
	            displayBoard();
	            player = Constants.COMPUTER_PLAYER;
	        }
		}
		if (gameOver() == false && moveIndex == Constants.SIDE * Constants.SIDE)
			System.out.println("It's a draw\n");
		else{
			// Toggling the user to declare the actual
	        // winner
	        if (player == Constants.COMPUTER_PLAYER)
	        	player = Constants.HUMAN_PLAYER;
	        else if (player == Constants.HUMAN_PLAYER)
	        	player = Constants.COMPUTER_PLAYER;
			isWinner(player);
		}
		in.close();
		return;
	}
	
	/**
	 * To declare the winner of the game
	 * @param turn
	 */
	public void isWinner(int player){
	    if (player == Constants.COMPUTER_PLAYER)
	        System.out.println("COMPUTER has won\n");
	    else
	    	System.out.println("HUMAN has won\n");
	    return;
	}
	
	/**
	 * Returns true if if game is over
	 * @return
	 */
	public boolean gameOver(){
		return(isRowSame() || isColSame() || isDiagonalSame());
	}
	
	/**
	 * Returns true if a row has similar elements 
	 * @return
	 */
	public boolean isRowSame(){
	    for (int row = 0; row < Constants.SIDE; row++){
	        if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != ' ')
	            return true;
	    }
	    return false;
	}
	
	/**
	 * Returns true if a column has similar elements 
	 * @return
	 */
	public boolean isColSame(){
		for (int col = 0; col< Constants.SIDE; col++){
			if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != ' ')
				return true;
		}
		return false;
	}
	
	/**
	 * Returns true if a diagonal has similar elements 
	 * @return
	 */
	public boolean isDiagonalSame(){
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ')
			return true ;
			
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')
			return true;
		return false;
	}
}
