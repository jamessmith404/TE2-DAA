package com.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class BNB {
    public static boolean[] bestAssignment; // Simulate ref bestAssignment
    public static int bestErr = Integer.MAX_VALUE; // Simulate ref bestErr

    public static void partition(int[] arr) {
        int[] values = arr;
        int startIndex = 0;
        int totalValue;
        int unassignedValue;
        boolean[] testAssignment = new boolean[values.length];
        int testValue = 0;

        bestAssignment = new boolean[values.length];
        totalValue = Arrays.stream(values).sum();
        bestErr = totalValue;
        unassignedValue = totalValue;

        findPartition(values, startIndex, totalValue, unassignedValue, testAssignment, testValue);
    }

    // Utility function to print the subsets
    public static void printSubsets(int[] arr) {
        ArrayList<Integer> subset1 = new ArrayList<>();
        ArrayList<Integer> subset2 = new ArrayList<>();

        for (int i = 0; i < bestAssignment.length; i++) {
            if (bestAssignment[i])
                subset1.add(arr[i]);
            else
                subset2.add(arr[i]);
        }
        System.out.println("Subset 1: " + subset1);
        System.out.println("Subset 2: " + subset2);
    }

    public static void findPartition(int[] values,
            int startIndex, int totalValue, int unassignedValue,
            boolean[] testAssignment, int testValue) {

        // If startIndex is beyond the end of the array, then all entries have been
        // assigned.
        if (startIndex >= values.length) {
            // We're done. Check if this assignment is better than what we have so far.
            int testErr = Math.abs(2 * testValue - totalValue);
            if (testErr < bestErr) {
                // Found improvement, update the bestErr
                bestErr = testErr;
                bestAssignment = Arrays.copyOf(testAssignment, testAssignment.length);

                // System.out.println(Arrays.toString(bestAssignment));
                // System.out.println(bestErr);
            }
        } else {
            // See if there's any way we can assign
            // the remaining items to improve the solution.
            int testErr = Math.abs(2 * testValue - totalValue);
            if (testErr - unassignedValue < bestErr) {
                // There's a chance we can make an improvement.
                // We will now assign the next item.
                unassignedValue -= values[startIndex];

                // Try adding values[startIndex] to set 1.
                testAssignment[startIndex] = true;
                findPartition(values, startIndex + 1,
                        totalValue, unassignedValue,
                        testAssignment, testValue + values[startIndex]);

                // Try adding values[startIndex] to set 2.
                testAssignment[startIndex] = false;
                findPartition(values, startIndex + 1,
                        totalValue, unassignedValue,
                        testAssignment, testValue);
            }
        }
    }
}