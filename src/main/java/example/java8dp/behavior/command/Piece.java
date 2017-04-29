package example.java8dp.behavior.command;

import java.util.stream.Stream;

public class Piece {
    private Direction direction = Direction.NORTH;
    private int x = 0;
    private int y = 0;

    public void moveForward() {
        this.x += this.direction.x();
        this.y += this.direction.y();
    }

    public void moveBackward() {
        this.x -= this.direction.x();
        this.y -= this.direction.y();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static enum Direction {
        NORTH(Math.PI / 2), EAST(0), SOUTH(Math.PI / -2), WEST(Math.PI);

        final private double radian;
        final private int x;
        final private int y;

        Direction(double radian) {
            this.radian = radian;
            this.x = (int) Math.cos(radian);
            this.y = (int) Math.sin(radian);
        }

        public static Direction valueOf(double radian) {
            return Stream.of(Direction.values())
                    .filter(d -> d.x == (int) Math.cos(radian) && d.y == (int) Math.sin(radian))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }

        public int x() {
            return this.x;
        }

        public int y() {
            return this.y;
        }

        public double radian() {
            return this.radian;
        }
    }
}
