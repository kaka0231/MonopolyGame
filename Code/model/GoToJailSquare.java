package model;

import java.io.Serializable;

public class GoToJailSquare extends Square implements Serializable{
    public GoToJailSquare() {
        super("Go to Jail");
    }

    @Override
    public void landOn(Player player) {
        // Move the player to jail
        player.setPosition(5); // Assuming 5 indicates jail
        System.out.println(player.getName() + " lands on Go toJail square.");
        player.setInJail(true);
        System.out.println(player.getName() + " is now in jail!");
        System.out.println("You have 3 turns to roll doubles to get out of jail or pay HKD 150 to leave jail.");
    }
}