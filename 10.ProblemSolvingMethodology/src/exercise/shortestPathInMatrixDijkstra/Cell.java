package exercise.shortestPathInMatrixDijkstra;

public class Cell {
    private int index;
    private int weight;

    public Cell(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
