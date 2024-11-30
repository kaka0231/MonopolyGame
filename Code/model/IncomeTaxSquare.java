package model;

import java.io.Serializable;

public class IncomeTaxSquare extends Square implements Serializable {
    public IncomeTaxSquare() {
        super("Income Tax");
    }

    @Override
    public void landOn(Player player) {
        int tax = (int) (player.getMoney() * 0.10);
        tax = (tax / 10) * 10;
        player.deductMoney(tax);
        System.out.println(player.getName() + " lands on Income Tax square.");
        System.out.println(player.getName() + " pays HKD " + tax + " as income tax!");
    }
}

