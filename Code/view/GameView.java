package view;

import model.Game;
import model.PropertySquare;
import model.Square;
import model.Player;

public class GameView {
    private Game game;

    public static final String RED = "\u001B[31m"; // Red
    public static final String YELLOW = "\u001B[33m"; // Yellow
    public static final String BLUE = "\u001B[34m";      // Standard Blue
    public static final String DEEP_BLUE = "\u001B[34;1m"; // Bright Blue
    public static final String RESET = "\u001B[0m"; 
    public static final String line = "========================================================================================================";
    public GameView(Game game) {
        this.game = game;
    }

    public void displayBoard() {
        Square[] board = game.getBoard();
        
        System.out.println("\n" + line);
        
        // Top row - first line (names)
        System.out.printf("||%15s||%15s||%15s||%15s||%15s||%15s||\n",
            centerText(board[10].getName(), 15),
            DEEP_BLUE + centerText(board[11].getName(), 15) + RESET,
            centerText(board[12].getName(), 15),
            DEEP_BLUE + centerText(board[13].getName(), 15) + RESET,
            DEEP_BLUE + centerText(board[14].getName(), 15) + RESET,
            centerText(board[15].getName(), 15));
        
        // Top row - second line (prices)
        System.out.printf("||%15s||%15s||%15s||%15s||%15s||%15s||\n",
            centerText(formatPrice(board[10]), 15),
            centerText(formatPrice(board[11]), 15),
            centerText(formatPrice(board[12]), 15),
            centerText(formatPrice(board[13]), 15),
            centerText(formatPrice(board[14]), 15),
            centerText(formatPrice(board[15]), 15));
            
        // Top row - third line (empty for spacing)
        System.out.printf("||%15s||%15s||%15s||%15s||%15s||%15s||\n",
            centerText(getOwnerName(board[10]), 15),
            centerText(getOwnerName(board[11]), 15),   
            centerText(getOwnerName(board[12]), 15),
            centerText(getOwnerName(board[13]), 15),
            centerText(getOwnerName(board[14]), 15),
            centerText(getOwnerName(board[15]), 15));
        
        // Top row - fourth line (player positions)
        System.out.printf("||%15s||%15s||%15s||%15s||%15s||%15s||\n",
            centerText(getPlayersOnSquare(board[10]), 15),
            centerText(getPlayersOnSquare(board[11]), 15),   
            centerText(getPlayersOnSquare(board[12]), 15),
            centerText(getPlayersOnSquare(board[13]), 15),
            centerText(getPlayersOnSquare(board[14]), 15),
            centerText(getPlayersOnSquare(board[15]), 15));
        System.out.println(line);
        
        // Middle rows with three lines for each box
        displayMiddleRow(board[9], board[16]);
        
        System.out.printf("%19s%66s%19s\n",
            centerText(line, 19),
            " ".repeat(66),
            centerText(line, 19));
        displayMiddleRow(board[8], board[17]);

        System.out.printf("%19s%66s%19s\n",
            centerText(line, 19),
            " ".repeat(66),
            centerText(line, 19));
        displayMiddleRow(board[7], board[18]);

        System.out.printf("%19s%66s%19s\n",
            centerText(line, 19),
            " ".repeat(66),
            centerText(line, 19));
        displayMiddleRow(board[6], board[19]);

        System.out.println(line);
        
        // Bottom row names
        System.out.printf("||%15s||%15s||%15s||%15s||%15s||%15s||\n",
            centerText(board[5].getName(), 15),
            BLUE + centerText(board[4].getName(), 15) + RESET,
            centerText(board[3].getName(), 15),
            BLUE + centerText(board[2].getName(), 15) + RESET,
            BLUE + centerText(board[1].getName(), 15) + RESET,
            centerText(board[0].getName(), 15));
        
        // Bottom row prices
        System.out.printf("||%15s||%15s||%15s||%15s||%15s||%15s||\n",
            centerText(formatPrice(board[5]), 15),
            centerText(formatPrice(board[4]), 15),
            centerText(formatPrice(board[3]), 15),
            centerText(formatPrice(board[2]), 15),
            centerText(formatPrice(board[1]), 15),
            centerText(formatPrice(board[0]), 15));

        // Bottom row - third line (empty for spacing)
        System.out.printf("||%15s||%15s||%15s||%15s||%15s||%15s||\n",
            centerText(getOwnerName(board[5]), 15),
            centerText(getOwnerName(board[4]), 15),   
            centerText(getOwnerName(board[3]), 15),
            centerText(getOwnerName(board[2]), 15),
            centerText(getOwnerName(board[1]), 15),
            centerText(getOwnerName(board[0]), 15));
        
        // Bottom row - fourth line (player positions)
        System.out.printf("||%15s||%15s||%15s||%15s||%15s||%15s||\n",
            centerText(getPlayersOnSquare(board[5]), 15),
            centerText(getPlayersOnSquare(board[4]), 15),   
            centerText(getPlayersOnSquare(board[3]), 15),
            centerText(getPlayersOnSquare(board[2]), 15),
            centerText(getPlayersOnSquare(board[1]), 15),
            centerText(getPlayersOnSquare(board[0]), 15));
        System.out.println(line);
    }

    private void displayMiddleRow(Square leftSquare, Square rightSquare) {
        System.out.printf("||%15s||%66s||%15s||\n",
            leftSquare.getName().contains("Chance") ? centerText(leftSquare.getName(), 15) : 
                RED + centerText(leftSquare.getName(), 15) + RESET,
            " ".repeat(66),
            rightSquare.getName().contains("Chance") ? centerText(rightSquare.getName(), 15) : 
                YELLOW + centerText(rightSquare.getName(), 15) + RESET);
        
        System.out.printf("||%15s||%66s||%15s||\n",
            centerText(formatPrice(leftSquare), 15),
            " ".repeat(66),
            centerText(formatPrice(rightSquare), 15));
        
        System.out.printf("||%15s||%66s||%15s||\n",
            centerText(getOwnerName(leftSquare), 15), // Display owner name for left square
            " ".repeat(66),
            centerText(getOwnerName(rightSquare), 15)); // Display owner name for right square
        
        // Add fourth line for player positions
        System.out.printf("||%15s||%66s||%15s||\n",
            centerText(getPlayersOnSquare(leftSquare), 15),
            " ".repeat(66),
            centerText(getPlayersOnSquare(rightSquare), 15));
    }

    private String centerText(String text, int width) {
        if (text == null || text.isEmpty()) return " ".repeat(width);
        text = text.length() > width ? text.substring(0, width) : text;
        int padding = width - text.length();
        int leftPad = padding / 2;
        int rightPad = padding - leftPad;
        return " ".repeat(leftPad) + text + " ".repeat(rightPad);
    }

    private String formatPrice(Square square) {
        if (square instanceof PropertySquare) {
            PropertySquare property = (PropertySquare) square;
            return "$" + property.getPrice();
        }
        return "";
    }

    private String getOwnerName(Square square) {
        if (square instanceof PropertySquare) {
            PropertySquare property = (PropertySquare) square;
            Player owner = property.getOwner(); 
            return owner != null ? "Owner: " + owner.getName() : " "; 
        }
        return " "; // Return empty space for non-property squares
    }

    public void displayDiceNumber(int diceNumber) {
        System.out.println("\n--- Dice Number =" + diceNumber + "---");
    }

    public void displayPlayerStatus() {
        System.out.println("--------------------------------------------- Player Status --------------------------------------------");
        for (Player player : game.getPlayers()) {
            String positionName;
            positionName = game.getBoard()[player.getPosition()].getName();
            String formattedStatus = String.format("%s: HKD %d, Position: %s (%d)", player.getName(), player.getMoney(),positionName ,player.getPosition());
            System.out.printf(centerText(formattedStatus, 103) + "\n");
        }
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }

    private String getPlayersOnSquare(Square square) {
        StringBuilder players = new StringBuilder();
        Square[] board = game.getBoard();
        
        // Find the position of the square in the board
        int squarePosition = -1;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == square) {
                squarePosition = i;
                break;
            }
        }
        
        // Find players on this square
        for (Player player : game.getPlayers()) {
            if (player.getPosition() == squarePosition) {
                players.append(player.getName()).append(" ");
            }
        }
        return players.length() > 0 ? "Here: " + players.toString().trim() : " ";
    }
}