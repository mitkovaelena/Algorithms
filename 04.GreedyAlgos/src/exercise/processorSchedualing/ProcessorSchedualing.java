package exercise.processorSchedualing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ProcessorSchedualing {
        private static class DeadlineAscValueDesc implements Comparator<Process> {

        @Override
        public int compare(Process process1, Process process2) {
            int cmp = Integer.compare(process1.getDeadline(), process2.getDeadline());
            if (cmp == 0) {
                cmp = Integer.compare(process2.getValue(), process1.getValue());
            }
            return cmp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int tasksCount = Integer.parseInt(reader.readLine().substring(7));

        SortedSet<Process> tasks = new TreeSet<>();
        for (int i = 1; i <= tasksCount; i++) {
            String[] inputLine = reader.readLine().split(" - ");
            Process process = new Process(i, Integer.parseInt(inputLine[0]), Integer.parseInt(inputLine[1]));
            tasks.add(process);
        }


        SortedSet<Process> chosenTasks = new TreeSet<Process>(new DeadlineAscValueDesc());
        int totalPrice = chooseTasks(tasks, chosenTasks);

        System.out.println("Optimal schedule: " + chosenTasks.stream().map(x -> String.valueOf(x.getIndex()))
                .collect(Collectors.joining(" -> ")));
        System.out.println("Total value: " + totalPrice);
    }

    private static int chooseTasks(SortedSet<Process> tasks, SortedSet<Process> chosenTasks) {

        int totalPrice = 0;
        int maxDeadline = tasks.stream().mapToInt(Process::getDeadline).max().getAsInt();

        int i = 1;
        for (Process task : tasks) {
            if (i > maxDeadline) break;              //optimisation

            int count = 0;                           //check if the task can be completed
            for (Process process : chosenTasks) {
                if (process.getDeadline() <= task.getDeadline()) count++;
            }
            if (count >= task.getDeadline()) continue;

            chosenTasks.add(task);
            totalPrice += task.getValue();
            i++;
        }
        return totalPrice;
    }
}

