import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BlackMessup {
    private static Map<String, List<String>> graph = new HashMap<>();
    private static Map<String, Atom> atoms = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        int atomsCount = Integer.parseInt(reader.readLine());
        int connectionsCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < atomsCount; i++) {
            String[] atomTokens = reader.readLine().split(" ");
            Atom atom = new Atom(atomTokens[0], Integer.parseInt(atomTokens[1]), Integer.parseInt(atomTokens[2]));
            atoms.put(atomTokens[0], atom);
            graph.put(atomTokens[0], new ArrayList<>());
        }

        for (int i = 0; i < connectionsCount; i++) {
            String[] connectionTokens = reader.readLine().split(" ");

            graph.get(connectionTokens[0]).add(connectionTokens[1]);
            graph.get(connectionTokens[1]).add(connectionTokens[0]);
        }

        List<SortedSet<Atom>> connectedComponents = getConnectedComponents();

        long maxSum = Long.MIN_VALUE;
        for (SortedSet<Atom> component : connectedComponents) {

            SortedSet<Atom> chosenTasks = new TreeSet<Atom>(new DeadlineAscValueDesc());
            long sum = chooseTasks(component, chosenTasks);


            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        System.out.println(maxSum);
    }

    private static long chooseTasks(SortedSet<Atom> component, SortedSet<Atom> chosenTasks) {

        long totalPrice = 0;
        int maxDecay = Integer.MIN_VALUE;
        for (Atom atom : component) {
            if(atom.decay > maxDecay){
                maxDecay = atom.decay;
            }
        }

        int i = 1;
        for (Atom task : component) {
            if (i > maxDecay) break;              //optimisation

            int count = 0;                           //check if the task can be completed
            for (Atom process : chosenTasks) {
                if (process.decay <= task.decay) count++;
            }
            if (count >= task.decay) continue;

            chosenTasks.add(task);
            totalPrice += task.mass;
            i++;
        }
        return totalPrice;
    }

    public static List<SortedSet<Atom>> getConnectedComponents() {
        List<SortedSet<Atom>> connectedComponents = new ArrayList<>();

        Set<String> visited = new HashSet<>();
        for (String atom : graph.keySet()) {
            if (!visited.contains(atom)) {
                SortedSet<Atom> dfsOrderedNodes = new TreeSet<>();
                DFS(atom, visited, dfsOrderedNodes);
                connectedComponents.add(dfsOrderedNodes);
            }
        }
        return connectedComponents;
    }

    private static void DFS(String crntNode, Set<String> visited, Set<Atom> dfsOrderedNodes) {
        if (!visited.contains(crntNode)) {
            visited.add(crntNode);
            for (String child : graph.get(crntNode)) {
                DFS(child, visited, dfsOrderedNodes);
            }
            dfsOrderedNodes.add(atoms.get(crntNode));
        }
    }

    private static class Atom implements Comparable<Atom>{
        String name;
        int mass;
        int decay;

        public Atom(String name, int mass, int decay) {
            this.name = name;
            this.mass = mass;
            this.decay = decay;
        }

        @Override
        public int compareTo(Atom o) {
            return Integer.compare(o.mass, this.mass);
        }
    }

    private static class DeadlineAscValueDesc implements Comparator<Atom> {

        @Override
        public int compare(Atom a1, Atom a2) {
            int cmp = Integer.compare(a1.decay, a2.decay);
            if (cmp == 0) {
                cmp = Integer.compare(a2.mass, a1.mass);
            }
            return cmp;
        }
    }
}
