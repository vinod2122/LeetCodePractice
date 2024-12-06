package dailyPractice;
/*
 * 
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.

Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

 

Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
Output: [4,1]
 

Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ui, vi <= n
ui != vi

 */
public class Solution14 {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] candidate1 = null, candidate2 = null;
        
        // Step 1: Check for nodes with two parents
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (parent[v] != 0) {
                // Node v already has a parent
                candidate1 = new int[] {parent[v], v};
                candidate2 = new int[] {u, v};
                edge[1] = 0; // Temporarily remove the second edge
            } else {
                parent[v] = u;
            }
        }
        
        // Step 2: Union-Find to check for cycles
        int[] root = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            root[i] = i;
        }
        
        for (int[] edge : edges) {
            if (edge[1] == 0) continue; // Skip the temporarily removed edge
            int u = edge[0], v = edge[1];
            int rootU = find(root, u);
            int rootV = find(root, v);
            if (rootU == rootV) {
                // Cycle detected
                if (candidate1 == null) {
                    return edge; // No two-parent case, return the edge causing the cycle
                }
                return candidate1; // Two-parent case, return the earlier edge
            }
            root[rootV] = rootU; // Union
        }
        
        // Step 3: If no cycle, return candidate2 (second edge in two-parent case)
        return candidate2;
    }
    
    private int find(int[] root, int x) {
        if (root[x] != x) {
            root[x] = find(root, root[x]); // Path compression
        }
        return root[x];
    }

}
