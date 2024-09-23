package Graph;

import java.util.ArrayList;

public class CycleDetection {

    static class Edge{
        int src;
        int dest;
        int wt;
        public Edge(int s, int d, int w){
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    // directed graph
    public static void createGraph(ArrayList<Edge>[]graph){
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<Edge>();
        }
    
        graph[0].add(new Edge(0,0,1));

        graph[1].add(new Edge(1,1,1));

        graph[2].add(new Edge(2,3,1));

        graph[3].add(new Edge(3,1,1));

        graph[4].add(new Edge(4,0,1));
        graph[4].add(new Edge(4,1,1));

        graph[5].add(new Edge(5,0,1));
        graph[5].add(new Edge(5,2,1));
       
    }


    //cycle detecting -> directed graph bfs
    public static boolean detectCycle(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                if(detectCycleUtil(graph,vis,i, -1)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean detectCycleUtil(ArrayList<Edge> graph[],boolean vis[],int cur,int par){
        vis[cur] = true;
        for(int i = 0;i<graph[cur].size();i++){
            Edge e = graph[cur].get(i);
            if(!vis[e.dest]){//case 3
                if(detectCycleUtil(graph, vis, e.dest, cur)){
                    return true;
                }
            }
            else if(vis[e.dest] && e.dest != par){//case 1(e.dest == cur for this and neighbors != par)
                return true;
            }
            //case 2 do nothing or continue (vis[cur] == true and e.dest(neighbor) == par)
        }
        return false;
    }


    //detect cycle in a directed graph using kan's algorithm
    public static boolean isCycle(ArrayList<Edge>[]graph){
        boolean vis[] = new boolean[graph.length];
        boolean stack[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                return isCycleUtil(graph,vis,stack,i);
            }
        }
        return false;
    }
    public static boolean isCycleUtil(ArrayList<Edge>[]graph,boolean vis[],boolean stack[],int cur){
        vis[cur] = true;
        stack[cur] = true; 
        for(int i = 0;i<graph[cur].size();i++){
            Edge e = graph[cur].get(i);
            if(stack[e.dest]){
                return true;
            }
            if(!vis[e.dest]){
                return isCycleUtil(graph,vis,stack,e.dest);
            }

        }
        stack[cur] = false;
        return false;
    }


    
}
