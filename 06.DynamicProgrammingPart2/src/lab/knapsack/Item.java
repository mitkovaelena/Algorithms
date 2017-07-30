package lab.knapsack;


public class Item {
    private String name;
    private int value;
    private int weight;

    public Item(String name, int weight, int value) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }
}
