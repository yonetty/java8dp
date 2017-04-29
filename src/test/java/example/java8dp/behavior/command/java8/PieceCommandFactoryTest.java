package example.java8dp.behavior.command.java8;

import static org.junit.Assert.*;

import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;

import example.java8dp.behavior.command.Piece;

public class PieceCommandFactoryTest {

    private Piece piece;

    @Before
    public void before() {
        piece = new Piece();
    }

    @Test
    public void test() {
        Consumer<Piece> cmd1 = PieceCommandFactory.ofForward(5);
        cmd1.accept(piece);

        assertEquals(Piece.Direction.NORTH, piece.getDirection());
        assertEquals(0, piece.getX());
        assertEquals(5, piece.getY());

        Consumer<Piece> cmd2 = PieceCommandFactory.ofTurnRight();
        cmd2.accept(piece);

        assertEquals(Piece.Direction.EAST, piece.getDirection());
        assertEquals(0, piece.getX());
        assertEquals(5, piece.getY());

        Consumer<Piece> cmd3 = PieceCommandFactory.ofBackward(3);
        cmd3.accept(piece);

        assertEquals(Piece.Direction.EAST, piece.getDirection());
        assertEquals(-3, piece.getX());
        assertEquals(5, piece.getY());

        Consumer<Piece> cmd4 = PieceCommandFactory.ofTurnLeft();
        cmd4.accept(piece);

        assertEquals(Piece.Direction.NORTH, piece.getDirection());
        assertEquals(-3, piece.getX());
        assertEquals(5, piece.getY());
    }

    @Test
    public void testComposition() {
        Consumer<Piece> composite = PieceCommandFactory.ofForward(5)
                .andThen(PieceCommandFactory.ofTurnRight())
                .andThen(PieceCommandFactory.ofBackward(3))
                .andThen(PieceCommandFactory.ofTurnLeft());
        composite.accept(piece);

        assertEquals(Piece.Direction.NORTH, piece.getDirection());
        assertEquals(-3, piece.getX());
        assertEquals(5, piece.getY());
    }

    @Test
    public void testComposition2() {
        //「右向け右」、を繰り返すと「回れ右」
        Consumer<Piece> reverse = PieceCommandFactory.ofTurnRight()
                .andThen(PieceCommandFactory.ofTurnRight());

        reverse.accept(piece);

        assertEquals(Piece.Direction.SOUTH, piece.getDirection());
        assertEquals(0, piece.getX());
        assertEquals(0, piece.getY());
    }

    @Test
    public void testComposition3() {
        Consumer<Piece> composite = PieceCommandFactory.chain(
                PieceCommandFactory.ofForward(5), PieceCommandFactory.ofTurnRight(), PieceCommandFactory.ofBackward(3),
                PieceCommandFactory.ofTurnLeft());
        composite.accept(piece);

        assertEquals(Piece.Direction.NORTH, piece.getDirection());
        assertEquals(-3, piece.getX());
        assertEquals(5, piece.getY());
    }

    @Test
    public void testReduce() {
        Consumer<Piece> composite = PieceCommandFactory.chain(PieceCommandFactory.ofForward(5));
        composite.accept(piece);

        assertEquals(Piece.Direction.NORTH, piece.getDirection());
        assertEquals(0, piece.getX());
        assertEquals(5, piece.getY());

        Consumer<Piece> empty = PieceCommandFactory.chain();
        empty.accept(piece);

        assertEquals(Piece.Direction.NORTH, piece.getDirection());
        assertEquals(0, piece.getX());
        assertEquals(5, piece.getY());
    }

}
