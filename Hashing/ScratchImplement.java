package Hashing;

import java.util.ArrayList;
import java.util.LinkedList;

public class ScratchImplement {
    static class HashMapCode<K,V>{
        private class Node{
            K key;
            V value;
            public Node(K key,V value){
                this.key = key;
                this.value = value;
            }
        }
        private int n;//n
        private int N;
        private LinkedList<Node> buckets[];//  N
        @SuppressWarnings("unchecked")
        public HashMapCode(){
            this.N = 4;
            this.buckets = new LinkedList[4];
            for(int i = 0;i<4;i++){
                buckets[i] = new LinkedList<Node>();
            }
        }

        private int hashFunction(K key){
            int hc = key.hashCode();
            return Math.abs(hc) % N;
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
            N = N*2;
            for(int i = 0;i<buckets.length;i++){
                buckets[i] = new LinkedList<Node>();
            }
            for(int i = 0;i<oldBuckets.length;i++){
                LinkedList<Node> ll = oldBuckets[i];
                Node node = ll.remove();
                put(node.key,node.value);
            }
        }
        //insert
        public void put(K key, V value){
            int bi = hashFunction(key);
            int di = searchInLL(key,bi);

            if(di != -1){
                Node node = buckets[bi].get(di);
                node.value = value;
            }else{
                buckets[bi].add(new Node(key,value));
                n++;
            }
            double lambda =(double) n/N;
            if(lambda > 2.0){
                rehash();
            }

        }
        //containsKey
        public boolean containsKey(K key){
            int bi = hashFunction(key);
            int di = searchInLL(key,bi);
            if(di != -1){
                return true;
            }else{
                return false;
            }
        }
        //get 
        public V get(K key){
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if(di != -1){
                Node node = buckets[bi].get(di);
                return node.value;
            }else{
                return null;
            }
        }
        //remove
        public V remove(K key){
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if(di != -1){
                Node node = buckets[bi].remove(di);
                n--;
                return node.value;
            }else{
                return null;
            }
        }
        //keySet
        public ArrayList<K> keySet(){
            ArrayList<K> keys = new ArrayList<>();
            for(int i = 0;i<buckets.length;i++){
                LinkedList<Node> ll = buckets[i];
                for(Node n: ll){
                    keys.add(n.key);
                }
            }
            return keys;
        }
        public boolean isEmpty(){
            return n == 0;
        }
    }

    public static void main(String[] args){
        HashMapCode<Integer, Integer> hm = new HashMapCode<Integer,Integer>();
        hm.put(0, 10);
        hm.put(1, 20);
        System.out.println(hm.get(0));
        System.out.println(hm.containsKey(1));
    }
}
