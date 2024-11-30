package model;

import java.io.Serializable;

public class GoSquare extends Square implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public GoSquare() {
        super("Go");
    }

    @Override
    public void landOn(Player player) {
        System.out.println(player.getName() + " lands on Go.");
    }
}