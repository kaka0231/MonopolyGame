package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void move() {
        Player player = new Player("Henry");

        // Move within the board range
        player.move(5);
        assertEquals(5, player.getPosition());

        // Move past the board range (simulating passing through Go)
        player.move(17);
        assertEquals(2, player.getPosition());
        assertEquals(3000, player.getMoney());
    }

    @Test
    void deductMoney() {
        Player player = new Player("Henry");

        // Add money
        player.addMoney(500);
        assertEquals(2000, player.getMoney());
    }

    @Test
    void addMoney() {
        Player player = new Player("Henry");

        // Add money
        player.addMoney(500);
        assertEquals(2000, player.getMoney(), "Player money should be 2000 after adding 500.");
    }

    @Test
    void JailStatus() {
        Player player = new Player("Henry");
        assertFalse(player.isInJail());
        player.setInJail(true);
        assertTrue(player.isInJail());
    }

    @Test
    void SetAndGetName() {
        Player player = new Player("Henry");

        // Verify initial name
        assertEquals("Henry", player.getName());
    }

    @Test
    void SetAndGetMoney() {
        Player player = new Player("Henry");

        // Set and get money
        player.setMoney(3000);
        assertEquals(3000, player.getMoney());
    }

    @Test
    void testSetAndGetPosition() {
        Player player = new Player("Henry");

        // Set and get position
        player.setPosition(10);
        assertEquals(10, player.getPosition());
    }
}