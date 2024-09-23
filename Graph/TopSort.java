package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TopSort {
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
    //toplogical sort
    public static void topSort(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        Stack<Integer> s = new Stack<>();
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                topSortUtil(graph,vis,s,i);
            }
        }
        while(!s.isEmpty()){
            System.out.print(s.pop()+" ");
        }

    }
    public static void topSortUtil(ArrayList<Edge>[]graph,boolean vis[],Stack<Integer>s,int cur){
        vis[cur] = true;
       
        for(int i = 0;i<graph[cur].size();i++){
            Edge e = graph[cur].get(i);
            if(!vis[e.dest]){
                topSortUtil(graph,vis,s,e.dest);
            }
        }
        s.push(cur);

    }

    
    public static void indegree(ArrayList<Edge> graph[],int indegree[]){
        for(int i = 0;i<graph.length;i++){
            int v = i;
            for(int j = 0;j<graph[v].size();j++){
                Edge e = graph[v].get(j);
                indegree[e.dest]++;
            }
        }
    }
    //topological sort using kan's algorithm
    public static void topSort2(ArrayList<Edge>[]graph){
        int indegree[] = new int[graph.length]; 
        indegree(graph, indegree);
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0;i<indegree.length;i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int curr = q.remove();
            System.out.print(curr+" ");
            for(int i = 0;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                indegree[e.dest]--;
                if(indegree[e.dest] == 0){
                    q.add(e.dest);
                }
            }
        }

    }
}
