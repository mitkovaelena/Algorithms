package exercise.processorSchedualing;

public class Process implements Comparable<Process> {
    private int index;
    private int value;
    private int deadline;

    public Process(int index, int value, int deadline) {
        this.index = index;
        this.value = value;
        this.deadline = deadline;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    public int getDeadline() {
        return deadline;
    }

    @Override
    public int compareTo(Process o) {
        return Integer.compare(o.getValue(), this.getValue());
    }
}