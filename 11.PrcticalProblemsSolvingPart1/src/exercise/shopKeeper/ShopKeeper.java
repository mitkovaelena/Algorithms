package exercise.shopKeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ShopKeeper {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] stockTokens = reader.readLine().split(" ");
        Set<Integer> stock = new HashSet<>();
        for (int i = 0; i < stockTokens.length; i++) {
            stock.add(Integer.parseInt(stockTokens[i]));
        }

        int[] purchases = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        if(!stock.contains(purchases[0])){
            System.out.println("impossible");
            return;
        }

        int changes = 0;
        for (int i = 1; i < purchases.length; i++) {
            if(!stock.contains(purchases[i])){
                Set<Integer> purchasesLeft = new LinkedHashSet<>();
                int lastPurchase = 0;
                for (int j = i+1; j < purchases.length; j++) {
                    if(stock.contains(purchases[j])) {
                        if(purchasesLeft.add(purchases[j])){
                            lastPurchase = purchases[j];
                        }
                    }
                }
                for(Integer product : stock){
                    if(!purchasesLeft.contains(product)) {
                        lastPurchase = product;
                        break;
                    }
                }
                stock.remove(lastPurchase);
                stock.add(purchases[i]);
                changes++;
            }
        }
        System.out.println(changes);
    }
}
