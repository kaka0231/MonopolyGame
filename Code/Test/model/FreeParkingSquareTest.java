package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreeParkingSquareTest {

    @Test
    void testConstructor() {
        FreeParkingSquare freeParkingSquare = new FreeParkingSquare();


        assertEquals("Free Parking", freeParkingSquare.getName(), "Square name should be 'Free Parking'.");
    }

    @Test
    void testLandOn() {
        Player player = new Player("Henry");
        FreeParkingSquare freeParkingSquare = new FreeParkingSquare();


        freeParkingSquare.landOn(player);


        assertEquals(0, player.getPosition(), "Player's position should remain unchanged after landing on Free Parking.");
        assertFalse(player.isInJail(), "Player should not be in jail after landing on Free Parking.");
        assertEquals(1500, player.getMoney(), "Player's money should remain unchanged after landing on Free Parking.");
    }
}