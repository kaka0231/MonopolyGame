package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private Square[] board;
    private Random random;
    private Player currentPlayer;
    private Scanner scanner;
    private int totalRoundNumber;

    public Game() {
        players = new ArrayList<>();
        board = new Square[20];
        random = new Random();
        totalRoundNumber = 100;
        scanner = new Scanner(System.in);
        setupBoard();
    }


    private void setupBoard() {
        board[0] = new GoSquare();
        board[1] = new PropertySquare("Central", 800, 90);
        board[2] = new PropertySquare("Wan Chai", 700, 65);
        board[3] = new IncomeTaxSquare();
        board[4] = new PropertySquare("Stanley", 600, 60);
        board[5] = new JailSquare();
        board[6] = new PropertySquare("Shek O", 400, 10);
        board[7] = new PropertySquare("Mong Kok", 500, 40);
        board[8] = new ChanceSquare();
        board[9] = new PropertySquare("Tsing Yi", 400, 15);
        board[10] = new FreeParkingSquare();
        board[11] = new PropertySquare("Shatin", 700, 75);
        board[12] = new ChanceSquare();
        board[13] = new PropertySquare("Tuen Mun", 400, 20);
        board[14] = new PropertySquare("Tai Po", 500, 25);
        board[15] = new GoToJailSquare();
        board[16] = new PropertySquare("Sai Kung", 400, 10);
        board[17] = new PropertySquare("Yuen Long", 400, 25);
        board[18] = new ChanceSquare();
        board[19] = new PropertySquare("Tai O", 600, 25);
    }

    public void setBoard(Square[] board) {
        this.board = board;
    }

    public void addPlayer(Player player) {
        players.add(player);
        // Set the first player added as the current player
        if (currentPlayer == null) {
            currentPlayer = player;
        }
    }

    public void nextTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        
        //user input
        boolean validInput = false;
        while (!validInput) {
            // Check if current player is in jail
            if (currentPlayer.isInJail()) {
                System.out.println("\n" + currentPlayer.getName() + " is in jail!");
                if (currentPlayerIndex == 0) {
                    System.out.println("Press 'P' to pay HKD 150 to leave jail, 'R' to roll dice, 'Q' to quit game, or 'S' to save game.");
                } else {
                    System.out.println("Press 'P' to pay HKD 150 to leave jail, 'R' to roll dice, 'Q' to quit game.");
                }
                String input = scanner.next().toLowerCase();
                
                if (input.equals("p")) {
                    validInput = true;
                    ((JailSquare)board[5]).payToLeaveJail(currentPlayer);
                    if (!currentPlayer.isInJail()) {  // If successfully paid and left jail
                        int dice1 = rollDie();
                        int dice2 = rollDie();
                        int totalRoll = dice1 + dice2;
                        System.out.println(currentPlayer.getName() + " rolled: " + dice1 + " & " + dice2 + " = " + totalRoll);
                        currentPlayer.move(totalRoll);
                        board[currentPlayer.getPosition()].landOn(currentPlayer);
                        handleBankruptcy(currentPlayer);
                    }
                } else if (input.equals("r")) {
                    validInput = true;
                    int dice1 = rollDie();
                    int dice2 = rollDie();
                    System.out.println(currentPlayer.getName() + " rolled: " + dice1 + " & " + dice2);
                    boolean canMove = ((JailSquare)board[5]).handleJailTurn(currentPlayer, dice1, dice2);
                    if (canMove) {
                        currentPlayer.move(dice1 + dice2);
                        board[currentPlayer.getPosition()].landOn(currentPlayer);
                        handleBankruptcy(currentPlayer);
                    }
                } else if (input.equals("q")) {
                    System.out.println("Game ended by player.");
                    System.exit(0);
                } else if (currentPlayerIndex == 0 && input.equals("s")) {
                    GameSaver saver = new GameSaver();
                    saver.saveBoard(board, "boardData.ser");
                    saver.savePlayersData(players, "playerData.ser");
                    System.out.println("Game saved successfully!");
                    System.exit(0);
                } else {
                    System.out.println("Invalid input! Please press 'P' to pay, 'R' to roll dice, or 'Q' to quit.");
                }
            } else {
                //non-jailed players
                System.out.println(currentPlayer.getName() + "'s turn.");
                
                // Allow only player one to save the game
                if (currentPlayerIndex == 0) {
                    System.out.println("Press 'R' to roll dice, or 'Q' to quit game, or 'S' to save game.");
                } else {
                    System.out.println("Press 'R' to roll dice or 'Q' to quit game.");
                }
                
                String input = scanner.next().toLowerCase();
                
                if (input.equals("q")) {
                    System.out.println("Game ended by player.");
                    System.exit(0);
                } else if (input.equals("r")) {
                    validInput = true;
                    int dice1 = rollDie();
                    int dice2 = rollDie();
                    int totalRoll = dice1 + dice2;
                    System.out.println(currentPlayer.getName() + " rolled: " + dice1 + " & " + dice2 + " = " + totalRoll);
                    currentPlayer.move(totalRoll);
                    board[currentPlayer.getPosition()].landOn(currentPlayer);
                    handleBankruptcy(currentPlayer);
                } else if (currentPlayerIndex == 0 && input.equals("s")) {
                    GameSaver saver = new GameSaver();
                    saver.saveBoard(board, "boardData.ser");
                    saver.savePlayersData(players, "playerData.ser");
                    System.out.println("Game saved successfully!");
                    System.exit(0);
                } else {
                    System.out.println("Invalid input! Please press 'R' to roll dice or 'Q' to quit.");
                }
            }
        }

        // Move to the next player
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private int rollDie() {
        return random.nextInt(6) + 1;  // Changed to 6-sided die
    }

    // Check if the game should end
    public boolean checkEndConditions(int roundNumber) {
        // Check if only one player is left
        if (players.size() == 1) {
            System.out.println(players.get(0).getName() + " is the winner!");
            return true;
        }
        // Check if 100 rounds have been completed
        if (roundNumber > totalRoundNumber) {
            System.out.println("100 rounds have been completed!");
            return true;
        }
        return false;
    }

    // Method to get the winner(s) at the end of the game
    public ArrayList<Player> getWinners() {
        int highestMoney = players.stream().mapToInt(Player::getMoney).max().orElse(0);
        ArrayList<Player> winners = new ArrayList<>();
        for (Player player : players) {
            if (player.getMoney() == highestMoney) {
                winners.add(player);
            }
        }
        return winners;
    }

    public Square[] getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }


    public int getNumberOfPlayers() {
        return players.size();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void handleBankruptcy(Player player) {
        if (player.getMoney() < 0) {
            System.out.println(player.getName() + " has gone bankrupt with HKD " + player.getMoney());
            
            // Release all properties owned by bankrupt player
            for (Square square : board) {
                if (square instanceof PropertySquare) {
                    PropertySquare property = (PropertySquare) square;
                    if (property.getOwner() == player) {
                        property.setOwner(null);
                        System.out.println(property.getName() + " is now available for purchase!");
                    }
                }
            }
            
            // Remove player from game
            players.remove(player);
            
            // If only one player remains, they win
            if (players.size() == 1) {
                Player winner = players.get(0);
                System.out.println(winner.getName() + " wins the game with HKD " + winner.getMoney() + "!");
                System.exit(0);
            }
            
            // Adjust currentPlayerIndex if necessary
            if (currentPlayerIndex >= players.size()) {
                currentPlayerIndex = 0;
            }
        }
    }
}