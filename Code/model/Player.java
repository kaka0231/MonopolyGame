package model;

import java.io.Serializable;


public class Player implements Serializable {
    private String name;
    private int money;
    private int position;
    private boolean inJail = false;
    private int jailTurns = 0;

    public Player(String name) {

        this.name = name;
        this.money = 1500; // Starting money
        this.position = 0; // Starting position
    }

    public void move(int spaces) {
        int oldPosition = position;
        position = (position + spaces) % 20;
        
        // Check if passed through Go (position 0)
        if (oldPosition + spaces >= 20 || (oldPosition > position && position != 0)) {
            money += 1500; // Add salary for passing Go
            System.out.println(name + " passed through Go and received $1500.");
        }
    }

    public void deductMoney(int amount) {
        money -= amount;
        if (money < 0) {
            // Handle player bankruptcy
            System.out.println(name + " is bankrupt!");
        }
    }

    public void addMoney(int amount) {
        money += amount;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public void incrementJailTurns() {
        jailTurns++;
    }

    public int getJailTurns() {
        return jailTurns;
    }

    public void resetJailTurns() {
        jailTurns = 0;
    }
}