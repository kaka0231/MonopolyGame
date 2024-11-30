package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class GameSaver {
   public void savePlayersData(ArrayList<Player> players, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(players); 
            System.out.println("All player data saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBoard(Object[] board, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(board);
            System.out.println("Board data saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
