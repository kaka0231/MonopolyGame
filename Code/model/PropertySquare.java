package model;

import java.io.Serializable;
import java.util.Scanner;

public class PropertySquare extends Square implements Serializable {
    private static final long serialVersionUID = 1L;
    private int price;
    private int rent;
    private Player owner;
    private transient Scanner scanner;

    public PropertySquare(String name, int price, int rent) {
        super(name);
        this.price = price;
        this.rent = rent;
        this.owner = null;
        this.scanner = new Scanner(System.in);
    }

    public PropertySquare(String name) {
        super(name);
        this.scanner = new Scanner(System.in);
    }
    
    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setPrice(int price) {
        this.price = price; 
    }

    @Override
    public void landOn(Player player) {
        // Initialize scanner only if it's not already initialized
        if (this.scanner == null) {
            this.scanner = new Scanner(System.in);
        }

        if (player == null) {
            System.out.println("Error: Player is null.");
            return;
        }

        System.out.println(player.getName() + " lands on " + name + ".");
        if (owner == null) {
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Your current balance: HKD " + player.getMoney());
                System.out.println("Would you like to buy " + name + " for HKD " + price + "? (Y/N)");

                String input = scanner.next().toLowerCase();

                if (input.equals("y")) {
                    player.deductMoney(price);
                    owner = player;
                    System.out.println(player.getName() + " buys " + name + " for HKD " + price + "!");
                    validInput = true;
                } else if (input.equals("n")) {
                    System.out.println(player.getName() + " decides not to buy " + name);
                    validInput = true;
                } else {
                    System.out.println("Invalid input! Please enter Y or N.");
                }
            }
        } else if (owner != player) {
            // Pay rent to the owner
            player.deductMoney(rent);
            owner.addMoney(rent);
            System.out.println(player.getName() + " pays HKD " + rent + " rent to " + owner.getName() + " for " + name + "!");
        }
    }
}