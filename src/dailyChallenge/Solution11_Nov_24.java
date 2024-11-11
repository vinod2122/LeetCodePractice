package dailyChallenge;

public class Solution11_Nov_24 {
	public boolean primeSubOperation(int[] nums) {
        int maxNum = getMaxElement(nums);

        // Create a Sieve of Eratosthenes array to identify prime numbers
        boolean[] isPrime = new boolean[maxNum + 1];
        fill(isPrime, true);
        isPrime[1] = false;  // 1 is not prime
        for (int num = 2; num <= Math.sqrt(maxNum); num++) {
            if (isPrime[num]) {
                for (int multiple = num * num; multiple <= maxNum; multiple += num) {
                    isPrime[multiple] = false;
                }
            }
        }

        // Check if array can be made strictly increasing by subtracting prime numbers
        int requiredValue = 1;
        int index = 0;
        while (index < nums.length) {
            int difference = nums[index] - requiredValue;

            // Return false if the current number is already smaller than required value
            if (difference < 0) {
                return false;
            }

            // Move to next number if difference is prime or zero
            if (isPrime[difference] || difference == 0) {
                index++;
                requiredValue++;
            } else {
                requiredValue++;
            }
        }
        return true;
    }

    // Helper method to find maximum element in array
    private int getMaxElement(int[] nums) {
        int maxElement = -1;
        for (int num : nums) {
            if (num > maxElement) {
                maxElement = num;
            }
        }
        return maxElement;
    }

    // Helper method to initialize boolean array
    private void fill(boolean[] array, boolean value) {
        for (int i = 0; i < array.length; i++) {
            array[i] = value;
        }
    }

}
