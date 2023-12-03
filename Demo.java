import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.algorithms.BNB;
import com.algorithms.DP;

public class Demo {
    public static void main(String[] args) {
        int[] arr; // { 1, 2, 3, 4, 5, 6, 7, 8 } for dummy data
        long startTime, endTime;
        int n = 1;

        // choose the size and method via this vars
        String size = "sedang"; // kecil, sedang, besar
        String method = "BNB"; // Change it accordingly
        int seedNum = 0;

        if (size.equals("kecil")) {
            n = 10;
            seedNum = 7;
        } else if (size.equals("sedang")) {
            n = 40;
            seedNum = 2;
        } else if (size.equals("besar")) {
            n = 80;
            seedNum = 961;
        }

        arr = new int[n];

        File data = new File(String.format("./dataset/%s_seed-%d.txt", size, seedNum));
        try (Scanner sc = new Scanner(data)) {
            String in = sc.nextLine();

            if (in != null) {
                // Split by blankspace
                in = in.substring(1, in.length() - 1);
                String[] integerStrings = in.split(" ");
                System.out.println(Arrays.toString(integerStrings));
                int i = 0;

                for (String integerString : integerStrings) {
                    int intValue = Integer.parseInt(integerString);
                    arr[i++] = intValue;
                }
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(arr));

        if (method.equals("DP")) {
            startTime = System.nanoTime();
            DP.partition(arr);
            endTime = System.nanoTime();

            DP.printSubsets();
        } else {
            startTime = System.nanoTime();
            BNB.partition(arr);
            endTime = System.nanoTime();

            if (BNB.bestErr == 0) {
                BNB.printSubsets(arr);
            }
        }

        double ms = (endTime - startTime) / 1_000_000.0;

        System.out.printf("Size: %s; Method: %s; Time elapsed: %f ms%n", size, method, ms);
    }
}