package game.state;

/**
 * Created by brandon.a.snow on 12/21/2016.
 */
public class Snake {

    //starting position
    int[] x = new int[Board.getAllDots()];
    int[] y = new int[Board.getAllDots()];

    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN;
    }





    Direction direction = Direction.RIGHT;

    int length = 0;//default length is one

    public Snake(){

    }
    public int getSnakeX(int index) {
        return x[index];
    }

    public int getSnakeY(int index) {
        return y[index];
    }

    public void setSnakeX(int i) {
        x[0] = i;
    }

    public void setSnakeY(int i) {
        y[0] = i;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move(){

        for (int i = length; i > 0; i--) {

            // Moves the joints of the snake 'up the chain'
            // Meaning, the joint of the snake all move up one
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }

        //if certain direction move that direction
        switch (direction) {
            case LEFT : x[0] -= Board.getDotSize();
                break;
            case RIGHT: x[0] += Board.getDotSize();
                break;
            case UP:   y[0] -= Board.getDotSize();
                break;
            case DOWN: y[0] += Board.getDotSize();
                break;
            default: x[0] += Board.getDotSize();
                break;
        }
    }

}
