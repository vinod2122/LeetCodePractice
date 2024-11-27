package dailyPractice;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * You are given an integer array cards of length 4. You have four cards, each containing a number in the range [1, 9]. You should arrange the numbers on these cards in a mathematical expression using the operators ['+', '-', '*', '/'] and the parentheses '(' and ')' to get the value 24.

You are restricted with the following rules:

The division operator '/' represents real division, not integer division.
For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
You cannot concatenate numbers together
For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.
Return true if you can get such expression that evaluates to 24, and false otherwise.

 

Example 1:

Input: cards = [4,1,8,7]
Output: true
Explanation: (8-4) * (7-1) = 24
Example 2:

Input: cards = [1,2,1,2]
Output: false
 

Constraints:

cards.length == 4
1 <= cards[i] <= 9
 */
public class Solution10 {
    public boolean judgePoint24(int[] cards) {
        // Convert the array to a list of doubles
        List<Double> nums = new ArrayList<>();
        for (int card : cards) {
            nums.add((double) card);
        }
        return canMake24(nums);
    }

    private boolean canMake24(List<Double> nums) {
        // Base case: if we have only one number left, check if it is 24
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24.0) < 1e-6; // Allow a small tolerance for floating point comparison
        }

        // Try all pairs of numbers and perform all operations between them
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i != j) {
                    // Get the two numbers to combine
                    double a = nums.get(i);
                    double b = nums.get(j);

                    // Prepare a new list for the next recursion level
                    List<Double> nextNums = new ArrayList<>();
                    for (int k = 0; k < nums.size(); k++) {
                        if (k != i && k != j) {
                            nextNums.add(nums.get(k));
                        }
                    }

                    // Try all operations
                    List<Double> results = new ArrayList<>();
                    results.add(a + b); // addition
                    results.add(a - b); // subtraction
                    results.add(a * b); // multiplication
                    if (b != 0) {
                        results.add(a / b); // division
                    }

                    // Recursively try each result
                    for (double result : results) {
                        nextNums.add(result);
                        if (canMake24(nextNums)) {
                            return true;
                        }
                        nextNums.remove(nextNums.size() - 1); // Backtrack
                    }
                }
            }
        }
        return false; // No way to make 24
    }

}
