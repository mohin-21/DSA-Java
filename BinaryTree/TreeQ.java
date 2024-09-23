package BinaryTree;

import java.util.*;

public class TreeQ {
    static class Node {
        int val;
        Node left;
        Node right;

        Node(int v) {
            this.val = v;
            this.left = null;
            this.right = null;
        }
    }

    // subtree of another tree
    public static boolean isIdentical(Node root, Node subRoot) {
        if (root == null && subRoot == null) {
            return true;
        } else if (root == null || subRoot == null || root.val != subRoot.val) {
            return false;
        }
        if (!isIdentical(root.left, subRoot.left)) {
            return false;
        }
        if (!isIdentical(root.right, subRoot.right)) {
            return false;
        }
        return true;
    }

    public static boolean isSubtree(Node root, Node subRoot) {
        if (root == null) {
            return false;
        }

        if (root.val == subRoot.val) {
            if (isIdentical(root, subRoot)) {
                return true;
            }
        }
        boolean left = isSubtree(root.left, subRoot);
        boolean right = isSubtree(root.right, subRoot);
        return left || right;
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        return Math.max(lh, rh) + 1;
    }

    // diameter a tree -> longest path between two nodes
    public static int diameter(Node root) {// O(n^2)
        if (root == null) {
            return 0;
        }
        int leftDia = diameter(root.left);
        int lh = height(root.left);
        int rightDia = diameter(root.right);
        int rh = height(root.right);
        int selfDia = lh + rh + 1;
        return Math.max(selfDia, Math.max(leftDia, rightDia));
    }

    // calculate diameter2 complexity O(n)
    static class Info {
        int dia;
        int ht;

        Info(int d, int h) {
            this.dia = d;
            this.ht = h;
        }
    }

    public static Info diameter2(Node root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftDia = diameter2(root.left);
        Info rightDia = diameter2(root.right);
        // int dia =
        // Math.max((leftDia.ht+rightDia.ht+1),Math.max(leftDia.dia,rightDia.dia));
        // int ht = Math.max(leftDia.ht,rightDia.ht)+1;
        int ld = leftDia.dia;
        int rd = rightDia.dia;
        int lh = leftDia.ht;
        int rh = rightDia.ht;
        int ht = Math.max(lh, rh) + 1;// selft diameter
        int selfDia = lh + rh + 1;
        int dia = Math.max(selfDia, Math.max(ld, rd));

        return new Info(dia, ht);
    }

    // kth levle of a tree
    public static void kthLevel(Node root, int k) {
        if (root == null) {
            return;
        }
        int level = 1;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            if (cur == null) {
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                    level++;
                }
            } else {
                if (k == level) {
                    System.out.print(cur.val + " ");
                }
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }

            }
        }
    }

    // kth level using recursion
    public static void kthLevel2(Node root, int k, int l) {
        if (root == null) {
            return;
        }

        kthLevel2(root.left, k, l + 1);
        kthLevel2(root.right, k, l + 1);

    }

    // lowest common ancestor
    public static int lca(Node root, int n1, int n2) {
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();
        getPath(root, n1, path1);
        getPath(root, n2, path2);

        int i = 0;
        for (; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) != path2.get(i)) {
                break;
            }
        }
        return path1.get(i - 1).val;
    }

    public static boolean getPath(Node root, int n, ArrayList<Node> path) {
        if (root == null) {
            return false;
        }
        path.add(root);
        if (root.val == n) {
            return true;
        }
        boolean left = getPath(root.left, n, path);
        boolean right = getPath(root.right, n, path);
        if (left || right) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;

    }

    // lca approach 2
    public static Node lca2(Node root, int n1, int n2) {
        if (root == null) {
            return null;
        }
        if (root.val == n1 || root.val == n2) {
            return root;
        }
        Node leftLca = lca2(root.left, n1, n2);
        Node rightLca = lca2(root.right, n1, n2);
        if (rightLca == null) {
            return leftLca;
        }
        if (leftLca == null) {
            return rightLca;
        }
        return root;

    }

    // min distance between two nodes
    public static int minDist(Node root, int n1, int n2) {
        Node lca = lca2(root, n1, n2);
        int leftDist = lcaDist(lca, n1);
        int rightDist = lcaDist(lca, n2);
        return leftDist + rightDist;
    }

    public static int lcaDist(Node root, int n) {
        if (root == null) {
            return -1;
        }
        if (root.val == n) {
            return 0;
        }
        int leftDist = lcaDist(root.left, n);
        int rightDist = lcaDist(root.right, n);
        if (leftDist == -1 && rightDist == -1) {
            return -1;
        } else if (leftDist == -1) {
            return rightDist + 1;
        } else {
            return leftDist + 1;
        }

    }

    // kth ancestor
    public static int kthAncestor(Node root, int k, int n) {
        if (root == null) {
            return -1;
        }
        if (root.val == n) {
            return 0;
        }
        int left = kthAncestor(root.left, k, n);
        int right = kthAncestor(root.right, k, n);
        if (left == -1 && right == -1) {
            return -1;
        }
        int max = Math.max(left, right);
        if (max + 1 == k) {
            System.out.println(root.val);
        }
        return max + 1;
    }

    // sum tree
    public static int sumTree(Node root) {
        if (root == null) {
            return 0;
        }
        int data = root.val;
        int left = sumTree(root.left);
        int right = sumTree(root.right);
        int newLeft = root.left == null ? 0 : root.left.val;
        int newRight = root.right == null ? 0 : root.right.val;
        root.val = newLeft + left + right + newRight;
        return data;

    }

    // top view of a binary tree
    static class Details {
        int ht;
        Node node;

        Details(int h, Node n) {
            this.ht = h;
            this.node = n;
        }
    }

    public static void topView(Node root) {
        HashMap<Integer, Node> map = new HashMap<>();
        Queue<Details> q = new LinkedList<>();
        int min = 0, max = 0;
        q.add(new Details(0, root));
        q.add(null);
        while (!q.isEmpty()) {
            Details cur = q.remove();
            if (cur == null) {
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }

            } else {
                if (!map.containsKey(cur.ht)) {
                    map.put(cur.ht, cur.node);
                }
                if (cur.node.left != null) {
                    q.add(new Details(cur.ht - 1, cur.node.left));
                    min = Math.min(min, cur.ht - 1);
                }
                if (cur.node.right != null) {
                    q.add(new Details(cur.ht + 1, cur.node.right));
                    max = Math.max(max, cur.ht + 1);
                }

            }

        }
        for (int i = min; i <= max; i++) {
            System.out.print(map.get(i).val + " ");
        }

    }

    // Questions binary tree
    public static boolean isUnivalued(Node root) {// all nodes have the same is called univalued binary tree
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val != root.left.val) {
            return false;
        }
        if (root.right != null && root.val != root.right.val) {
            return false;
        }
        return isUnivalued(root.left) && isUnivalued(root.right);
    }

    // miror or invert of a binary tree
    public static Node miror(Node root) {
        if (root == null) {
            return null;
        }
        Node left = miror(root.left);
        Node right = miror(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static Node delete(Node root, int n) {
        if (root == null) {
            return null;
        }
        root.left = delete(root.left, n);
        root.right = delete(root.right, n);
        if (root.val == n) {
            // case 1 leaf node
            if (root.left == null && root.right == null) {
                return null;
            }
            // case 2 single child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // case 3 both children
            return root.right;

        }

        return root;

    }

    public static Node deleteLeaves(Node root, int n) {
        if (root == null) {
            return null;
        }
        root.left = deleteLeaves(root.left, n);
        root.right = deleteLeaves(root.right, n);
        if (root.val == n && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }
    
    // find all duplicate subtrees
    //  code

    // maximum path sum
    // public static int maxPathSum(Node root){//this code is wrong
    // if(root == null){
    // return 0;
    // }
    // int leftSum = maxPathSum(root.left);
    // int rightSum = maxPathSum(root.right);
    // //int selfSum = leftSum+rightSum+root.val;
    // int maxSingle = Math.max(Math.max(leftSum,rightSum)+root.val,root.val);
    // int maxTop = Math.max(maxSingle,leftSum+rightSum+root.val);
    // //return Math.max(selfSum,Math.max(leftSum,rightSum));
    // //return Math.max(maxSingle,maxTop);
    // return maxSingle;
    // }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        System.out.println(root);

    }

}
