
/**
 * Bottom view:
 * The Bottom View showcases nodes visible from the bottom of the tree when
 * viewed from below.
 * It shows the nodes at the lowest vertical level.
 * Example:
 * 
 * A
 * / \
 * B C
 * / / \
 * D E F
 * \
 * G
 * 
 * Bottom View: D E G F
 * 
 */

import java.util.*;

class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;

    public TreeNode(char val) {
        this.val = val;
        left = null;
        right = null;
    }
}

class NodeInfo {
    TreeNode node;
    int hd;

    public NodeInfo(TreeNode node, int hd) {
        this.node = node;
        this.hd = hd;
    }
}

public class BottomViewOfTree {

    public static List<Character> bottomView(TreeNode root) {
        List<Character> bottomView = new ArrayList<>();
        if (root == null) {
            return bottomView;
        }
        Map<Integer, Character> bottomMap = new TreeMap<>();
        Queue<NodeInfo> nodeQueue = new LinkedList<>();
        nodeQueue.offer(new NodeInfo(root,
                0));
        while (!nodeQueue.isEmpty()) {
            NodeInfo info = nodeQueue.poll();
            TreeNode node = info.node;
            int hd = info.hd;
            bottomMap.put(hd, node.val);
            if (node.left != null) {
                nodeQueue.offer(new NodeInfo(node.left, hd - 1));
            }
            if (node.right != null) {
                nodeQueue.offer(new NodeInfo(node.right, hd + 1));
            }
        }
        for (char value : bottomMap.values()) {
            bottomView.add(value);
        }
        return bottomView;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('C');
        root.left.left = new TreeNode('D');
        root.right.left = new TreeNode('E');
        root.right.right = new TreeNode('F');
        root.right.right.right = new TreeNode('G');
        List<Character> bottomViewResult = bottomView(root);
        // Printing the Bottom View
        System.out.print("Bottom View: ");
        for (char node : bottomViewResult) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
