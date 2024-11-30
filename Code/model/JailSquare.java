package model;

import java.io.Serializable;

public class JailSquare extends Square implements Serializable {
    private static final int JAIL_FEE = 150;
    private static final int MAX_JAIL_TURNS = 3;

    public JailSquare() {
        super("Jail");
    }

    public boolean handleJailTurn(Player player, int dice1, int dice2) {
        player.incrementJailTurns();
        boolean isDoubles = (dice1 == dice2);
        
        int remainingTurns = MAX_JAIL_TURNS - player.getJailTurns();

        if (player.getJailTurns() >= MAX_JAIL_TURNS && !isDoubles) {
            System.out.println(player.getName() + " must pay HKD " + JAIL_FEE + " after three turns in jail.");
            player.deductMoney(JAIL_FEE);
            player.setInJail(false);
            player.resetJailTurns();
            return true;
        } else {
            if (isDoubles) {
                System.out.println(player.getName() + " rolled doubles and can leave jail!");
                player.setInJail(false);
                player.resetJailTurns();
                return true; // Player successfully leaves jail
            } else if (remainingTurns > 0) {
                System.out.println(player.getName() + " rolled " + dice1 + " and " + dice2 + " and must stay in jail for " + remainingTurns + " more turns.");
            }
        }
        return false; // Return false if the player is still in jail
    }

    public void payToLeaveJail(Player player) {
        if (player.getMoney() >= JAIL_FEE) {
            player.deductMoney(JAIL_FEE);
            player.setInJail(false);
            player.resetJailTurns();
            System.out.println(player.getName() + " paid HKD " + JAIL_FEE + " to get out of jail.");
        } else {
            System.out.println(player.getName() + " doesn't have enough money to pay the jail fine.");
        }
    }

    @Override
    public void landOn(Player player) {
        //when a player lands on jail square (just visiting)
        System.out.println(player.getName() + " lands on Visiting Jail.");
    }
} 