package dailyChallenge;
/*
 * 
 * You are given two strings start and target, both of length n. Each string consists only of the characters 'L', 'R', and '_' where:

The characters 'L' and 'R' represent pieces, where a piece 'L' can move to the left only if there is a blank space directly to its left, and a piece 'R' can move to the right only if there is a blank space directly to its right.
The character '_' represents a blank space that can be occupied by any of the 'L' or 'R' pieces.
Return true if it is possible to obtain the string target by moving the pieces of the string start any number of times. Otherwise, return false.

 

Example 1:

Input: start = "_L__R__R_", target = "L______RR"
Output: true
Explanation: We can obtain the string target from start by doing the following moves:
- Move the first piece one step to the left, start becomes equal to "L___R__R_".
- Move the last piece one step to the right, start becomes equal to "L___R___R".
- Move the second piece three steps to the right, start becomes equal to "L______RR".
Since it is possible to get the string target from start, we return true.
Example 2:

Input: start = "R_L_", target = "__LR"
Output: false
Explanation: The 'R' piece in the string start can move one step to the right to obtain "_RL_".
After that, no pieces can move anymore, so it is impossible to obtain the string target from start.
Example 3:

Input: start = "_R", target = "R_"
Output: false
Explanation: The piece in the string start can move only to the right, so it is impossible to obtain the string target from start.
 

Constraints:

n == start.length == target.length
1 <= n <= 105
start and target consist of the characters 'L', 'R', and '_'.

 */
public class Solution05_Dec_24 {
	    public boolean canChange(String start, String target) {
	        if (start.equals(target)) {
	            return true;
	        }
	        
	        int leftToMove = 0; // Tracks 'L' pieces that need to move left
	        int rightToMove = 0; // Tracks 'R' pieces that need to move right
	        
	        for (int i = 0; i < start.length(); i++) {
	            char currentChar = start.charAt(i);
	            char targetChar = target.charAt(i);
	            
	            // Current character is 'R', it may need to move right
	            if (currentChar == 'R') {
	                if (leftToMove > 0) {
	                    return false; // An 'R' cannot move past 'L'
	                }
	                rightToMove++;
	            }
	            
	            // Target character is 'L', it may need to move left
	            if (targetChar == 'L') {
	                if (rightToMove > 0) {
	                    return false; // An 'L' cannot move past 'R'
	                }
	                leftToMove++;
	            }
	            
	            // Target character is 'R', confirm if we can move an 'R' to this position
	            if (targetChar == 'R') {
	                if (rightToMove == 0) {
	                    return false; // No available 'R' to move here
	                }
	                rightToMove--;
	            }
	            
	            // Current character is 'L', confirm if we can move an 'L' to its target
	            if (currentChar == 'L') {
	                if (leftToMove == 0) {
	                    return false; // No available 'L' to move here
	                }
	                leftToMove--;
	            }
	        }
	        
	        // Ensure no leftover 'L' or 'R' movements are pending
	        return leftToMove == 0 && rightToMove == 0;
	    }

}
