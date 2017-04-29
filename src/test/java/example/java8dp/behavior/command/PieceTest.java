package example.java8dp.behavior.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PieceTest {

    private Piece piece;

    @Before
    public void before() {
        piece = new Piece();
    }

    @Test
    public void testMove() {
        assertEquals(Piece.Direction.NORTH, piece.getDirection());
        assertEquals(0, piece.getX());
        assertEquals(0, piece.getY());
        piece.moveForward();
        assertEquals(0, piece.getX());
        assertEquals(1, piece.getY());
        piece.moveBackward();
        assertEquals(0, piece.getX());
        assertEquals(0, piece.getY());
    }

    @Test
    public void testDirection() {
        piece.setDirection(Piece.Direction.EAST);
        piece.moveForward();
        assertEquals(1, piece.getX());
        assertEquals(0, piece.getY());

        piece.setDirection(Piece.Direction.SOUTH);
        piece.moveBackward();
        assertEquals(1, piece.getX());
        assertEquals(1, piece.getY());
    }

}
