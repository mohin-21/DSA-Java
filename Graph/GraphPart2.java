package Graph;
import java.util.*;

public class GraphPart2 {

    static class Edge{
        int src;
        int dest;
        int wt;
        public Edge(int s,int d,int w){
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

     // static ArrayList<Integer> graph[];
    // static int v;
    // static int e;
    // static void addEdge(int a , int b){
    //     graph[b].add(a);
    // } 
    // //course scheduliing 2
    // public static int[] course(int num,int pre[][]){
    //     v  = num;
    //     e = pre.length;
    //     graph = new ArrayList[v];
    //     for(int i = 0;i<v;i++){
    //         graph[i] = new ArrayList<>();
    //     }
    //     for(int i = 0;i<v;i++){
    //         addEdge(pre[i][0],pre[i][0]);
    //     }
    //     //cal indeg
    //     int indeg[] = new int[v];
    //     for(int i = 0;e<v;i++){
    //         indeg[pre[i][0]]++; 
    //     }

    //     Queue<Integer> q = new LinkedList<>();
    //     for(int i = 0;i<indeg.length;i++){
    //         if(indeg[i] == 0){
    //             q.add(i);
    //         }
    //     }
    //     if(q.isEmpty()){
    //         System.out.println("[]");
    //         return new int[]{};
    //     }
    //     int ans [] = new int[v];
    //     int idx = 0;
    //     //bfs
    //     while(!q.isEmpty()){
    //         int cur = q.remove();
    //         ans[idx++] = cur;
    //         for(int i = 0;i<graph[cur].size();i++){
    //             int c = graph[cur].get(i);
    //             indeg[c]--;
    //             if(indeg[c] == 0){
    //                 q.add(c);
    //             }
    //         }
    //     }
    //     if(ans.length < v){
    //         System.out.println("[]");
    //         return new int[]{};
    //     }
    //     return ans;
    // }

    //create graph -> adjacency list , edge list, adjacency matrix , 2d matrix
    //adjacency list
    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        // graph[0].add(new Edge(0,1,2));
        // graph[0].add(new Edge(0,2,4));

        // graph[1].add(new Edge(1,3,7));
        // graph[1].add(new Edge(1,2,1));

        // graph[2].add(new Edge(2,4,3));

        // graph[3].add(new Edge(3,5,1));
    
        // graph[4].add(new Edge(4,5,5));
        // graph[4].add(new Edge(4,3,2));

        graph[0].add(new Edge(0,1,2));
        graph[0].add(new Edge(0,2,4));

        graph[1].add(new Edge(1,2,-4));

        graph[2].add(new Edge(2,3,2));

        graph[3].add(new Edge(3,4,4));
  
        graph[4].add(new Edge(4,1,-1));

    } 

    static class Pair implements Comparable<Pair> {
        int n;
        int path;
        Pair(int n,int p){
            this.n = n;
            this.path = p;
        }

        @Override
        public int compareTo(Pair p2){
            return this.path - p2.path;
        }
    }
    //digkstras algorithm
    public static void dijkstra(ArrayList<Edge> graph[],int src){//O(v+elogv)
        int dest[] = new int[graph.length];
        for(int i = 0;i<dest.length;i++){
            if(i != src){
                dest[i] = Integer.MAX_VALUE;
            }
        }
        boolean vis[] = new boolean[graph.length];
        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(src,0));
        while(!q.isEmpty()){
            Pair cur = q.remove();
            if(!vis[cur.n]){
                vis[cur.n] = true;
                for(int i = 0;i<graph[cur.n].size();i++){
                    Edge e = graph[cur.n].get(i);
                    int u = e.src;
                    int v  = e.dest;
                    int wt = e.wt;
                    if(dest[u] +wt < dest[v]){
                        dest[v] = dest[u]+wt;
                        q.add(new Pair(v,dest[v]));
                    }
                }
            }
            
        }
        for(int i : dest){
            System.out.print(i+" ");
        }
    }

    public static void createGraph2(ArrayList<Edge> graph){
        graph.add(new Edge(0,1,2));
        graph.add(new Edge(0,2,4));

        graph.add(new Edge(1,2,-4));

        graph.add(new Edge(2,3,2));

        graph.add(new Edge(3,4,4));

        graph.add(new Edge(4,1,-1));

    }

    //bellman ford algorithm
    public static void bellmanFord(ArrayList<Edge>graph[],int src){
        int dest[] = new int [graph.length];
        for(int i = 0;i<dest.length;i++){
            if( i != src){
                dest[i] = Integer.MAX_VALUE;
            }
        }
        for(int i = 0;i<graph.length-1;i++){
            
            for(int j = 0;j<graph.length;j++){
                for(int k = 0;k<graph[j].size();k++){
                    Edge e = graph[j].get(k);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    //relaxation
                    if(dest[u] != Integer.MAX_VALUE && dest[u]+ wt < dest[v]){
                        dest[v] = dest[u] + wt;
                    }
                }
            }
        }

        for(int i = 0;i<dest.length;i++){
            System.out.print(dest[i]+" ");
        }

    }

    public static void bellmanFord2(ArrayList<Edge>graph,int src,int V){
        int dest[] = new int [V];
        for(int i = 0;i<dest.length;i++){
            if( i != src){
                dest[i] = Integer.MAX_VALUE;
            }
        }
        for(int i = 0;i<V-1;i++){
            for(int j = 0;j<graph.size();j++){
                Edge e = graph.get(j);
                int u = e.src;
                int v = e.dest;
                int wt = e.wt;
                //relaxation
                if(dest[u] != Integer.MAX_VALUE && dest[u]+ wt < dest[v]){
                    dest[v] = dest[u] + wt;
                }
            }
                   
                
            
        }

        for(int i = 0;i<dest.length;i++){
            System.out.print(dest[i]+" ");
        }

    }

    //prim's algorithm
    static class Pair2 implements Comparable <Pair2>{
        int val;
        int cost;
        //int wt;
        Pair2(int v,int c){
            this.val = v;
            this.cost = c;
            //int wt = w;
        }
        @Override
        public int compareTo(Pair2 p2){
            return this.cost - p2.cost;
        }
    }
    public static void create(ArrayList<Edge> graph[]){
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        
        graph[0].add(new Edge(0,1,10));
        graph[0].add(new Edge(0,2,15));
        graph[0].add(new Edge(0,3,30));

        graph[1].add(new Edge(1,3,40));

        graph[2].add(new Edge(2,3,50));

    }
    public static void prims(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        PriorityQueue<Pair2> pq = new PriorityQueue<>();
        pq.add(new Pair2(0,0));
        int finalCost = 0;
       
        while(!pq.isEmpty()){
            Pair2 cur = pq.remove();
            if(!vis[cur.val]){
                vis[cur.val] = true;
                finalCost += cur.cost;
                
                for(int i= 0;i<graph[cur.val].size();i++){
                    Edge e = graph[cur.val].get(i);
                    
                    pq.add(new Pair2(e.dest,e.wt));
                }
            }
        }
        System.out.println(finalCost);
        
    }

    public static void cheapestFightsUtil(int fights[][],ArrayList<Edge> graph[]){
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0;i<graph.length;i++){
            int src = fights[i][0];
            int dest = fights[i][1];
            int wt = fights[i][2];
            graph[src].add(new Edge(src,dest,wt));
        }
    }
    static class Info{
        int v;
        int wt;//cost
        int stops;
        public Info(int v,int w,int stops){
            this.v = v;
            this.wt = w;
            this.stops = stops;
        }
    }
    public static int cheapestFights(int fights[][],int n,int src,int dest,int k){
        ArrayList<Edge> graph[] = new ArrayList[n];
        cheapestFightsUtil(fights, graph);
        int dist[] = new int[n];
        for(int i = 0;i<n;i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(src,0,0));
       
        //boolean vis[] = new boolean[n];
        while(!q.isEmpty()){
            Info curr = q.remove();
            if(curr.stops > k){
                break;
            }
            // if(!vis[curr.v]){
            //     vis[curr.v] = true;
               
                for(int i = 0;i<graph[curr.v].size();i++){
                    Edge e = graph[curr.v].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    if(curr.wt + wt < dist[v]&& curr.stops <= k){
                        dist[v] = curr.wt + wt;
                        q.add(new Info(e.dest,dist[v],curr.stops+1));
                    }

                }
            

        }
        return dist[dest];   

    }

    static class Pair3 implements Comparable <Pair3>{
        int val;
        int cost;
        int wt;
        Pair3(int v,int c){
            this.val = v;
            this.cost = c;
        }
        @Override
        public int compareTo(Pair3 p2){
            return this.cost - p2.cost;
        }
    }


    //connecting cities
    public static int connectiCities(int cities[][]){
        System.out.println(cities.length);
        ArrayList<Edge> graph[] = new ArrayList[cities.length];
        System.out.println(graph.length);
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0;i<cities.length;i++){
            for(int j = 0;j<cities.length;j++){
                if(cities[i][j] != 0){
                    int src = i; 
                    int dest = j;
                    int wt = cities[i][j];
                    graph[src].add(new Edge(src,dest,wt));
                }
            }
           
        }

        PriorityQueue<Pair3> pq = new PriorityQueue<>();
        pq.add(new Pair3(0,0));
        int cost = 0;
        boolean vis[] = new boolean[cities.length];
        while(!pq.isEmpty()){
            Pair3 cur = pq.remove();
            if(!vis[cur.val]){
                cost += cur.cost;
                vis[cur.val] = true;
                for(int i = 0;i<graph[cur.val].size();i++){
                    Edge e = graph[cur.val].get(i);
                    pq.add(new Pair3(e.dest,e.wt));
                }
            }
        }
        return cost; 
    }

    static int n = 7;
    static int par[] = new int[n];
    static int rank[] = new int[n];
    public static void init(){
        for(int i = 0;i<par.length;i++){
            par[i] = i;
        }
    }

    public static int  find(int x){//O(1)
        if(par[x] == x){
            return x;
        }
        return par[x] = find(par[x]);
    }

    public static void union(int a,int b){//O(1)
        int parA = find(a);
        int parB = find(b);
        if(rank[parA] == rank[parB]){
            par[parB]  = parA;
            rank[parA]++;
        }else if(rank[parA] > rank[parB]){
            par[parB] = parA;
        }else{
            par[parA] = parB;
        }
    }

    public static void edgeList(ArrayList<Edge2> graph){
        
        
        graph.add(new Edge2(0,1,10));
        graph.add(new Edge2(0,2,15));
        graph.add(new Edge2(0,3,30));

        graph.add(new Edge2(1,3,40));

        graph.add(new Edge2(2,3,50));

    }

    static class Edge2 {
        int src;
        int dest;
        int wt;//cost
        public Edge2(int s,int d,int c){
            this.src = s;
            this.dest = d;
            this.wt = c;
        }
    }
    
    static class sortbyEdge2 implements Comparator<Edge2>{
        public int compare(Edge2 e1,Edge2 e2){
            return e1.wt-e2.wt;
        }
    }

    public static void kruskalsMST(ArrayList<Edge2> graph,int V){
        init();
        Collections.sort(graph,new sortbyEdge2());
        int minCost = 0;
        for(int i = 0;i<graph.size()-1;i++){
            Edge2 e = graph.get(i);
            int parA = find(e.src);
            int parB = find(e.dest);
            if(parA != parB){
                union(e.src,e.dest);
                minCost += e.wt;
            }
        }
        System.out.println(minCost);
    }

    //flood fil 
    public static void helper(int image[][],int sr,int sc,int col,boolean vis[][],int orgCol){
        if(sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || vis[sr][sc] 
            || image[sr][sc] != orgCol){
                return;
        }
        image[sr][sc] = col;
        vis[sr][sc] = true;
        //left
        helper(image, sr, sc-1, col, vis, orgCol);
        //right
        helper(image, sr, sc+1, col, vis, orgCol);
        //up
        helper(image, sr-1, sc, col, vis, orgCol);
        //down
        helper(image, sr+1, sc, col, vis, orgCol);
    }
    public static int[][]  floodFil(int image[][],int sr,int sc,int col){
        boolean vis[][] = new boolean[image.length][image[0].length];
        helper(image,sr,sc,col,vis,image[sr][sc]);
        return image;
    }
    public static void main(String[] args){
     int image[][] = {{1,1,1},{1,1,0},{1,0,1}};//[1,1,1],[1,1,0],[1,0,1]]
     floodFil(image, 1, 1, 2);
     for(int i = 0;i<image.length;i++ ){
        for(int j = 0;j<image[0].length;j++){
            System.out.print(image[i][j]+ " ");
        }
     }
    }
} 