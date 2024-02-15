public class MouseLine {
    private int position;
    private int heading;

    public MouseLine(int initialPosition) {
        position = initialPosition;
        heading = 1;
    }

    public void turn() {
        heading *= -1;
    }

    public void move() {
        position += heading;
    }

    public int getPosition() {
        return position;
    }
}