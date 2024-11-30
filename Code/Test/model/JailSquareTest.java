package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JailSquareTest {

    @Test
    void testHandleJailTurn_RollDoubles() {
        Player player = new Player("Henry");
        player.setInJail(true);

        JailSquare jailSquare = new JailSquare();

        // Test rolling doubles to get out of jail
        boolean result = jailSquare.handleJailTurn(player, 3, 3);

        // Verify player is no longer in jail
        assertTrue(result, "Player should leave jail when rolling doubles.");
        assertFalse(player.isInJail(), "Player should no longer be in jail.");
        assertEquals(0, player.getJailTurns(), "Jail turns should reset to 0.");
    }

    @Test

    void testHandleJailTurn_MaxTurns() {
        Player player = new Player("Henry");
        player.setInJail(true);

        JailSquare jailSquare = new JailSquare();

        // Simulate three turns in jail without rolling doubles
        jailSquare.handleJailTurn(player, 1, 2);
        jailSquare.handleJailTurn(player, 2, 3);
        boolean result = jailSquare.handleJailTurn(player, 4, 5);

        // Verify player is forced to pay and leave jail
        assertTrue(result, "Player should leave jail after three turns without rolling doubles.");
        assertFalse(player.isInJail(), "Player should no longer be in jail.");
        assertEquals(0, player.getJailTurns(), "Jail turns should reset to 0.");
        assertEquals(1350, player.getMoney(), "Player should have HKD 1350 after paying the jail fee.");
    }

    @Test
    void testHandleJailTurn_StayInJail() {
        Player player = new Player("Henry");
        player.setInJail(true);

        JailSquare jailSquare = new JailSquare();

        // Simulate one turn in jail without rolling doubles
        boolean result = jailSquare.handleJailTurn(player, 1, 2);

        // Verify player is still in jail
        assertFalse(result, "Player should stay in jail if not rolling doubles and turns remain.");
        assertTrue(player.isInJail(), "Player should still be in jail.");
        assertEquals(1, player.getJailTurns(), "Jail turns should increment to 1.");
    }

    @Test
    void testPayToLeaveJail_WithEnoughMoney() {
        Player player = new Player("Henry");
        player.setInJail(true);

        JailSquare jailSquare = new JailSquare();

        // Test paying to leave jail
        jailSquare.payToLeaveJail(player);

        // Verify player is no longer in jail
        assertFalse(player.isInJail(), "Player should no longer be in jail after paying.");
        assertEquals(1350, player.getMoney(), "Player should have HKD 1350 after paying the jail fee.");
        assertEquals(0, player.getJailTurns(), "Jail turns should reset to 0.");
    }

    @Test
    void testPayToLeaveJail_NotEnoughMoney() {
        Player player = new Player("Henry");
        player.setInJail(true);
        player.setMoney(100); // Set insufficient funds

        JailSquare jailSquare = new JailSquare();

        // Test trying to pay without enough money
        jailSquare.payToLeaveJail(player);

        // Verify player is still in jail
        assertTrue(player.isInJail(), "Player should still be in jail if they cannot pay.");
        assertEquals(100, player.getMoney(), "Player's money should remain unchanged.");
    }


    @Test
    void testLandOn() {
        Player player = new Player("Henry");
        JailSquare jailSquare = new JailSquare();

        // Test landing on jail square (just visiting)
        jailSquare.landOn(player);

        // Verify player's state remains unchanged
        assertEquals(0, player.getJailTurns(), "Jail turns should remain 0.");
        assertFalse(player.isInJail(), "Player should not be in jail when just visiting.");
    }
}