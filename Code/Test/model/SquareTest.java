package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    static class TestSquare extends Square {
        public TestSquare(String name) {
            super(name);
        }

        @Override
        public void landOn(Player player) {

        }
    }

    @Test
    void getName() {
        Square square = new TestSquare("Start");


        assertEquals("Start", square.getName(), "Initial name should be 'Start'.");
    }

    @Test
    void setName() {
        Square square = new TestSquare("Start");


        square.setName("Go");
        assertEquals("Go", square.getName(), "Name should be updated to 'Go'.");
    }

    @Test
    void setPrice() {
        Square square = new TestSquare("Start");


        square.setPrice(1500);
        assertEquals(1500, square.getPrice(), "Price should be updated to 1500.");
    }

    @Test
    void getPrice() {
        Square square = new TestSquare("Start");

        assertEquals(0, square.getPrice(), "Initial price should be 0.");
    }

    @Test
    void landOn() {
        Square square = new TestSquare("Start");
        Player player = new Player("Henry");

        square.landOn(player);
        assertTrue(true, "landOn was called without exception.");
    }

}