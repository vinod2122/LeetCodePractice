package dailyPractice;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Solution_12_Nov_24 {
	
	public static void main (String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // Number of test cases

        while (T-- > 0) {
            int N = sc.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }

            // Sort the array A to process smallest elements first
            Arrays.sort(A);

            boolean canBePermutation = true;
            int requiredValue = 1;  // We need to construct numbers from 1 to N

            for (int i = 0; i < N; i++) {
                // If current element is less than required, move to next larger element
                if (A[i] <= requiredValue) {
                    requiredValue++;  // Assign this value and move to next required number
                } else {
                    canBePermutation = false;  // If we can't meet the requirement, permutation is not possible
                    break;
                }
            }

            // Output result for each test case
            System.out.println(canBePermutation ? "YES" : "NO");
        }
        sc.close();
    }

}
