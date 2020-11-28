package program;

import players.Player;

public class Game {
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	

	public Game() {
		launchGame();
	}
	
	public void launchGame() {
		
	}

}
