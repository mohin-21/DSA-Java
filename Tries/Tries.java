package Tries;
import java.util.*;

public class Tries {
    static class Node{
        Node children[] = new Node[26];
        boolean eow = false;//eow -> endOfWord
        public Node(){
            for(int i = 0;i<26;i++){
                children[i] = null; 
            }
        }
    }
    public static Node root = new Node();
    public static void insert(String word){
        Node cur = root;
        for(int i = 0;i<word.length();i++){
            int idx = word.charAt(i)-'a';
            if(cur.children[idx] == null){
               cur.children[idx] =  new Node();
            }
            cur = cur.children[idx];
        }
        cur.eow = true;
    }
    public static boolean search(String key){
        Node cur = root;
        for(int i = 0;i<key.length();i++){
            int idx = key.charAt(i)-'a';
            if(cur.children[idx] == null){
                return false;
            }
            cur = cur.children[idx];
        }
        return cur.eow == true;
    }

    public static boolean starsWith(String prefix){
        Node cur = root;
        for(int i = 0;i<prefix.length();i++){
            int idx = prefix.charAt(i) - 'a';
            if(cur.children[idx] == null){
                return false;
            }
            cur = cur.children[idx];
        }
        return true;
    } 

    public static int countNodes(Node root){
        if(root == null){
            return 0;
        }
       
        int count = 0;
        for(int i = 0;i<26;i++){
            if(root.children[i] != null){
                count += countNodes(root.children[i]);
            }
        }
        return count+1;
    }
    public static String ans = "";
    public static void longestWord(Node root,StringBuilder sb){
        if(root == null){
            return;
        }
        for(int i = 0;i<26;i++){
            if(root.children[i] != null && root.children[i].eow == true){
                char ch = (char) (i +'a');
                sb.append(ch);
                if(sb.length() > ans.length()){
                    ans = sb.toString();
                }
                longestWord(root.children[i],sb);
                sb.deleteCharAt(sb.length()-1);
            }
            
            
        }
        
    }
    //group anagram 
    public static List<List<String>> groupAnagram(String str[]){
        HashMap<String,List<String>> map = new HashMap<>();
        //sorted ch - AL(orginal str)
        for(String s : str){
            char[] strd=  s.toCharArray();
            Arrays.sort(strd);
            String sorted = String.valueOf(strd);
            if(!map.containsKey(sorted)){
                map.put(sorted,new ArrayList<>());

            }
            map.get(sorted).add(s);
        }
        return new ArrayList<>(map.values());
    }

    static class TrieNode{
        TrieNode children []= new TrieNode[26];
        String word;
        public TrieNode(){
        //    for(int i = 0;i<26;i++){
        //     tr.children[i] = null;
        //    }
        }
    }
    public static void build(String s){
        TrieNode cur = tr;
        // for(int i = 0;i<s.length();i++){
        //     int idx = s.charAt(i)-'a';
        //     if(cur.children[idx] == null){
        //         cur.children[idx] = new TrieNode();

        //     }
        //     cur = cur.children[idx];
        // }
        for(char ch : s.toCharArray()){
            if(cur.children[ch-'a'] == null){
                cur.children[ch -'a'] = new TrieNode();
            }
            cur = cur.children[ch - 'a'];
        }
        cur.word = s;
    }

    private static String res = "";
    private static TrieNode tr = new TrieNode();
    //longest word in dictionary (that can be buit one char in a time)
    public static String longestDic(String str[]){
        tr = new TrieNode();
        for(String st : str){
            build(st);
        }
        dfs(tr);
        return res;

    }
    private static void dfs(TrieNode root){
        if(root == null){
            return;
        }
        if(root.word != null){
            if(root.word.length() > res.length()){
                res = root.word;
            }else if(root.word.length() == res.length() && root.word.compareTo(res) < 0){
                res = root.word;
            }
        }
        for(TrieNode child : root.children){
            if(child != null && child.word != null){
                dfs(child);
            }

        }
    }
        
    
    public static void main(String[] args){
       String str[] = {"a","banana","app","ap","appl","apple","apply"};
    //    for(int i = 0;i<str.length;i++){
    //     insert(str[i]);

    //    }
       
      System.out.println(longestDic(str)); 
    }
}
