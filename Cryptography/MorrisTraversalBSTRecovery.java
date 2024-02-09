import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class MorrisTraversalBSTRecovery {

    public void recoverBST(TreeNode root) {
        TreeNode prev = null;
        TreeNode curr = root;
        
        TreeNode first = null;
        TreeNode second = null;

        while (curr != null) {
            if (curr.left == null) {
                if (prev != null && prev.val > curr.val) {
                    if (first == null) {
                        first = prev;
                        second = curr;
                    } else {
                        second = curr;
                    }
                }
                prev = curr;
                curr = curr.right;
            } else {
                TreeNode pred = curr.left;
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    pred.right = null;
                    if (prev != null && prev.val > curr.val) {
                        if (first == null) {
                            first = prev;
                            second = curr;
                        } else {
                            second = curr;
                        }
                    }
                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        // Swap the values of the first and second nodes
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    public static void main(String[] args) {
        /*
         * Constructing the following tree:
         * 3
         * / \
         * 1 4
         * \
         * 2
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        MorrisTraversalBSTRecovery morris = new MorrisTraversalBSTRecovery();
        morris.recoverBST(root);

        System.out.println("BST after recovery:");
        inorderTraversal(root);
    }

    public static void inorderTraversal(TreeNode root) {
        if (root == null)
            return;
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }
}
