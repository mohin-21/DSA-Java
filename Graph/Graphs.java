package Graph;
import java.util.*;

public class Graphs {
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
    
    
    //Bipartite graph
    public static boolean isBipartite(ArrayList<Edge> graph[]){ 
       int col[] = new int[graph.length];
       for(int i = 0;i<col.length;i++){
        col[i] = -1;// no color
       }
       Queue<Integer> q = new LinkedList<>();
       for(int i = 0;i<graph.length;i++){
         if(col[i] == -1){//BFS
            q.add(i);
            col[i] = 0;
            while(!q.isEmpty()){
                int cur = q.remove();
                for(int j = 0;j<graph[cur].size();j++){
                    Edge e = graph[cur].get(j);
                    if(col[e.dest] == -1){ 
                        int nextCol = col[cur] == 0 ? 1 : 0;
                        col[e.dest] = nextCol;
                        q.add(e.dest);
                    }else if(col[cur] == col[e.dest]){
                        return false;
                    }
                }
            } 
         }
       }
      
       return true;
    }
    // directed graph
    public static void createGraph(ArrayList<Edge> graph[]){
        //int V = graph.length;
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,1,1));
        graph[0].add(new Edge(0,2,1));

        graph[1].add(new Edge(1,3,1));

        graph[2].add(new Edge(2,3,1));
    }
    
    
    

    public static void createGraph7(ArrayList<Edge> graph[]){
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,3,1));
        //graph[0].add(new Edge(0,2,1));

        //graph[1].add(new Edge(1,4,1));

        graph[2].add(new Edge(2,3,1));

        graph[3].add(new Edge(3,1,1));

        graph[4].add(new Edge(4,0,1));
        graph[4].add(new Edge(4,1,1));

        graph[5].add(new Edge(5,0,1));
        graph[5].add(new Edge(5,2,1));


    }

    //has path
    public static boolean hasPath(ArrayList<Edge> graph[],int src,int dest,boolean vis[]){//O(V+E)
        vis[src] = true;
        if(src == dest){
            return true;
        }
        for(int i = 0;i<graph[src].size();i++){
            Edge e = graph[src].get(i);
            if(!vis[e.dest] && hasPath(graph,e.dest,dest,vis)){
                return true;
            }
        }
        return false;
    }

    //all path from src to dest 
    public static void allPath(ArrayList<Edge> graph[],int src,int dest,String path){
        
        if(src == dest){
            System.out.println(path+dest);
            return;
        }
        
        for(int j = 0;j < graph[src].size();j++){
            Edge e = graph[src].get(j);
            allPath(graph,e.dest,dest,path+src);
            
        }   
    }

   
    
    public static void main(String[ ] args){
       int V = 6;
       @SuppressWarnings("unchecked")
       ArrayList<Edge> graph[] = new ArrayList[V];
       createGraph7(graph);
       allPath(graph, 5, 1, "");
        
    }
}
  