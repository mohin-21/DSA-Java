package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Traversal {
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
    //BFS (breadth first search)
    public static void bfs(ArrayList<Edge> graph[]){//O(V+E)
        Queue<Integer> q = new LinkedList<>();
        boolean visit[] = new boolean[graph.length];
        q.add(0);
        while(!q.isEmpty()){
            int cur = q.remove();
            if(!visit[cur]){
                visit[cur] = true;
                System.out.print(cur+" ");
                for(int i = 0;i<graph[cur].size();i++){
                    Edge e = graph[cur].get(i);
                    q.add(e.dest);
                }
            }
        }
    }
    
    //DFS(Depth first search)
    public static void dfs(ArrayList<Edge> graph[],int cur,boolean visit[]){ //O(V+E)
        //visit
        System.out.print(cur +" ");
        visit[cur] = true;
        for(int i = 0;i<graph[cur].size();i++){
            Edge e = graph[cur].get(i);
            if(!visit[e.dest]){
                dfs(graph,e.dest,visit);
            }
        }
    }
    

    //connected component
    public static void bfs2(ArrayList<Edge>[] graph){
        boolean vis[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                bfs2Util(graph,vis);
            }
        }
    }
    public static void bfs2Util(ArrayList<Edge>[]graph,boolean vis[]){
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
       
        while(!q.isEmpty()){
            int cur = q.remove();
            if(!vis[cur]){
                System.out.print(cur + " ");
                vis[cur] = true;
                for(int i = 0;i<graph[cur].size();i++){
                    Edge e = graph[cur].get(i);
                    q.add(e.dest);
                }
            }   
        }
    }
    public static void dfs2(ArrayList<Edge>[]graph){
        boolean vis[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                dfs2Util(graph,i,vis);
            }
        }
    }
    public static void dfs2Util(ArrayList<Edge>[]graph,int cur,boolean vis[]){
        System.out.print(cur +" ");
        vis[cur] = true;
        for(int i =0;i<graph[cur].size();i++){
            Edge e = graph[cur].get(i);
            if(!vis[e.dest]){
                dfs2Util(graph,e.dest,vis);
            }
        }
    }
}
