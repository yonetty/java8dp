package example.java8dp.behavior.command.old;

import example.java8dp.behavior.command.Piece;

public class TurnLeft implements PieceCommand {

    @Override
    public void execute(Piece piece) {
        double radian = piece.getDirection().radian() + Math.PI / 2;
        piece.setDirection(Piece.Direction.valueOf(radian));
    }

}
