package exercise.rectangleIntersection;

public class Rectangle implements Comparable<Rectangle> {

    private int x1;

    private int y1;

    private int x2;

    private int y2;

    private long area;


    public Rectangle(int x1, int x2, int y1, int y2) {
        this.setX1(x1);
        this.setY1(y1);
        this.setX2(x2);
        this.setY2(y2);
    }

    public int getX1() {
        return this.x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return this.y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return this.x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return this.y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public long getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Rectangle intersectionArea(Rectangle other) {
        int x1 = Math.max(this.getX1(), other.getX1());           //ToDo
        int x2 = Math.min(this.getX2(), other.getX2());
        int y1 = Math.max(this.getY1(), other.getY1());
        int y2 = Math.min(this.getY2(), other.getY2());

        int area = Math.max(0, x2 - x1) * Math.max(0, y2 - y1);
        if (area != 0) {
            Rectangle rect = new Rectangle(x1, x2, y1, y2);
            rect.setArea(area);
            return rect;
        }
        return null;
    }


    @Override
    public int compareTo(Rectangle o) {

        if (x1 != o.x1) return 1;
        if (y1 != o.y1) return 1;
        if (x2 != o.x2) return 1;
        if (y2 != o.y2) return 1;

        return 0;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d) .. (%d, %d)",
                this.getX1(), this.getY1(), this.getX2(), this.getY2());
    }
}
