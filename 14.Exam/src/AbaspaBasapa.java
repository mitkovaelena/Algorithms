import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class AbaspaBasapa {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String str1 = reader.readLine();
        String str2 = reader.readLine();

        if(str1.equals(str2)){
            System.out.println(str1);
            return;
        }

        int max = 0;
        String maxStr = null;
        for (int i = 0; i < str1.length(); i++) {
            for (int j = str1.length() - max - 1; j >= 0; j--) {
                if(j - i < max){
                    break;
                }
                String subStr = str1.substring(i, j + 1);

                if (str2.contains(subStr)) {
                    if (subStr.length() > max) {
                        max = subStr.length();
                        maxStr = subStr;
                    }
                    break;
                }
            }
        }

        System.out.println(maxStr);
    }
}

