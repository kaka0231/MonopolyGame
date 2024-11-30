package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertySquareTest {

    @Test
    void getPrice() {
        PropertySquare square = new PropertySquare("Central", 1000, 50);

        // Test initial price
        assertEquals(1000, square.getPrice(), "Initial price should be 1000.");

        // Set new price and verify
        square.setPrice(2000);
        assertEquals(2000, square.getPrice(), "Price should be updated to 2000.");
    }

    @Test
    void getOwner() {
        PropertySquare square = new PropertySquare("Central", 1000, 50);
        Player player = new Player("Henry");

        // Initially no owner
        assertNull(square.getOwner(), "Property should have no owner initially.");
    }

    @Test
    void setOwner() {
        PropertySquare square = new PropertySquare("Central", 1000, 50);
        Player player = new Player("Henry");
        square.setOwner(player);
        assertEquals(player, square.getOwner(), "Owner should be set to Henry.");
    }

    @Test
    void testLandOnPayRent() {
        PropertySquare square = new PropertySquare("Central", 1000, 50);
        Player owner = new Player("Owner");
        Player player = new Player("Henry");

        // Set up initial ownership and balance
        owner.setMoney(2000);
        player.setMoney(1500);
        square.setOwner(owner);

        // Player lands on property owned by another player
        square.landOn(player);

        // Verify rent payment
        assertEquals(1450, player.getMoney(), "Player's money should decrease by the rent amount.");
        assertEquals(2050, owner.getMoney(), "Owner's money should increase by the rent amount.");

    }

    @Test
    void testLandOnPlayerOwnsProperty() {
        PropertySquare square = new PropertySquare("Central", 1000, 50);
        Player player = new Player("Henry");
        player.setMoney(1500);
        square.setOwner(player);

        // Player lands on their own property
        square.landOn(player);

        // Verify no rent is paid
        assertEquals(1500, player.getMoney(), "Player's money should remain unchanged when landing on their own property.");
    }
}