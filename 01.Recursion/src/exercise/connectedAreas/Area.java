package exercise.connectedAreas;

public class Area implements Comparable<Area> {
    private int positionR;
    private int positionC;
    private int size;

    public Area(int positionR, int positionC) {
        this.positionR = positionR;
        this.positionC = positionC;
        this.size = 0;
    }

    public int getPositionR() {
        return positionR;
    }

    public void setPositionR(int positionR) {
        this.positionR = positionR;
    }

    public int getPositionC() {
        return positionC;
    }

    public void setPositionC(int positionC) {
        this.positionC = positionC;
    }

    public int getSize() {
        return size;
    }

    public void incrementSize() {
        this.size++;
    }

    @Override
    public int compareTo(Area other) {
        int cmp = Integer.compare(other.size, this.size);
        if (cmp == 0) {
            cmp = Integer.compare(this.positionR, other.getPositionR());
        }

        if (cmp == 0) {
            cmp = Integer.compare(this.positionC, other.getPositionC());
        }

        return cmp;
    }
}
