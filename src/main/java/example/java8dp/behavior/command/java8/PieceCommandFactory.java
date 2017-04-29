package example.java8dp.behavior.command.java8;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import example.java8dp.behavior.command.Piece;

public class PieceCommandFactory {

    static Consumer<Piece> ofForward(final int step) {
        return p -> {
            IntStream.range(0, step)
                    .forEach(i -> p.moveForward());
        };
    }

    static Consumer<Piece> ofBackward(final int step) {
        return p -> {
            IntStream.range(0, step)
                    .forEach(i -> p.moveBackward());
        };
    }

    static Consumer<Piece> ofTurnRight() {
        return p -> {
            double radian = p.getDirection().radian() - Math.PI / 2;
            p.setDirection(Piece.Direction.valueOf(radian));
        };
    }

    static Consumer<Piece> ofTurnLeft() {
        return p -> {
            double radian = p.getDirection().radian() + Math.PI / 2;
            p.setDirection(Piece.Direction.valueOf(radian));
        };
    }

    @SafeVarargs
    static Consumer<Piece> chain(Consumer<Piece>... commands) {
        return Stream.of(commands)
                .reduce((c1, c2) -> c1.andThen(c2))
                .orElse(p -> {
                });
    }

}
