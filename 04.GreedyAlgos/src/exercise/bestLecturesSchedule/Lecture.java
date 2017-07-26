package exercise.bestLecturesSchedule;

public class Lecture implements Comparable<Lecture> {
    private int startTime;
    private int endTime;
    private String name;

    public Lecture(int startTime, int endTime, String name) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    @Override
    public int compareTo(Lecture o) {
        return Integer.compare(this.endTime, o.endTime);
    }

    @Override
    public String toString() {
        return startTime + "-" + endTime + " -> " + name;
    }
}