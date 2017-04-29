package example.java8dp.behavior.command.old;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import example.java8dp.behavior.command.Piece;

public class PieceCommandTest {

    private Piece piece;

    @Before
    public void before() {
        piece = new Piece();
    }

    @Test
    public void test() {
        PieceCommand cmd1 = new Forward(5);
        cmd1.execute(piece);

        assertEquals(Piece.Direction.NORTH, piece.getDirection());
        assertEquals(0, piece.getX());
        assertEquals(5, piece.getY());

        PieceCommand cmd2 = new TurnRight();
        cmd2.execute(piece);

        assertEquals(Piece.Direction.EAST, piece.getDirection());
        assertEquals(0, piece.getX());
        assertEquals(5, piece.getY());

        PieceCommand cmd3 = new Backward(3);
        cmd3.execute(piece);

        assertEquals(Piece.Direction.EAST, piece.getDirection());
        assertEquals(-3, piece.getX());
        assertEquals(5, piece.getY());

        PieceCommand cmd4 = new TurnLeft();
        cmd4.execute(piece);

        assertEquals(Piece.Direction.NORTH, piece.getDirection());
        assertEquals(-3, piece.getX());
        assertEquals(5, piece.getY());

    }

}
