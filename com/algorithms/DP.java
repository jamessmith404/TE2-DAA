package com.algorithms;

import java.util.ArrayList;

public class DP {
    public static ArrayList<Integer> subset1 = new ArrayList<>();
    public static ArrayList<Integer> subset2 = new ArrayList<>();

    // Utility function to print the subsets
    public static void printSubsets() {
        System.out.println("Subset 1: " + subset1);
        System.out.println("Subset 2: " + subset2);
    }

    // Returns true if arr[] can be partitioned in two subsets of equal sum,
    // otherwise false
    static boolean findPartition(int arr[], int n) {
        int sum = 0;
        int i, j;

        // Calculate sum of all elements
        for (i = 0; i < n; i++)
            sum += arr[i];

        if (sum % 2 != 0)
            return false;

        boolean[][] part = new boolean[sum / 2 + 1][n + 1];

        // initialize top row as true
        for (i = 0; i <= n; i++)
            part[0][i] = true;

        // initialize leftmost column, except part[0][0], as TRUE
        for (i = 1; i <= sum / 2; i++)
            part[i][0] = false;

        // Fill the partition table in bottom up manner
        for (i = 1; i <= sum / 2; i++) {
            for (j = 1; j <= n; j++) {
                part[i][j] = part[i][j - 1];
                if (i >= arr[j - 1])
                    part[i][j] = part[i][j] || part[i - arr[j - 1]][j - 1];
            }
        }

        if (!part[sum / 2][n])
            return false;

        // Now reconstruct the subsets
        int x = sum / 2;
        int y = n;

        while (x != 0 && y != 0) {
            if (!part[x][y - 1]) { // If this part is False, then the element will be enter the subset1
                subset1.add(arr[y - 1]);
                x -= arr[y - 1];
            } else {
                subset2.add(arr[y - 1]);
            }
            y--;
        }

        // Add remaining elements to subset2
        while (y > 0) {
            subset2.add(arr[y - 1]);
            y--;
        }

        return true;
    }

    // Driver code
    public static boolean partition(int[] arr) {
        int n = arr.length;
        return (findPartition(arr, n));
    }
}