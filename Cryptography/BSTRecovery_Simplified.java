import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class BSTRecovery_Simplified {
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        traverse(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void traverse(TreeNode root) {
        if (root == null)
            return;

        traverse(root.left);

        if (first == null && prev.val >= root.val) {
            first = prev;
        }

        if (first != null && prev.val >= root.val) {
            second = root;
        }

        prev = root;

        traverse(root.right);
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

        BSTRecovery_Simplified recoverBST = new BSTRecovery_Simplified();
        recoverBST.recoverTree(root);

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
