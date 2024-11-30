package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoSquareTest {

    @Test
    void testConstructor() {
        GoSquare goSquare = new GoSquare();

        assertEquals("Go", goSquare.getName(), "Square name should be 'Go'.");
    }


    @Test
    void testLandOn() {
        Player player = new Player("Henry");
        GoSquare goSquare = new GoSquare();


        goSquare.landOn(player);


        assertEquals(0, player.getPosition(), "Player's position should remain unchanged.");
        assertFalse(player.isInJail(), "Player should not be in jail after landing on GoSquare.");
    }
}