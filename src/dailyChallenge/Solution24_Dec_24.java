package dailyChallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 
 * There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1, respectively. You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.

You must connect one node from the first tree with another node from the second tree with an edge.

Return the minimum possible diameter of the resulting tree.

The diameter of a tree is the length of the longest path between any two nodes in the tree.

 

Example 1:

Input: edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]

Output: 3

Explanation:

We can obtain a tree of diameter 3 by connecting node 0 from the first tree with any node from the second tree.

Example 2:


Input: edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]

Output: 5

Explanation:

We can obtain a tree of diameter 5 by connecting node 0 from the first tree with node 0 from the second tree.

 

Constraints:

1 <= n, m <= 105
edges1.length == n - 1
edges2.length == m - 1
edges1[i].length == edges2[i].length == 2
edges1[i] = [ai, bi]
0 <= ai, bi < n
edges2[i] = [ui, vi]
0 <= ui, vi < m
The input is generated such that edges1 and edges2 represent valid trees.
 */
public class Solution24_Dec_24 {
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int nodesTree1 = edges1.length + 1;
        int nodesTree2 = edges2.length + 1;
        List<List<Integer>> adjacencyListTree1 = new ArrayList<>();
        List<List<Integer>> adjacencyListTree2 = new ArrayList<>();
        
        for (int i = 0; i < nodesTree1; i++) adjacencyListTree1.add(new ArrayList<>());
        for (int i = 0; i < nodesTree2; i++) adjacencyListTree2.add(new ArrayList<>());

        // Create adjacency lists for both trees
        for (int[] edge : edges1) {
            adjacencyListTree1.get(edge[0]).add(edge[1]);
            adjacencyListTree1.get(edge[1]).add(edge[0]);
        }
        for (int[] edge : edges2) {
            adjacencyListTree2.get(edge[0]).add(edge[1]);
            adjacencyListTree2.get(edge[1]).add(edge[0]);
        }

        // Find longest diameter in Tree 1
        boolean[] visitedTree1 = new boolean[nodesTree1];
        int[] diameterInfoTree1 = findDiameter(adjacencyListTree1, visitedTree1, 0);
        visitedTree1 = new boolean[nodesTree1];
        diameterInfoTree1 = findDiameter(adjacencyListTree1, visitedTree1, diameterInfoTree1[0]);
        
        // Find longest diameter in Tree 2
        boolean[] visitedTree2 = new boolean[nodesTree2];
        int[] diameterInfoTree2 = findDiameter(adjacencyListTree2, visitedTree2, 0);
        visitedTree2 = new boolean[nodesTree2];
        diameterInfoTree2 = findDiameter(adjacencyListTree2, visitedTree2, diameterInfoTree2[0]);

        // Find maximum diameter between Tree 1 and Tree 2
        int maxIndividualDiameter = Math.max(diameterInfoTree1[1], diameterInfoTree2[1]);

        // Find diameter in the combined tree
        int combinedDiameter = (diameterInfoTree1[1] + 1) / 2 + (diameterInfoTree2[1] + 1) / 2 + 1;

        // Return maximum of the individual and combined diameters
        return Math.max(maxIndividualDiameter, combinedDiameter);
    }

    // Helper method to find diameter
    // Returns {FarthestNode, Diameter}
    static int[] findDiameter(List<List<Integer>> adjacencyList, boolean[] visited, int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        visited[startNode] = true;
        int depth = 0;
        int farthestNode = startNode;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;
            for (int i = 0; i < levelSize; i++) {
                int currentNode = queue.poll();
                farthestNode = currentNode;
                visited[currentNode] = true;
                for (int neighbor : adjacencyList.get(currentNode)) {
                    if (!visited[neighbor]) queue.add(neighbor);
                }
            }
        }
        return new int[]{farthestNode, depth - 1};
    }

}
