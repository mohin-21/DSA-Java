package BinarySearchTree;

import java.util.ArrayList;

public class BSTImplement {
     static class Node{
        int val;
        Node left,right;
        Node(int v ){
            this.val = v;
            this.left = null;
            this.right = null;
        }
    }
    public static Node insert(Node root,int val){//insert in bst-> binary search tree
        if(root == null){
            root = new Node(val);
            return root;
        }
       
        if(root.val > val){
            root.left = insert(root.left,val);
        }else{
            root.right = insert(root.right, val);
        }
        
        return root;
    }
    public static void inorder(Node root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.val+" ");
        inorder(root.right);
    }
    //search in bst
    public static boolean search(Node root,int key){
        if(root == null){
            return false;
        }
        if(root.val ==key ){
            return true;
        }else if(root.val > key){
            return search(root.left,key);
        }else{
           return search(root.right,key);
        }
        
    }
    //delete a node a bst
    public static Node delete(Node root,int key){
        if(root == null){
            return null;
        }
        if(root.val > key){
            root.left = delete(root.left, key);
        }else if(root.val< key){
            root.right = delete(root.right, key);
        }else{
            //case 1 leaf node
            if(root.left == null && root.right == null){
                return null;
            }
            //case 2 single child
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            //case 3 both childeren
            Node newRoot = inorderSuccessor(root.right);
            root.val = newRoot.val;
            root.right = delete(root.right, newRoot.val);
        }
        return root;

    }
    public static Node inorderSuccessor(Node root){
        while(root.left != null){
            root = root.left;
        }
        return root;
    }
    //print in range 
    public static void printInRange(Node root,int k1,int k2){//this code doesn't produce right key in all cases 
        if(root == null){
            return;
        }
        if(k1 < root.val){
            printInRange(root.left, k1, k2);
        }else if(k1 <= root.val && root.val <= k2){
            System.out.print(root.val+" ");
        }
        printInRange(root.right, k1, k2);
        // if(root.val >= k1 && root.val >= k2){
        //     printInRange(root.left, k1, k2);
        //     System.out.print(root.val+" ");
        //     printInRange(root.right, k1, k2);
        // }else if( root.val >k2){
        //     printInRange(root.left, k1, k2);
        // }else{
        //     printInRange(root.right, k1, k2);
        // }
    }
    //getInorder
    public static void getInorder(Node root,ArrayList<Integer> range){
        if(root == null){
            return;
        }
        getInorder(root.left,range);
        range.add(root.val);
        getInorder(root.right, range);
    }

    public static void printInRange2(Node root,int k1,int k2){
        ArrayList<Integer> range = new ArrayList<>();
        getInorder(root, range);
        for(int i = 0;i<range.size();i++){
            if(k1<= range.get(i) && range.get(i)<=k2){
                System.out.print(range.get(i) + " ");
            }
        }
        
    }
    public static void printPath(ArrayList<Integer> path){
        for(int i = 0;i<path.size();i++){
            System.out.print(path.get(i)+ " ");
        }System.out.println();
    }
    //root to leaf path
    public static void rootToLeaf(Node root,ArrayList<Integer> path){//root to leaf all path
        if(root == null){
            return;
        }
        path.add(root.val);
       
        if(root.left == null && root.right == null){
            printPath(path);
        }
        rootToLeaf(root.left,path);
        rootToLeaf(root.right,path);
        path.remove(path.size()-1);
    }

    //validate bst
    public static boolean isValideBST(Node root,Node min,Node max){
        if(root == null){
            return true;
        }
        if(min != null && root.val <= min.val){
            return false;

        }
        if(max != null && root.val >= max.val){
            return false;

        }
        return isValideBST(root.left, min, root) && isValideBST(root.right, root, max);
    }

    public static void main(String[] args){
        Node root = null;
        int keys[] = { 1,4,3,2,4,2,5,4,6};
        for (int x : keys){
            root = insert(root, x);
        }
    }
}
