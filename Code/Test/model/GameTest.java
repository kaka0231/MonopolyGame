package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
class GameTest {

    @Test
    void testAddPlayer() {
        Game game = new Game();
        Player player1 = new Player("Henry");
        Player player2 = new Player("Anna");

        // Add players
        game.addPlayer(player1);
        game.addPlayer(player2);

        // Verify players were added
        assertEquals(2, game.getNumberOfPlayers(), "Number of players should be 2.");
        assertEquals(player1, game.getPlayers().get(0), "First player should be Henry.");
        assertEquals(player2, game.getPlayers().get(1), "Second player should be Anna.");
    }

    @Test
    void testSetupBoard() {
        Game game = new Game();
        Square[] board = game.getBoard();

        // Verify board setup
        assertNotNull(board[0], "Board position 0 should not be null.");
        assertEquals("Go", board[0].getName(), "Square at position 0 should be GoSquare.");
        assertEquals("Central", board[1].getName(), "Square at position 1 should be Central.");
        assertTrue(board[5] instanceof JailSquare, "Square at position 5 should be JailSquare.");
        assertTrue(board[15] instanceof GoToJailSquare, "Square at position 15 should be GoToJailSquare.");
    }

    @Test
    void testGetWinners() {
        Game game = new Game();
        Player player1 = new Player("Henry");
        Player player2 = new Player("Anna");
        player1.setMoney(1500);
        player2.setMoney(1500);

        game.addPlayer(player1);
        game.addPlayer(player2);

        ArrayList<Player> winners = game.getWinners();
        assertEquals(2, winners.size(), "Both players should be winners if they have the same money.");
        assertTrue(winners.contains(player1), "Henry should be one of the winners.");
        assertTrue(winners.contains(player2), "Anna should be one of the winners.");
    }









    @Test
    void testNextTurn() {
        Game game = new Game();
        Player player1 = new Player("Henry");
        Player player2 = new Player("Anna");

        game.addPlayer(player1);
        game.addPlayer(player2);

        // Simulate first turn
        game.nextTurn();
        assertEquals(player2, game.getCurrentPlayer(), "Next turn should set the current player to Anna.");

        // Simulate another turn
        game.nextTurn();
        assertEquals(player1, game.getCurrentPlayer(), "Next turn should set the current player back to Henry.");
    }


}