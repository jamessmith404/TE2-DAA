import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.algorithms.DP;

public class Generator {

    public static void main(String[] args) {
        Random rand = new Random();
        int n = 80; // Change this for the size
        int lower = 0;
        int upper = 100;
        long seed = 0; // Seed Value
        boolean isWrite = false;
        int[] result = { 1 };
        List<Integer> resultList = new ArrayList<>();

        while (!DP.partition(result)) {
            seed = rand.nextInt(1000);
            System.out.println(seed);
            resultList = generateRandomSetAsList(n, lower, upper, seed, isWrite);
            result = resultList.stream().mapToInt(i -> i).toArray();
        }

        writeListToFile(resultList, seed);

        // Print the result
        System.out.println("Generated Random Set: " + Arrays.toString(result));

    }

    public static List<Integer> generateRandomSetAsList(int n, int lower, int upper, long seed, boolean isWrite) {
        Set<Integer> randomSet = new HashSet<>();
        Random random = new Random(seed);

        while (randomSet.size() < n) {
            int randomElement = random.nextInt(upper - lower + 1) + lower;
            randomSet.add(randomElement);
        }

        // Convert set to list
        List<Integer> result = new ArrayList<>(randomSet);

        // Shuffle the list
        java.util.Collections.shuffle(result);

        if (isWrite) {
            writeListToFile(result, seed);
        }

        return result;
    }

    public static void writeListToFile(List<Integer> list, long seed) {
        String size = "";

        if (list.size() == 10)
            size = "kecil";
        else if (list.size() == 40)
            size = "sedang";
        else if (list.size() == 80)
            size = "besar";

        String filename = size + "_seed-" + seed + ".txt";
        try (FileWriter fileWriter = new FileWriter(String.format("./dataset/%s", filename))) {
            for (int num : list) {
                fileWriter.write(num + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
