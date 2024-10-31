package vk;

public class Solution31_10 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        addRow(root, val, depth, 1);
        return root;
    }
    
    private void addRow(TreeNode node, int val, int depth, int currentDepth) {
        if (node == null) {
            return;
        }
        
        if (currentDepth == depth - 1) {
            TreeNode tempLeft = node.left;
            TreeNode tempRight = node.right;
            node.left = new TreeNode(val, tempLeft, null);
            node.right = new TreeNode(val, null, tempRight);
        } else {
            addRow(node.left, val, depth, currentDepth + 1);
            addRow(node.right, val, depth, currentDepth + 1);
        }
    }

    public static void main(String[] args) {
        Solution31_10 solution = new Solution31_10();
        
        // Example tree creation
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(3), new TreeNode(1));
        root.right = new TreeNode(6, new TreeNode(5), null);
        
        // Add a row with value 1 at depth 2
        TreeNode modifiedRoot = solution.addOneRow(root, 1, 2);
        
        // Method to print the tree (You may add a method to visualize the output)
        System.out.println("Tree modified.");
    }
}
