package controller;

import model.Game;
import model.GameLoader;
import model.Player;
import model.Square;
import view.GameView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

    public class GameController {
        private Game game;
        private GameView view;
        private int roundNumber;
        private Scanner scanner;
        private int numPlayers = 0;
        private boolean validInput = false;
        private boolean gameRunning;

        public GameController(Game game, GameView view) {
            this.game = game;
            this.view = view;
            this.roundNumber = 1;
            this.scanner = new Scanner(System.in);
            this.gameRunning = true;
        }



        public void startGame() {
            while (gameRunning) {
                System.out.println("Wellcome to Monopoly!!, please select an option:");
                System.out.println("1. Start New Game");
                System.out.println("2. Edit Game Board");
                System.out.println("3. Load Game Record");
                System.out.println("4. Quit");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        addPlayer();
                        GameOpen();
                        break;
                    case "2":
                        EditGameBoard();
                        break;
                    case "3":
                        loaderRecord();
                        GameOpen();
                        break;
                    case "4":
                        System.out.println("Exiting the game.");
                        gameRunning = false;
                        return; // Exit the method
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

                if (checkForWinner()) {
                    gameRunning = false;
                    break;
                }
            }

            cleanup();
        }

        private boolean checkForWinner() {
            ArrayList<Player> winners = game.getWinners();
            if (winners.size() > 0) {
                return true;
            }
            return false;
        }

        // Edit the game board
        public void EditGameBoard() {
            while (true) {
                Square[] board = game.getBoard(); // Get the current board state
                view.displayBoard(); // Display the current state of the board

                // Display squares
                for (int i = 0; i < board.length; i++) {
                    if (isPropertySquare(board[i].getName())) {
                        System.out.println((i + 1) + ". " + board[i].getName() + " - Price: " + board[i].getPrice());
                    }
                }

                System.out.println("Enter the number of the square you want to edit (1-" + board.length + "), or 'Q' to quit:");
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("q")) {
                    break;
                }

                try {
                    int squareNumber = Integer.parseInt(input) - 1;
                    if (squareNumber < 0 || squareNumber >= board.length) {
                        System.out.println("Invalid square number. Please enter a number between 1 and " + board.length);
                        continue;
                    }

                    // Check if selected square is a function square
                    if (!isPropertySquare(board[squareNumber].getName())) {
                        System.out.println("Cannot edit function squares (Go, Chance, Tax, etc.).");
                        continue;
                    }

                    System.out.println("Enter the new name for square " + (squareNumber + 1) + " (" + board[squareNumber].getName() + ") (or press Enter to skip):");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) {
                        board[squareNumber].setName(newName);
                    }

                    System.out.println("Enter the new price for square " + (squareNumber + 1) + " (" + board[squareNumber].getName() + ") (or press Enter to skip):");
                    String priceInput = scanner.nextLine();
                    if (!priceInput.isEmpty()) {
                        try {
                            int newPrice = Integer.parseInt(priceInput);
                            if (newPrice >= 0) {
                                board[squareNumber].setPrice(newPrice);
                                System.out.println("Square " + (squareNumber + 1) + " (" + board[squareNumber].getName() + ") price updated to $" + newPrice);
                            } else {
                                System.out.println("Price cannot be negative. Price not updated.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid price format. Price not updated.");
                        }
                    }

                    System.out.println("Square updated successfully!");

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number or 'Q' to quit.");
                }
            }
        }

        private boolean isPropertySquare(String name) {
            String[] functionSquares = {"Go", "Income Tax", "Jail", "Chance", "Free Parking", "Go to Jail"};
            for (String functionSquare : functionSquares) {
                if (name.equals(functionSquare)) {
                    return false;
                }
            }
            return true;
        }

        public  void addPlayer(){
            while (!validInput) {
                System.out.print("Enter number of players (2 to 8): ");
                try {
                    numPlayers = scanner.nextInt();
                    scanner.nextLine();
                    if (numPlayers >= 2 && numPlayers <= 8) {
                        validInput = true;
                    } else {
                        System.out.println("Please enter a number between 2 and 8.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }
    
            // Add players
            for (int i = 0; i < numPlayers; i++) {
                System.out.print("Enter player " + (i + 1) + " name: ");
                String name = scanner.nextLine();
                game.addPlayer(new Player(name));
            }
        }

        public void GameOpen() {
           
            while (true) {
                System.out.print("==============================================Round " + roundNumber + " Start=============================================");
                for (int i = 0; i < game.getNumberOfPlayers(); i++) {
                    view.displayBoard();
                    view.displayPlayerStatus();
                    game.nextTurn();
                }
                System.out.println("==============================================Round " + roundNumber + " End===============================================");
                roundNumber++;  // Increment round counter
                
                // Check for end conditions
                if (game.checkEndConditions(roundNumber)) {
                    view.displayPlayerStatus();
                    ArrayList<Player> winners = game.getWinners();
                    if (winners.size() > 1) {
                        System.out.print("It's a tie between: ");
                        for (Player winner : winners) {
                            System.out.print(winner.getName() + " ");
                        }
                    } else {
                        System.out.println(winners.get(0).getName() + " wins the game with HKD " + winners.get(0).getMoney() + "!");
                    }
                    break;
                }
            }
        }

        public void loaderRecord() {
            GameLoader loader = new GameLoader();

            //Load Players
            ArrayList<Player> loadedPlayers = loader.loadPlayersData("playerData.ser");
            Square[] loadedBoard = (Square[]) loader.loadBoardData("boardData.ser");
            for (int i = 0; i < loadedPlayers.size(); i++) {
                game.addPlayer(loadedPlayers.get(i));
            }

            //Load Board
            Square[] board = new Square[20];
            if (loadedBoard != null && loadedBoard.length > 0) {
                for (int i = 0; i < loadedBoard.length; i++) {
                    if (loadedBoard[i] instanceof Square) {
                        board[i] = (Square) loadedBoard[i]; 
                    } else {
                        System.out.println("Loaded board contains an invalid element at index " + i);
                        return; 
                    }
                }
                game.setBoard(board);
            } else {
                System.out.println("Loaded board is invalid or empty.");
            }
        }

        public void cleanup() {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
