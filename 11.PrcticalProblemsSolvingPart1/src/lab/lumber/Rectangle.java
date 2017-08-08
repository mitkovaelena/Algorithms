package lab.lumber;

public class Rectangle {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Rectangle(int x1, int y2, int x2, int y1) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public boolean intersects(Rectangle other) {
        return this.x1 <= other.x2 && other.x1 <= this.x2 &&
                this.y1 <= other.y2 && other.y1 <= this.y2;
    }
}