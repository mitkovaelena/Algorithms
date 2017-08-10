package exercise.zones;

public class Zone {
    private String type;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private double price;

    public Zone(String type, int x1, int y1, int width, int height, double price) {
        this.type = type;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + width;
        this.y2 = y1 + height;
        this.price = price;
    }

    public boolean containsSpace(int[] coordinates) {

        int x1 = coordinates[0];
        int y1 = coordinates[1];
        int x2 = coordinates[0] + 1;
        int y2 = coordinates[1] + 1;

        return  this.x2 >= x2 &&
                this.x1 <= x1 &&
                this.y1 <= y1 &&
                this.y2 >= y2;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}