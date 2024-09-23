package Heap;
import java.util.*;

public class HeapClass {
   
    //object store and comparision in pq
    static class Student implements Comparable<Student>{
        String name;
        int rank;
        public Student(String n,int rank){
            this.name = n;
            this.rank = rank;
        }
        @Override
        // public int compareTo(Student s2){ sting comparision it's very important
        //     return this.name.compareTo(s2.name);
        // }
        public int compareTo(Student s2){
            return this.rank - s2.rank;
        }

    }
    public static void heapify2(int arr[],int i,int size){
        int maxIdx = i;
        int left = 2*i+1;
        int right = 2*i+2;
        if(left < size && arr[maxIdx] < arr[left]){
            maxIdx = left;
        }
        if(right <size && arr[maxIdx] < arr[right]){
            maxIdx = right;
        }
        if(maxIdx != i){
            int temp = arr[i];
            arr[i] = arr[maxIdx];
            arr[maxIdx] = temp;
            heapify2(arr,maxIdx,size);
        }
    }
    //heap sort
    public static void heapSort(int arr[]){
        //convert min heap to max heap
        int n = arr.length;
        for(int i = n/2-1;i>= 0;i--){
            heapify2(arr,i,n);
        }
        //step 2 swap first and last
        for(int i =n-1;i>0;i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp; 
            heapify2(arr,0,i);
        }
    }
    //k nearest cars
    static class Cars implements Comparable<Cars>{
        int x;
        int y;
        int dist;
        int idx;
        public Cars(int x,int y,int dist,int i){
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.idx = i;
        }
        @Override
        public int compareTo(Cars c){
            return this.dist - c.dist;
        }
    }
    public static void kNearest(int cars[][],int k){
        PriorityQueue<Cars> pq = new PriorityQueue<>();
        for(int i = 0;i<cars.length;i++){
            int dist =(cars[i][0]*cars[i][0]) + (cars[i][1]*cars[i][1]);
            pq.add(new Cars(cars[i][0],cars[i][1],dist,i));
        }
        for(int i= 0;i<k;i++){
            System.out.print("c"+pq.remove().idx+" ");
        }System.out.println();
    }
    //connect n ropes 
    public static int connectNRopes(int  ropes[]){
        int cost = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //adding element in pq 
        for(int i = 0;i<ropes.length;i++){
            pq.add(ropes[i]);
        }
        //calculating cost 
        while(pq.size() > 1){
            int r1 = pq.remove();
            int r2 = pq.remove();
            cost += r1+r2;
            pq.add(r1+r2);
        }
        return cost;
    }
    static class Army implements Comparable<Army>{
        int solder;//solder count
        
        int idx;
        public Army(int s,int i){
            this.solder = s;
            this.idx = i;
        }
        @Override
        public int compareTo(Army a){
            if(this.solder == a.solder){
                return this.idx - a.idx;
            }else{
                return this.solder - a.solder;
            }
        
    }
    //k weakest solder
    public static void kWeakest(int solder[][],int k){
        PriorityQueue<Army> pq = new PriorityQueue<>();
        
        for(int i = 0;i<solder.length;i++){
            int count = 0;
            for(int j = 0;j<solder[0].length;j++){
                count += solder[i][j] == 1 ?1:0;
           }
            pq.add(new Army(count, i));
        }

        for(int i = 0;i< k;i++){
            System.out.print("R" + pq.remove().idx+" ");
        }
          
    }
    //sliding window or maximum of all subarray of size k
    static class Pair implements Comparable<Pair>{
        int val;
        int idx;
        public Pair(int v,int i){
            this.val = v;
            this.idx = i;
        }
        @Override
        public int compareTo(Pair p){
            return p.val - this.val;
        }

    }
    public static void slidingWindow(int arr[],int k){
        int res []= new int[arr.length - k + 1];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int i = 0;i<k;i++){
            pq.add(new Pair(arr[i],i));
        }
        res[0] = pq.peek().val;
        for(int i = k;i<arr.length;i++){
            while(pq.size() >0 && pq.peek().idx <= (i-k) ){
                pq.remove();
            }
            pq.add(new Pair(arr[i],i));
            res[i-k+1] = pq.peek().val;
        }
        for(int i = 0;i<res.length;i++){
            System.out.print(res[i]+" ");
        }
       
    }
     
    //Questions
    //kth largest element in a stream
    public static int [] kthLargest(int arr[],int k){
        int n = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int res[] = new int[n];
        for(int i = 0;i<n;i++){
            if(pq.size() < k){
                pq.add(arr[i]);
            }else{
                if(arr[i] > pq.peek()){
                    pq.poll();
                    pq.add(arr[i]);
                }
            }
            if(pq.size() >= k){
                res[i] = pq.peek();
            }else{
                res[i] = (-1);
            }
        }
        return res;
    }
    //minimum time require to fil given n slots
    public static int minTime(int n,int k,int arr[]){
        int time = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean visit[] = new boolean[n+1];
        for(int i = 0;i<k;i++){
            q.add(arr[i]);
            visit[arr[i]] = true;
        }
        while( q.size()> 0){
            for(int i = 0;i<q.size();i++){
               System.out.println(i +" "+ q.size());
               int cur = q.remove();
               System.out.println(cur);
               if(cur - 1 >= 1 && !visit[cur-1]){
                visit[cur -1 ] = true;
                q.add(cur -1);
               }
               if(cur +1 <= n && !visit[cur +1]){
                visit[cur +1] = true;
                q.add(cur +1);
               }
            }
            time++; 
        }
        return time-1;
    }   
    //minimum operation to halves array sum
    public static int minOperation(int arr[]){
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0;i<arr.length;i++){
            sum+=arr[i];
            pq.add(arr[i]);
        }
        int op = 0;
        float temp = sum;
        while(temp > sum/2){
            int cur = pq.remove();
            temp -= Math.ceil(cur*1.0)/2;
            pq.add(cur/2);
            op++;
        }
        while(pq.size()>0){
            System.out.print(pq.remove()+" ");
        }  
        
        return op;
    }

    //path 

    //k sorted linked list
    static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public static Node kSorted(Node arr[], int k){
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)-> a.data - b.data);
        //Node at[] = new Node[k];
        Node head = new Node(0);
        Node last = head;
        for(int i = 0;i<arr.length;i++){
            if(arr[i] != null){
                pq.add(arr[i]);
            }
           
        }
        if(pq.isEmpty()){
            return null;
        }
        while(!pq.isEmpty() ){
            Node cur = pq.remove();
            last.next = cur;
            last = last.next;
            if(last.next != null){
                pq.add(last.next);
            }
        }
        return head.next;
    }
    public static void printList(Node head){
        while ( head != null){
            System.out.print(head.data+" ");
            head = head.next;
        }System.out.println();
    }
    public static void main(String[] args){
     int n = 3;
     Node ll[] = new Node[n];
     //Linkd list 1
     Node head1 = new Node(1);
     ll[0] = head1;
     head1.next = new Node(3);
     head1.next.next = new Node(5);
     head1.next.next.next = new Node(7);
     //linked list 2
     Node head2 = new Node(2);
     ll[1] = head2;
     head2.next = new Node(4);
     head2.next.next = new Node(6);
     head2.next.next.next = new Node(8);
     //list 3
     Node head3 = new Node(0  );
     ll[2] = head3;
     head3.next = new Node(9);
     head3.next.next = new Node(10);
     head3.next.next.next = new Node(11);
     Node head = kSorted(ll, n);
     printList(head);


      
    }
   } 
}
