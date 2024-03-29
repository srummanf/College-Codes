
/**
 * EXPLANATION
 Consider the following binary tree as an 
example:
         1
       /   \
      2     3
     / \   / \
    4   5 6   7
 Vertical Order Traversal for this tree 
would produce the following output:
 Vertical Order Traversal for this 
tree would produce the following 
output:
 4
 2
 1 5 6
 3
 7

 */


import java.util.*;
import java.util.AbstractMap.SimpleEntry;

class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
                this.val = val;
                left = null;
                right = null;
        }
}

public class VerticalOrderTraversal {

        public static List<List<Integer>> verticalOrderTraversal(TreeNode root) {

                List<List<Integer>> result = new ArrayList<>();
                if (root == null) {
                        return result;
                }

                Map<Integer, List<Integer>> verticalMap = new TreeMap<>();
                Queue<SimpleEntry<TreeNode, Integer>> nodeQueue = new LinkedList<>();

                nodeQueue.offer(new SimpleEntry<>(root, 0));

                while (!nodeQueue.isEmpty()) {
                        SimpleEntry<TreeNode, Integer> entry = nodeQueue.poll();

                        TreeNode node = entry.getKey();
                        int col = entry.getValue();
                        
                        verticalMap.computeIfAbsent(col, k -> new ArrayList<>()).add(node.val);
                        if (node.left != null) {
                                nodeQueue.offer(new SimpleEntry<>(node.left, col - 1));
                        }

                        if (node.right != null) {
                                nodeQueue.offer(new SimpleEntry<>(node.right, col + 1));
                        }
                }
                for (List<Integer> values : verticalMap.values()) {
                        result.add(values);
                }
                return result;
        }

        public static void main(String[] args) {
                // Sample binary tree input
                TreeNode root = new TreeNode(1);
                root.left = new TreeNode(2);
                root.right = new TreeNode(3);
                root.left.left = new TreeNode(4);
                root.left.right = new TreeNode(5);
                root.right.left = new TreeNode(6);
                root.right.right = new TreeNode(7);
                List<List<Integer>> verticalOrderResult = verticalOrderTraversal(root);

                for (List<Integer> column : verticalOrderResult) {
                        for (int val : column) {
                                System.out.print(val + " ");
                        }
                        System.out.println();
                }
        }
}
