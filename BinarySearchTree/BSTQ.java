package BinarySearchTree;
import java.util.*;

public class BSTQ {
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

     //getInorder
     public static void getInorder(Node root,ArrayList<Integer> range){
        if(root == null){
            return;
        }
        getInorder(root.left,range);
        range.add(root.val);
        getInorder(root.right, range);
    }
    
    //mirror of a binary tree
    public static Node mirror(Node root){
        if(root == null){
            return null;
        }
        Node leftChild = mirror(root.left);
        Node rightChild = mirror(root.right);
        root.left = rightChild;
        root.right = leftChild;
        return root;
        
    }
    //sorted array to balanced BST
    public static Node createBST(int arr[],int st,int end){
        if(st > end){
            return null;
        }
        int mid = (st+end) /2;//st+(end - st)/2;
        Node root = new Node(arr[mid]);
        root.left = createBST(arr,st,mid-1);
        root.right = createBST(arr,mid+1,end);
        return root;
    }
    public static void preorder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }
    public static Node createBST2(ArrayList<Integer> inorder,int st,int end){
        if(st > end){
            return null;
        }
        int mid = (st+end) /2;//st+(end - st)/2;
        Node root = new Node(inorder.get(mid));
        root.left = createBST2(inorder,st,mid-1);
        root.right = createBST2(inorder,mid+1,end);
        return root;
    }
    //convet bst to balanced bst
    public static Node BST_Balanced(Node root){
        ArrayList<Integer> inorder = new ArrayList<>();
        getInorder(root, inorder);
        return createBST2(inorder, 0,inorder.size()-1);   
    }
    //size of largest a BST in a BT
    static class Info{
        int size;
        int min;
        int max;
        boolean isBST;
        Info(boolean isBST,int min,int max,int s){
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.size = s;

        }
    }
    static int maxSize = 0;
    public static Info largestBST(Node root){
        if(root == null){
            return new Info(true,Integer.MAX_VALUE,Integer.MIN_VALUE,0);
            
        }
        
        Info left = largestBST(root.left);
        Info right = largestBST(root.right);
        int min = Math.min(root.val,Math.min(left.min,right.min));
        int max = Math.max(root.val,Math.max(left.max,right.max));
        int size = left.size + right.size +1;
        if(root.val >= right.min || root.val <= left.max){
            return new Info(false,min,max,size);
        }
        if(left.isBST && right.isBST){
            maxSize = Math.max(maxSize,size);
            System.out.println(maxSize);
            return new Info(true, min, max, size);
        }
        return new Info(false,min,max,size);

    }
    //merger two BST
    public static Node mergeBST(Node root1,Node root2){
        ArrayList<Integer> r1 = new ArrayList<>();
        ArrayList<Integer> r2 = new ArrayList<>();
        getInorder(root1,r1);
        getInorder(root2, r2);
        int i =0,j =0;
        ArrayList<Integer> com = new ArrayList<>();
        while(i< r1.size() && j<r2.size()){
            if(r1.get(i) < r2.get(j)){
                com.add(r1.get(i));
                i++;
            }else{
                com.add(r2.get(j));
                j++;
            }
        }
        while(i< r1.size()){
            com.add(r1.get(i));
            i++;
        }
        while(j<r2.size()){
            com.add(r2.get(j));
            j++;
        }
        return createBST2(com, 0, com.size()-1);
    }

    //Qusestion 
    public static int rangeSum(Node root,int L,int R){
        ArrayList<Integer> ele = new ArrayList<>();
        getInorder(root, ele);
        int sum = 0;
        for(int i = 0;i<ele.size();i++){
            int cur = ele.get(i);
            if(L <= cur && cur <= R){
                sum+=cur;
            }
        }
        return sum;
    }
    //closest element
    public static Node closest(Node root,int k){//wrong code
        if(root == null){
            return null;
        }
        int min = Integer.MAX_VALUE;
        Node node = null;
        int cur = root.val - k;
        while(root != null){
            if(cur < min){
                Node curNode = root;
                min = Math.min(min,cur);
                node = curNode;
            }
            closest(root.left, k);
            closest(root.right,k);
        }
        
        return node;

    }
    static int min = Integer.MAX_VALUE;
    static Node minNode = null;
    public static Node close(Node root,int k){
        if(root == null){
            return null;
        }
       
        if(root.val == k){
            minNode = root;
            return minNode;
        }
        if(min > Math.abs(root.val-k)){
            min = Math.abs(root.val - k);
            minNode = root;

        }
        
        if(root.val > k){
            close(root.left,k);
        }else{
            close(root.right,k);
        }
        return minNode;

    }
    static int count = 0;
    //kth smallest element
    public static Node kthSmallest(Node root,int k){
        // ArrayList<Integer> el = new ArrayList<>();
        // getInorder(root, el);
        // for(int i = 0;i<el.size();i++){
        //     if(i == k){
        //         System.out.println(el.get(i));
        //         break;
        //     }
        // }
        if(root == null){
            return null;
        }
        Node left = kthSmallest(root.left, k);
        if(left != null){
            System.out.println(left.val);
            return left;
        }
        count++;
        if(count == k){
            System.out.println(root.val);
            return root;
        }
        return kthSmallest(root.right, k);
    }
    //transform a bst to grater sum tree
    static int sum = 0;
    public static Node BstToGst(Node root){
        if(root.right != null){
            BstToGst(root.right);
        }
        sum+=root.val;
        root.val = sum - root.val;
        if(root.left != null){
            BstToGst(root.left);
        }
        return root;
    }
    //maximum sum in bst of a bt
    static class Info2{
        int size;
        int max;
        int min;
        boolean isBST;
        int v;
        Info2(boolean isBST,int s,int max,int min,int v){
            this.isBST = isBST;
            this.size = s;
            this.max = max;
            this.min = min;
            this.v = v;
            
        }
    }
    static int maxSum;
    public static Info2 maxBSTSum(Node root){
        if(root == null){
            return new Info2(true,0,Integer.MIN_VALUE,Integer.MAX_VALUE,0);
        }
        Info2  leftInfo2 = maxBSTSum(root.left);
        Info2 rightInfo2 = maxBSTSum(root.right);
        int min = Math.min(root.val,Math.min(leftInfo2.min,rightInfo2.min));
        int max = Math.max(root.val,Math.max(leftInfo2.max,rightInfo2.max));
        int size = leftInfo2.size+rightInfo2.size+1;
        int sum1 = leftInfo2.v+rightInfo2.v+root.val;
        //validaty check of a bst
        if(rightInfo2.min <= root.val || leftInfo2.max >= root.val){
            return new Info2(false, size, max, min,sum1);
        }
        if(leftInfo2.isBST && rightInfo2.isBST){
            maxSum = Math.max(maxSum,sum1);
            return new Info2(true, size, max, min,sum1);
        }
        return new Info2(false, size, max, min,sum1);
    }
    public static void main(String args[]){
   

        Node root = null;
        int keys[] = { 1,4,3,2,4,2,5,4,6};
        for (int x : keys){
            root = insert(root, x);
        }
           
       
        
    }
    
}
