package exercise.bestLecturesSchedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BestLecturesSchedule {

    public static class Lecture implements Comparable<Lecture> {
        private int startTime;
        private int endTime;
        private String name;

        public Lecture(int startTime, int endTime, String name) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.name = name;
        }

        private String getName() {
            return name;
        }

        private int getStartTime() {
            return startTime;
        }

        private int getEndTime() {
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

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int lecturesCount = Integer.parseInt(reader.readLine().substring(10));

        SortedSet<Lecture> lecturesByEndTime = new TreeSet<>();

        for (int i = 0; i < lecturesCount; i++) {
            String[] inputLine = reader.readLine().split(": ");
            String lectureName = inputLine[0];
            Integer startTime = Integer.parseInt(inputLine[1].split(" - ")[0]);
            Integer endTime = Integer.parseInt(inputLine[1].split(" - ")[1]);
            Lecture lecture = new Lecture(startTime, endTime, lectureName);
            lecturesByEndTime.add(lecture);
        }

        SortedSet<Lecture> chosenLectures = new TreeSet<>();
        chooseTasks(lecturesByEndTime, chosenLectures);

        System.out.printf("Lectures (%d):\n", chosenLectures.size());
        System.out.println(chosenLectures.stream().map(Lecture::toString)
                .collect(Collectors.joining("\n")));
    }

    private static void chooseTasks(SortedSet<Lecture> lectures, SortedSet<Lecture> chosenLectures) {
        Set<Lecture> overlappingLectures = new HashSet<>();
        for (Lecture lecture : lectures) {
            if (overlappingLectures.contains(lecture)) {
                continue;
            }
            chosenLectures.add(lecture);

            for (Lecture other : lectures) {             //check for overlapping letures
                if ((other.getStartTime() >= lecture.getStartTime() && other.getStartTime() <= lecture.getEndTime())
                        || (other.getEndTime() >= lecture.getStartTime() && other.getEndTime() <= lecture.getEndTime())
                        || (lecture.getStartTime() >= other.getStartTime() && lecture.getStartTime() <= other.getEndTime())
                        || (lecture.getEndTime() >= other.getStartTime() && lecture.getEndTime() <= other.getEndTime())) {
                    overlappingLectures.add(other);
                }
            }

            if (overlappingLectures.size() == lectures.size())  //optimisation
                break;
        }
    }
}

