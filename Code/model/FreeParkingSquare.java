package model;

import java.io.Serializable;

public class FreeParkingSquare extends Square implements Serializable{
    public FreeParkingSquare() {
        super("Free Parking");
    }

    @Override
    public void landOn(Player player) {
        // No effect
        System.out.println(player.getName() + " lands on Free Parking (No effect).");
    }
}