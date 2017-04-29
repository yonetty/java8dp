package example.java8dp.behavior.command.old;

import example.java8dp.behavior.command.Piece;

public class Forward implements PieceCommand {
    private final int step;

    public Forward(int step) {
        this.step = step;
    }

    @Override
    public void execute(Piece piece) {
        for (int i = 0; i < step; i++) {
            piece.moveForward();
        }
    }
}
