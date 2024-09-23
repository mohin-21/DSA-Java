package Graph;

import java.util.ArrayList;

public class EdgeImplement {
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

    //Bi-directional / undirected graph
    public static void createGraph(int V,ArrayList<Edge> graph[]){
        
        for(int i = 0; i<V; i++){
            graph[i] = new ArrayList<>();
        }

        //0 vertex
        graph[0].add(new Edge(0,1,5));

        //1 vertex
        graph[1].add(new Edge(1,0,5));
        graph[1].add(new Edge(1,2,1));
        graph[1].add(new Edge(1,3,3));

        //2 vertex
        graph[2].add(new Edge(2,1,1));
        graph[2].add(new Edge(2,3,1));
        graph[2].add(new Edge(2,4,2));

        //3 vertex
        graph[3].add(new Edge(3,1,3));
        graph[3].add(new Edge(3,2,1));
        graph[3].add(new Edge(3,5,1));
        graph[3].add(new Edge(3,4,1)); 

        //4 vertex
        graph[4].add(new Edge(4,2,2));
        graph[4].add(new Edge(4,3,1));
        graph[4].add(new Edge(4,5,1));
        

        //5 vertex
        graph[5].add(new Edge(5,3,1));
        graph[5].add(new Edge(5,4,1));
        graph[5].add(new Edge(5,6,1));

        //6 vertex
        graph[6].add(new Edge(6,5,1));

        //2's neighbors
        // for(int i = 0;i<graph[2].size();i++){
        //     Edge e = graph[2].get(i);
        //     System.out.print(e.dest+" ");
        // }
    }

    // directed / unidirected graph
    public static void createGraph(ArrayList<Edge> graph[]){
        
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,1,1));
        graph[0].add(new Edge(0,2,1));

        graph[1].add(new Edge(1,3,1));

        graph[2].add(new Edge(2,3,1));
    }
    

    public static void main(String args[]){
        //open terminal and run this to execute code 
        //javac Graph/EdgeImplement.java                                                                                      
        //java Graph.EdgeImplement

        int V = 7;
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(V, graph);

        for(int i = 0;i<graph[2].size();i++){
            Edge e = graph[2].get(i);
            System.out.print(e.dest+" ");
        }

        
    }
}
