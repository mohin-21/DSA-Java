package Hashing;
import java.util.*;

public class Hashing {
    

    //Questions 
    //majority element
    public static void mojorityEle(int nums[]){
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            // int cur = nums[i];
            // if(map.containsKey(cur)){
            //     map.put(cur,map.get(cur)+1);
            // }else{
            //     map.put(cur,1);
            // }

            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        //Set<Integer> keys = map.keySet();
        for (Integer key : map.keySet()) {
            if(map.get(key) > nums.length/3){
                System.out.println(key);
            }
            
        }

    }

    //valid anagram
    public static boolean isAnagram(String t,String s){
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<t.length();i++){
            char ch = t.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        for(int i = 0;i<s.length();i++){
            char ch = s.charAt(i);
            if(map.containsKey(ch)){
                if(map.get(ch) == 1){
                    map.remove(ch);
                }else {
                    map.put(ch,map.get(ch)-1);
                }
            }else{
                return false;
            }
        }
        return map.isEmpty();
    }
    //count distinct(unique) elements
    //brute force approach
    public static int distinct(int nums[]){
        
        //ArrayList<Integer> list = new ArrayList<>();
        int count = 1;
        Arrays.sort(nums);
        for(int i = 1;i<nums.length;i++){
            if(nums[i-1] < nums[i]){
                count++;
            }
        }
        return count;
    }
    //optimize approach
    public static int countDistinct(int nums[]){
       
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0;i<nums.length;i++){
            set.add(nums[i]);
        }
        return set.size();
    }

    //union and intersection of two arrays
    //union
    public static int unionCount(int arr1[],int arr2[]){
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0;i<arr1.length;i++){
            set.add(arr1[i]);
        }
        for(int i = 0;i<arr2.length;i++){
            set.add(arr2[i]);
        }
        System.out.print("Elements are :");
        for(Integer i : set){
            System.out.print(i+" ");
        }
        return set.size();
    }

    //intersection
    public static int intersection(int arr1[],int arr2[]){
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0;i<arr1.length;i++){
            set.add(arr1[i]);
        }
        int count = 0;
        System.out.print("Elements are :");
        for(int i = 0;i<arr2.length;i++){
            if(set.contains(arr2[i])){
                count++;
                set.remove(arr2[i]);
                System.out.print(arr2[i]+" ");
            }
        }
        return count;
        
    }
    public static String getStart(HashMap<String,String> map){
        HashMap<String,String> dMap = new HashMap<>();
        for(String key : map.keySet()){
            dMap.put(map.get(key),key);
        }
        for(String key : map.keySet()){
            if(!dMap.containsKey(key)){
                return key;
            }
        }
        return null;
    
    }
    //find itenerary tickets
    public static void tickets(HashMap<String,String> map){
        
        // for(int i = 0;i<tick[0].length;i++){
        //     for(int j = 1;j<tick[0].length;j++){
        //         map.put(tick[i][i],tick[i][j]);
        //     }
            
        // }
        String start = getStart(map);
        System.out.print(start);
   
        for(String t : map.keySet()){
            System.out.print("->"+map.get(start));
            start = map.get(start); 

        }
    }
    //largest sum with 0 sum
    public static int largest(int nums[]){
        int len = 0;
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        //(sum idx )
        for(int j = 0;j<nums.length;j++){
            sum+=nums[j];
            if(map.containsKey(sum)){
                len = Math.max(len,j-map.get(sum));
            }else{
                map.put(sum,j);
            }

        }
        return len;

    }

    // subarray sum equal to k 
    public static int kEqualSum(int nums[],int k){
        HashMap<Integer,Integer> map = new HashMap<>();
        //(sum,count)
        int sum = 0;
        int count = 0;
        map.put(0,1);
        for(int j = 0;j<nums.length;j++){
            sum+=nums[j];
            if(map.containsKey(sum - k)){
                count+= map.get(sum - k);
            }
            map.put(sum,map.getOrDefault(sum, 0)+1);
            

        }
        return count;
    }
    
    static class Node{
        int val;
        Node left,right;
        Node(int v ){
            this.val = v;
            this.left = this.right = null;
        }
    }

    //buttom view of a binary tree
    static class Pair{
        int idx;
        Node n;
        public Pair(int d,Node n){
            this.idx = d;
            this.n = n;
        }
    }
    public static void buttomView(Node root){
        HashMap<Integer,Node> map = new HashMap<>();
       
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,root));
        q.add(null);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while(!q.isEmpty()){
            Pair cur = q.remove();
            if(cur == null){
                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }
            }else{
                map.put(cur.idx,cur.n);
                if(cur.n.left != null){
                    q.add(new Pair(cur.idx-1,cur.n.left));
                    min = Math.min(min,cur.idx-1);
                }
                if(cur.n.right != null){
                    q.add(new Pair(cur.idx+1,cur.n.right));
                    max = Math.max(max,cur.idx+1);
                }
            }
        }
        for(int i = min;i<=max;i++){
            System.out.print(map.get(i).val+" ");
        }

    }
    //two sum
    public static int[] twoSum(int nums[],int k){
        HashMap<Integer,Integer> map = new HashMap<>();
        //(sum - idx)
        for(int i = 0;i<nums.length;i++){
            if(map.containsKey(k-nums[i])){
                return new int[]{map.get(k-nums[i]),i};
            }else{
                map.put(nums[i],i);
            }
        }
        return new int[]{0,0};
    }
    //sort by frequency
    static class Freq{
        int f;
        char ch;
        Freq(int f,char ch){
            this.f = f;
            this.ch = ch;
        }
    }

    public static void sortByFreq(String str){
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<str.length();i++){
            char ch = str.charAt(i);
            //counting each char frequency
            map.put(ch,map.getOrDefault(ch, 0)+1);
            
        }
        PriorityQueue<Freq> pq = new PriorityQueue<>((a,b)-> b.f- a.f);
        for(Character ch : map.keySet()){
            pq.add(new Freq(map.get(ch),ch));
        }
        while(!pq.isEmpty()){
            Freq cur = pq.remove();
            while(cur.f-- > 0){
                System.out.print(cur.ch+" ");
                //cur.f--;
            }
            
        }

    }
   
    /*HashMap -> unordered
    * Linked HashMap -> keys are ordered by insert -> Doubly LL diye implemetnt kora hoy
    * Tree Map -> keys are sorted
    * 
    */
       
    //divisor of a number
    public static List<Integer> divisor(int n){
        ArrayList<Integer> div = new ArrayList<>();
        int sqrN =(int) Math.sqrt(n);
        for(int i =1; i<=sqrN; i++){
            if(n % i ==0 ){
                div.add(i);
            }

            if(i != n/i){
                div.add(n/i);
            }

        }
        return div;
    }

    //number with most frequency
    public static int mostFreq(int arr[]){
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(arr[0],1);
        int max =0;
        for(int i=0; i<arr.length; i++){
            if(map.containsKey(arr[i])){
                int freq = map.get(arr[i]);
                
                map.put(arr[i],freq+1);
                max = Math.max(max, map.get(arr[i]));
            }else{
                map.put(arr[i],1);
            }
        }
        return max;

    }
     
    public static void main(String[] args){
       
       int arr[] = {1,3,3,1,2,2,2,3};
       System.out.println(mostFreq(arr));
       
    }
}
  