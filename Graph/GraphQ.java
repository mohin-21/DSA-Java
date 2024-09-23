package Graph;
import java.util.*;
public class GraphQ {

    static class Edge{
        int src;
        int dest;
        int wt;
        public Edge(int s,int d){
            this.src = s;
            this.dest = d;
        }

    }

    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        // graph[0].add(new Edge(0,1));

        // graph[1].add(new Edge(1,0));
        // graph[1].add(new Edge(1,3));
        // graph[1].add(new Edge(1,4));

        // graph[3].add(new Edge(3,1));
        // graph[3].add(new Edge(3,4));

        // graph[4].add(new Edge(4,1));
        // graph[4].add(new Edge(4,3));
        graph[0].add(new Edge(0,2));
        graph[0].add(new Edge(0,3));
        graph[1].add(new Edge(1,0));
        graph[2].add(new Edge(2,1));
        graph[3].add(new Edge(3,4));
    }
    

    public static boolean dfs(ArrayList<Edge> graph[],int cur,int par,boolean vis[]){
       vis[cur] = true;
       for(int i = 0;i<graph[cur].size();i++){
        Edge e = graph[cur].get(i);
        
        if(!vis[e.dest]){
           if(dfs(graph, e.dest, cur, vis)){
            return true;
           }else if(par != e.dest && vis[e.dest]){
            return true;
            }
        }
        
       }
       return false;
    }
    public static boolean detectCycle(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){ 
                return dfs(graph,i,-1,vis);
            }
        }
        return false;
    }
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
            this.left = this.right = null;
        }
        
    }
    //min depth of binary tree
    public static int minDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        if(root.left == null){
            return minDepth(root.right)+1;
        }
        if(root.right == null){
            return minDepth(root.left)+1;
        }
       
        return Math.min( minDepth(root.left),minDepth(root.right))+1;
    }

    static class Ele{
        int x = 0;
        int y = 0;
        public Ele(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    public static boolean delim(Ele temp){
        return (temp.x == -1 && temp.y == -1);
    }
    public static boolean checkAll(int orange[][]){
        for(int i[] : orange){
            for(int j: i){
                if(j == 1){
                    return true;
                }
            }
        }
        return false;
    }
    //min time required to rot all orange
    public static int rotOrange(int orange[][],int sr,int sc){
        int ans = 0;
        Queue<Ele> q = new LinkedList<>();
        for(int i = 0;i<orange.length;i++){
            for(int j = 0;j<orange[0].length;j++){
                if(orange[i][j] == 2){
                    q.add(new Ele(i,j));
                }
            }
        }
        q.add(new Ele(-1,-1));
        while(!q.isEmpty()){
           
            boolean flag = false;
            while(!delim(q.peek())){
                Ele cur = q.remove();
                //up
                if(isValid(orange, cur.x-1, cur.y) && orange[cur.x-1][cur.y] == 1){
                    if(!flag){
                        ans++;
                        flag = true;

                    }
                    orange[cur.x-1][cur.y] = 2;
                    cur.x--;
                    q.add(new Ele(cur.x,cur.y));
                    cur.x++;
                }
                //down
                if(isValid(orange, cur.x+1, cur.y) && orange[cur.x+1][cur.y] == 1){ 
                    if(!flag){
                        ans++;
                        flag = true;
                    }
                    orange[cur.x+1][cur.y] = 2;
                    cur.x++;
                    q.add(new Ele(cur.x,cur.y));
                    cur.x--;
                }
                //left
                if(isValid(orange,cur.x,cur.y-1)&& orange[cur.x][cur.y-1] == 1){
                    if(!flag){
                        ans++;
                        flag = true;
                    }
                    orange[cur.x][cur.y-1] = 2;
                    cur.y--;
                    q.add(new Ele(cur.x,cur.y));
                    cur.y++;
                } 
                //down
                if(isValid(orange,cur.x,cur.y+1)&& orange[cur.x][cur.y+1] == 1){
                    if(!flag){
                        ans++;
                        flag = true;
                    }
                    orange[cur.x][cur.y+1] = 2;
                    cur.y++;
                    q.add(new Ele(cur.x,cur.y));
                    cur.y--;
                }
                //q.remove();
            }
            q.remove();
            if(!q.isEmpty()){
                q.add(new Ele(-1,-1));
            }  
        }
        return checkAll(orange) ? -1 : ans;
       
       
    }
 

    public static boolean isValid(int grid[][],int sr,int sc){
        return sr >= 0 && sc >= 0 && sr < grid.length && sc < grid[0].length;
        
    }
    static int count;
    public static int largestRigion(int grid[][]){
        int res = 0;
        boolean vis[][] = new boolean[grid.length][grid[0].length];
        for(int i =0;i<grid.length;i++){
            for(int j = 0;j<grid[0].length;j++){
                if(grid[i][j] == 1 && !vis[i][j]){
                    count = 1;
                    helper(grid,i,j,vis);//bfs
                    res = Math.max(count ,res);
                }
            }
        }
        return res;
    }
    public static boolean isSafe(int grid[][],int r,int c,boolean vis[][]){
        return r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && !vis[r][c]
        && grid[r][c] == 1;
    }
    public static void helper(int grid[][],int i,int j,boolean vis[][]){
        vis[i][j] = true;
        int rowNmb [] = {-1,-1,-1,0,0,1,1,1};
        int colNmb[] = {-1,0,1,-1,1,-1,0,1};
        for(int k = 0;k<8;k++){
            if(isSafe(grid, i+rowNmb[k],j+colNmb[k], vis)){
                count++;
                helper(grid, i+rowNmb[k], j+colNmb[k], vis);
            }
        }
    

    }

    //word ladder
    public static int smallestChain(String dic[],String start,String target){
        HashSet<String> set = new HashSet<>();
        for(int i = 0;i<dic.length;i++){
            set.add(dic[i]);
        }
        int level = 0;
        Queue<String> q = new LinkedList<>();
        q.add(start);
        //bfs
        while(!q.isEmpty()){
            level++;
            int size = q.size();
            char[] word = q.remove().toCharArray();
            //q.remove();
            for(int i = 0;i<size;i++){
               
                for(int j = 0;j<start.length();j++){
                    char org = word[j];
                    for(char c = 'a';c <= 'z';c++){
                        word[j] = c;
                        if(String.valueOf(word).equals(target)){
                            return level+1;
                        }
                        if(!set.contains(String.valueOf(word))){
                            continue;
                        }
                        set.remove(String.valueOf(word));
                        q.add(String.valueOf(word));

                    }
                    word[j] = org;
                }
            }
        }  
        return 0;

    }

    public static void dfs(ArrayList<Edge> graph[],int cur,boolean vis[]){
        vis[cur] = true;
        System.out.print(cur+" ");
        
            for(int j = 0;j<graph[cur].size();j++){
                Edge e = graph[cur].get(j);
                if(!vis[e.dest]){
                    dfs(graph,e.dest,vis);
                }
            }
         
    }
    public static void topSort(ArrayList<Edge> graph[],int cur,boolean vis[],Stack<Integer> s){
        vis[cur] = true;
        for(int i = 0;i<graph[cur].size();i++){
            Edge e = graph[cur].get(i);
            if(!vis[e.dest]){
                topSort(graph, e.dest, vis, s);
            }
        }
        s.push(cur);
    }
    //strongly connect component
    public static void kosaraju(ArrayList<Edge> graph[],int V){
        //step 1 
        boolean vis[] = new boolean[V];
        Stack<Integer> s = new Stack<>();
        for(int i = 0;i<V;i++){
            if(!vis[i]){
                topSort(graph, i, vis, s);
            }
        }
        //step 2 transpose graph
        @SuppressWarnings("unchecked")
        ArrayList<Edge> transpose[] = new ArrayList[V];
        
        for(int i = 0;i<V;i++){
            vis[i] = false;
            transpose[i] = new ArrayList<>();
        }
        for(int i = 0;i<V;i++){
            for(int j = 0;j<graph[i].size();j++){
                Edge e = graph[i].get(j);
                transpose[e.dest].add(new Edge(e.dest,e.src));

            }
        }
        //step 3
        while(!s.isEmpty()){
            int cur = s.pop();
            if(!vis[cur]){
                dfs(transpose, cur, vis);
                System.out.println();
            }
        }
       
    }
    
    public static void dfs2(ArrayList<Edge> graph[],int cur,int par,int dt[],int low[],int time,boolean vis[]){
        vis[cur] = true;
        dt[cur] = low[cur] = time++;
        for(int i = 0;i<graph[cur].size();i++){
            Edge e = graph[cur].get(i);
            if(!vis[e.dest]){
                dfs2(graph, e.dest,cur,dt,low,time,vis);
                low[cur]  = Math.min(low[cur],low[e.dest]);
                if(dt[cur] < low[e.dest]){
                    System.out.println("Bridge : " + cur + " -- "+ e.dest);
                }
                
            }else if(e.dest == par){
                continue;
            }else{
                low[cur] = Math.min(low[cur],dt[e.dest]);
            }
            
        } 
    }
    public static void bridge(ArrayList<Edge> graph[],int V){
        boolean vis[] = new boolean[V];
        int time = 0;
        int dis[] = new int[V];
        int low[] = new int[V];

        for(int i = 0;i<V;i++){
            if(!vis[i]){
                dfs2(graph,i,-1,dis,low,time,vis);
            }
        }
    }

    public static void dfs3(ArrayList<Edge> graph[],int cur,int par,int dt[],int low[],boolean vis[],int time){
        vis[cur] = true;
        dt[cur] = low[cur] = time++;
        int children = 0;
        for(int i = 0;i<graph[cur].size();i++){
            Edge e = graph[cur].get(i);
            if(par == e.dest){
                continue;
            }else if(!vis[e.dest]){
                dfs3(graph, e.dest, cur, dt, low, vis, time);
                low[cur] = Math.min(low[cur],low[e.dest]);
                if(par != -1 && dt[cur] <= low[e.dest]){
                    System.out.println("ap : "+ cur);
                }
                children++;
            }else{
                low[cur] = Math.min(low[cur],dt[e.dest]);
            }
        }

        if(par == -1 && children > 1){
            System.out.println("ap : " + cur);
        }

    }
    //articulation point of graph tarjons algo
    public static void articulation(ArrayList<Edge> graph[],int V){
        boolean vis[] = new boolean[V];
        int dt [] = new int[V];
        int low[] = new int[V];
        int time = 0;
        for(int i = 0;i<V;i++){
            if(!vis[i]){
                dfs3(graph, i, -1, dt, low, vis, time);
            }
        }
    }
    public static void main(String[] args){
        int V = 5;
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[V];  
        createGraph(graph);
        
        articulation(graph, V);
       

    }
    
}
   