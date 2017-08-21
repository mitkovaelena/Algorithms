import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Medenka {
    private static String[] medenka;
    private static Set<String> output;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        medenka = reader.readLine().split("\\s+");

        output = new HashSet<>();
        breakTheMedenka(0, false, "");

        StringBuilder sb = new StringBuilder();
        for (String m : output) {
            sb.append(m + "\n");
        }
        System.out.println(sb.toString().trim());
        System.out.println(".");
    }

    private static void breakTheMedenka(int index, boolean hasNut, String crntPieces) {
        if (index == medenka.length) {
            if (hasNut && crntPieces.charAt(crntPieces.length() - 1) != '|') {
                output.add(crntPieces);
            }
        } else if (medenka[index].equals("1") && !hasNut) {
            crntPieces += "1";
            breakTheMedenka(index + 1, false, crntPieces + "|");
            breakTheMedenka(index + 1, true, crntPieces);
        } else if (medenka[index].equals("0")) {
            crntPieces += "0";
            breakTheMedenka(index + 1, hasNut, crntPieces);
            if (hasNut) {
                breakTheMedenka(index + 1, false, crntPieces + "|");
            }
        }
    }
}
