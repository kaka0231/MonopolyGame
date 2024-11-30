package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoToJailSquareTest {

    @Test
    void testConstructor() {
        GoToJailSquare goToJailSquare = new GoToJailSquare();

        // 驗證名稱是否正確
        assertEquals("Go to Jail", goToJailSquare.getName(), "Square name should be 'Go to Jail'.");
    }

    @Test
    void testLandOn_PlayerSentToJail() {
        Player player = new Player("Henry");
        GoToJailSquare goToJailSquare = new GoToJailSquare();


        assertFalse(player.isInJail(), "Player should not be in jail initially.");
        assertNotEquals(5, player.getPosition(), "Player's position should not be 5 initially.");


        goToJailSquare.landOn(player);


        assertEquals(5, player.getPosition(), "Player should be moved to position 5 (jail).");
        assertTrue(player.isInJail(), "Player should be in jail after landing on GoToJailSquare.");
    }

    @Test
    void testLandOn_PlayerStateUnchangedBeforeLanding() {
        Player player = new Player("Henry");
        GoToJailSquare goToJailSquare = new GoToJailSquare();


        assertFalse(player.isInJail(), "Player should not be in jail before landing.");
        assertEquals(0, player.getPosition(), "Player's initial position should be 0.");
    }
}