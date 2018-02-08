package com.tictactoe;

/**
 * Main class
 */
public class TicTacToe {

	public static void main(String[] args) {
		Board board = new Board();
		board.showInstructions();
		board.playGame(Constants.HUMAN_PLAYER); //Always the player HUMAN begins
	}

}
