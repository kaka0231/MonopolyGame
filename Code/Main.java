import model.Game;
import view.GameView;
import controller.GameController;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GameView view = new GameView(game);
        GameController controller = new GameController(game, view);
        controller.startGame();
        
        // Close the scanner at the end
        controller.cleanup(); // Call cleanup to close the scanner
    }
}