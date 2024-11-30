package model;

import java.io.Serializable;
import java.util.Random;

public class ChanceSquare extends Square implements Serializable{
    private Random random;

    public ChanceSquare() {
        super("Chance");
        this.random = new Random();
    }

    @Override
    public void landOn(Player player) {
        int event = random.nextInt(2);
        if (event == 0) {
            int gain = (random.nextInt(20) + 1) * 10;
            player.addMoney(gain);
            System.out.println(player.getName() + " lands on Chance.");
            System.out.println(player.getName() + " gains HKD " + gain + "!");
        } else {
            int loss = (random.nextInt(30) + 1) * 10;
            player.deductMoney(loss);
            System.out.println(player.getName() + " lands on Chance.");
            System.out.println(player.getName() + " loses HKD " + loss + "!");
            if (player.getMoney() < 0) {
                System.out.println(player.getName() + " is bankrupt!");
            }
        }
    }
}