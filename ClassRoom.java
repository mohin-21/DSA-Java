import java.util.*;



public class ClassRoom{

    //Heap implementation
    static class Heap{
       ArrayList<Integer> heap = new ArrayList<>();
        //add
        public void add(int data){
            heap.add(data);
            int x = heap.size()-1;//child index
            int par = (x-1)/2; 
            while(heap.get(x) > heap.get(par)){
                int temp = heap.get(x);
                heap.set(x,heap.get(par));
                heap.set(par,temp);
                x = par;
                par = (x-1)/2;
            }
        }
        //peek 
        public int peek(){
            return heap.get(0); 
        }

        private void heapify(int i){
            int minIdx = i;
            int left = 2*i+1;
            int right = 2*i+2;
            int n = heap.size();
            if(left < n && heap.get(minIdx) < heap.get(left)){
                minIdx = left;
            }
            if(right < n && heap.get(minIdx) < heap.get(right)){
                minIdx = right;
            }
            if(minIdx != i){
                int temp = heap.get(i);
                heap.set(i,heap.get(minIdx));
                heap.set(minIdx,temp);
                heapify(minIdx);
            }
        }
        //remove
        public int remove(){
            int data = heap.get(0);
            //step 1 
            int temp = heap.get(0);
            heap.set(0,heap.get(heap.size()-1));
            heap.set(heap.size()-1,temp);
            //step 2
            heap.remove(heap.size()-1);
            //step 3 heapify
            heapify(0);
            return data;
        }

        public boolean isEmpty(){
            return heap.size() == 0;
        }
    }
    //heap sort work by comparing element
    public static void heapify(int arr[],int idx,int size){
        int maxIdx = idx;
        int left = 2*idx+1;
        int right = 2*idx+2;
        if(left < size && arr[left] > arr[maxIdx]){
            maxIdx = left;
        }
        if(right < size && arr[right] > arr[maxIdx]){
            maxIdx = right;
        }
        if(maxIdx != idx){
            int temp =  arr[idx];
            arr[idx] = arr[maxIdx];
            arr[maxIdx] = temp;
            heapify(arr,maxIdx,size);
        }
    }
    public static void heapSort(int arr[]){
        //step 1 build max to min heap 
        int n = arr.length;
        for(int i = n/2;i>=0;i--){
            heapify(arr,i,n);
        }
        //step 2 swpa first and last element or push larget elemnt in the last
        for(int i = n-1;i> 0;i--){// i is the current size of the arr
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;  
            heapify(arr,0,i);
        }
    }
    //k sorted Linked list
    public static void kSorted(){
        ArrayList<Integer> nums = new ArrayList<>(List.of( 4, 6, 3, 9, 10, 2));
        int sum = 0;
        for(int i = 0 ; i < nums.size() ; i++){
            sum += nums.get(i);
          }
       
          // Initializing max heap
          PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
          for(int i = 0 ; i < nums.size() ; i++){
            pq.add(-nums.get(i));
            //System.out.println(pq.remove());
          }
       
          double temp = sum;
          int cnt = 0;
          while (temp > sum / 2) {
            int x = -pq.peek();
            //System.out.println(x);
            pq.remove();
            temp -= Math.ceil(x * 1.0 / 2);
            pq.add(x / 2);
            //System.out.println(pq.remove());
            cnt++;
          }
          System.out.println(cnt);
    }
    

    //HashMap implementation
    static class HashMapCode<K, V>{
        private class Node{
            K key;
            V value;
            public Node(K key, V value){
                this.key = key;
                this.value = value;
            }
        }
        private int n;
        private int N;
        LinkedList<Node> buckets[];
        @SuppressWarnings("unchecked")
        public HashMapCode(){
            this.N = 4;
            this.buckets = new LinkedList[4];
            for(int i = 0;i<4;i++){
                buckets[i] = new LinkedList<>();
            }
        }
 
        private int hashFunction(K key){
            int hc = key.hashCode();
            return Math.abs(hc)%N; 
        }
        private int searchInLL(K key,int bi){
            LinkedList<Node> ll = buckets[bi];
            int di = 0;
            for(int i = 0;i<ll.size();i++){
                Node node = ll.get(i);
                if(node.key == key){
                    return di;
                }
                di++;
            }
            return -1;

        }
        @SuppressWarnings("unchecked")
        private void rehash(){
            LinkedList<Node> oldBuckets[] = buckets;
            buckets = new LinkedList[N*2];
            N = N *2;
            for(int i = 0;i<buckets.length;i++){
                buckets[i] = new LinkedList<Node>();
            }
            for(int i = 0;i<oldBuckets.length;i++){
                LinkedList<Node> ll = oldBuckets[i];
                Node node = ll.remove();
                put(node.key,node.value);
            }

        }
        //add put insert
        public void put(K key, V value){
            int bi = hashFunction(key);// i need 0 to size-1 
            int di = searchInLL(key,bi);
            if(di != -1){
                Node node = buckets[bi].get(di);
                node.value = value;
            }else{
                buckets[bi].add(new Node(key,value));
                n++;
            }
            
            double lambda = (double)n/N;
            if( lambda > 2.0){
                rehash();
            }
        }
        //remove
        public V remove(K key){
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if(di != 1){
                // LinkedList<Node> ll = buckets[bi];
                // Node node = ll.remove(di);
                Node node = buckets[bi].remove(di);
                n--;
                return node.value;
            }
            return null;
        }
        //get
        public V get(K key){
           int bi = hashFunction(key);
           int di = searchInLL(key,bi);
           if(di != -1){
            Node node = buckets[bi].get(di);
            return node.value;
           }
           return null;
        }
        //containsKey
        public boolean containsKey(K key){
            int bi = hashFunction(key);// i need 0 to size-1 
            int di = searchInLL(key,bi);
            if(di != -1){
                return true;
            }else{
                return false;
            }
            
        }
        //keySet
        public ArrayList<K> keySet(){
            ArrayList<K> keys = new ArrayList<>();
            for(int i =0;i<buckets.length;i++){
                LinkedList<Node> ll = buckets[i];
                for (Node node : ll) {
                    keys.add(node.key);
                }
            }
            return keys;
        }
        public boolean isEmpty(){
            return n == 0;
        }
        public int size(){
            return n;
        }
    }
    public static  List<Integer> rightSideView(TreeNode root) {
        return rightSideView2(root);
    }
   
    public static  List<Integer> rightSideView2(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer,TreeNode> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        q.add(null);
        int min = 0;
        int max = 0;
        int le = 0;
        while(!q.isEmpty()){
            TreeNode cur = q.remove();
            if(cur == null){
                //cur.l = cur.l+1;
                le++;
                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }
            }else{
                //cur.l++;
                map.put(le,cur);
                if(cur.left != null){
                    q.add(cur.left);
                }
                if(cur.right != null){
                    q.add(cur.right);
                   
                }
                max = Math.max(max,le);

            }
        }
        for(int i = min;i<=max;i++){
            list.add(map.get(i).val);
        }
        return list;
    }

    static  class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    

    //tries / retrieval/prefix

    static class Node{
        Node children[] = new Node[26];
        boolean eow = false;
        public Node(){
            for(int i = 0;i<children.length;i++){
                children[i] = null;
            }
        }
    }
    static Node root = new Node();
    //build or create a trie
    public static void insert(String word){
        Node cur = root;
        for(int i = 0;i<word.length();i++){
            int idx = word.charAt(i) - 'a';
            if(cur.children[idx] == null){
                cur.children[idx] = new Node();
            }
            cur = cur.children[idx];
        }
        cur.eow = true;
    }
    //look up for a key/search
    public static boolean search(String key){
        Node cur = root;
        for(int i = 0;i<key.length();i++){
            int idx = key.charAt(i) -'a';
            if(cur.children[idx] == null){
                return false;
            }
            cur = cur.children[idx];
        }
        return cur.eow == true;
    }
    //word break problem
    public static boolean wordBreak(String key){
        if(key.length() == 0){
            return true;
        }
        for(int i = 1;i<= key.length();i++){
            if(search(key.substring(0,i)) && 
            wordBreak(key.substring(i))){
                return true;
            }
        }
        return false; 
    }
    static class Node2{
        Node2 children [] = new Node2[26];
        boolean eow = false;
        int freq;
        Node2(){
            for(int i = 0;i<children.length;i++){
                children[i] = null;
            }
            freq = 1;
        }
    }
    static Node2 root2 = new Node2();
    // root2.freq = -1;
    public static void build(String word){
        Node2 cur = root2;
        for(int i = 0;i<word.length();i++){
            int idx = word.charAt(i) - 'a';
            if(cur.children[idx] == null){
                cur.children[idx] = new Node2();
            }else{
                cur.children[idx].freq++;
            }
            cur = cur.children[idx];
        }
        cur.eow = true;
    }

    public static void uniquePrefix(Node2 root2,String words){
        if(root2 == null){
            return;
        }

        if(root2.freq == 1){
            System.out.println(words);
            return;
        }
        for(int i = 0;i<26;i++){
            if(root2.children[i] != null){
                uniquePrefix(root2.children[i], words+(char)(i+'a'));
            }

        }
    }

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int max = 0;
        int ans =0;
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(!map.containsKey(ch)){
                map.put(ch,i);
                ans++;
                max = Math.max(max,ans);
            }else{
                ans=0;
                map.clear();
            }
        }
        return max;
    }

    private static boolean isPalin(StringBuilder s1){
        int n = s1.length();
        for(int i=0; i<=n/2; i++){
            if(s1.charAt(i) != s1.charAt(n-1-i)){
                return false;
            }
        }
        return true;
    }
    // public static String longestPalindrome(String s) {
    //    StringBuilder dp[][] = new StringBuilder[2][s.length()];
    //    String ans="";
    //    for(int i=0; i<dp.length; i++){
    //     if(isPalin(dp[0][i])){
    //         dp[1][i] = dp[0][i];
    //         if(ans.compareTo(dp[0][i].toString()) >1){
    //             dp[1][i] = dp[0]

    //         }
    //     }
    //    }
    // }
    public static void main(String[] args){
        // int cities[][] = {{0,1,2,3,4},{1,0,5,0,7},{2,5,0,6,0},{3,0,6,0,0},{4,7,0,0,0}};
        // for(int i = 0;i<cities.length;i++){
        //     for(int j = 0;j<cities.length;j++){
        //         if(cities[i][j] != 0){
        //             int src = i;
        //             int dest =j;
        //             int wt = cities[i][j];
        //            System.out.println("src " + src +" dest "+ dest +" wt "+ wt);
        //         }
        //     }
           
        // }
        String s= "acfgbd";
        int k = 2;
        HashSet<Character> set = new HashSet<>();
        for(int i=0; i<s.length()-1; i++){
            int idx1 = s.charAt(i)-'a';
            int idx2 = s.charAt(i+1)-'a';
            System.out.println(idx2 - idx1 );
            if(idx2 - idx1 <= k+1){
                set.add(s.charAt(i));
               
            }else{
                 set.add(s.charAt(i+1));
            }
        }
        System.out.println(set.size());
        
    }
    
}

    
