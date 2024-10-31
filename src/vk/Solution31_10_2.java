package vk; 
import java.util.List;
import java.util.PriorityQueue;

class Solution31_10_2 {
    public int[] smallestRange(List<List<Integer>> nums) {
        // Priority queue to maintain the minimum element at the top (min-heap)
        PriorityQueue<Element> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);
        
        int max = Integer.MIN_VALUE;
        
        // Add the first element from each list to the min-heap
        for (int i = 0; i < nums.size(); i++) {
            int value = nums.get(i).get(0);
            minHeap.offer(new Element(value, i, 0));
            max = Math.max(max, value);
        }
        
        // Initialize the smallest range with the max possible values
        int rangeStart = -100000;
        int rangeEnd = 100000;
        
        // Process elements in the min-heap
        while (true) {
            Element minElement = minHeap.poll();
            int min = minElement.value;
            
            // Update the range if the current range is smaller
            if (max - min < rangeEnd - rangeStart || 
                (max - min == rangeEnd - rangeStart && min < rangeStart)) {
                rangeStart = min;
                rangeEnd = max;
            }
            
            // Move to the next element in the current list
            if (minElement.index + 1 < nums.get(minElement.listIndex).size()) {
                int nextValue = nums.get(minElement.listIndex).get(minElement.index + 1);
                minHeap.offer(new Element(nextValue, minElement.listIndex, minElement.index + 1));
                max = Math.max(max, nextValue);
            } else {
                // If we reach the end of one list, break out as we can't cover all lists anymore
                break;
            }
        }
        
        return new int[]{rangeStart, rangeEnd};
    }
    
    // Helper class to store element information
    private static class Element {
        int value;
        int listIndex;
        int index;
        
        Element(int value, int listIndex, int index) {
            this.value = value;
            this.listIndex = listIndex;
            this.index = index;
        }
    }
}

