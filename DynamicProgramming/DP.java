package DynamicProgramming;
import java.util.*;

public class DP {
    //undertanding of dp 
    //calculate fibonacci for a natural number using dp
    public static int fib(int n,int dp[]){//memoization
        if(n == 1 || n == 0){
            return n;
        }
        if(dp[n] != 0){
            return dp[n];
        }
        dp[n] = fib(n-1,dp) + fib(n-2,dp);
        return dp[n];
    }

    //climbing stairs
    public static int climbStairs(int n){//tabulation
        int dp[] = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    // 0-1 knapsack 
    public static int knapsack(int val[],int wt[],int W,int n){
        if(n == 0 || W == 0){
            return 0;
        }
        if(wt[n-1] <= W){
            //include 
            int ans1 = val[n-1]+knapsack(val,wt,W-wt[n-1],n-1);
            //exclude
            int ans2 = knapsack(val,wt,W,n-1);
            return Math.max(ans1,ans2);
        }else{
            //exclude 
            return knapsack(val, wt, W, n-1);
        }
    }
    //using memoization
    public static int knapsackMemo(int val[],int wt[],int n,int W,int dp[][]){
        if(n == 0 || W == 0){
            return 0;
        }
        if(dp[n][W] != -1){
            return dp[n][W];
        }
        if(wt[n-1] <= W){
            //include
            int ans1 = val[n-1]+knapsackMemo(val,wt,n-1,W-wt[n-1],dp);
            //exclude
            int ans2 = knapsackMemo(val,wt,n-1,W,dp);
            dp[n][W] = Math.max(ans1,ans2);
            return dp[n][W];
        }else{
            dp[n][W] = knapsackMemo(val,wt,n-1,W,dp);
            return dp[n][W];
        }
       
    }
    public static int knapsackTab(int val[],int wt[],int n,int W){
        int dp[][] = new int[val.length+1][W+1];
        for(int i = 0;i<dp.length;i++){
            dp[i][0] = 0;
        }
        for(int j = 0;j<dp[0].length;j++){ 
            dp[0][j] = 0;
        }
        for(int i = 1;i<n+1;i++){
            for(int j = 1;j<W+1;j++){
                int v = val[i-1];
                int w = wt[i-1];
                if(wt[i-1]<=j){
                    //include
                    int inc  = v+dp[i-1][j-w];
                    //exclude
                    int exc = dp[i-1][j];
                    dp[i][j] = Math.max(inc,exc);

                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        printDp(dp);
        return dp[n][W];
    }
    //target sum subset
    public static boolean targetSum(int arr[],int target){
        int n = arr.length;
        boolean dp[][] = new boolean[n+1][target+1];
        //initialize dp array
        for(int i = 0;i<dp.length;i++){
           
            dp[i][0] = true;
            
        }
        for(int i = 1;i<dp[0].length;i++){
          
            dp[0][i] = false;;
               
        }
        for(int i = 1;i<n+1;i++){
            for(int j = 1;j<target+1;j++){
                //valid
                int v =  arr[i-1];
                if(v <= j && dp[i-1][j-v] == true ){
                    dp[i][j] = true;
                }else if(dp[i-1][j] == true){
                    dp[i][j] = true;
                }
            }
        }
       
        return dp[n][target];
    }
    //unbounded knapsack
    public static int unboundedKnap(int val[],int wt[],int W){
        int n = val.length;
        int m = W;
        //create
        int dp[][] = new int[n+1][m+1];
        //initialize
        for(int i = 0;i<n+1;i++){
            for(int j = 0;j<m+1;j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
            }
        }
        //fill bottom up manner
        for(int i = 1;i<n+1;i++){
            for(int j = 1;j<m+1;j++){
                int v = val[i-1];
                int w = wt[i-1];
                if(w <= j){
                    //include
                    int ans1 = v + dp[i][j-w];
                    //exclude
                    int ans2 = dp[i-1 ][j];
                    dp[i][j] = Math.max(ans1,ans2);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][m];
    }
    public static void printDp(int dp[][]){
        for(int i = 0;i<dp.length;i++){
            for(int j = 0;j<dp[0].length;j++){
                System.out.print(dp[i][j]+" ");
            }System.out.println();
        }System.out.println();
    }

    //rod cutting problem
    public static int rodCutting(int price[],int len[],int rodLen){
        int n = price.length;
        int dp[][] = new int[n+1][rodLen+1];
        for(int i = 0;i<n+1;i++){
            for(int j = 0;j<rodLen+1;j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
            }
        }
        for(int i = 1;i<n+1;i++){
            for(int j = 1;j<rodLen+1;j++){
                int v = price[i-1];
                int w = len[i-1];
                if(w <= j){
                    int ans1 = v+dp[i][j-w];
                    int ans2 = dp[i-1][j];
                    dp[i][j] = Math.max(ans1,ans2);
                }else{
                    dp[i][j] = dp[i-1][j];  
                }
            }
        }
        return dp[n][rodLen];
        
    }

    public static int rodCuttingMemo(int len[],int price[],int rodLen,int n,int dp[][]){
        if(rodLen == 0 || n == 0){
            return 0;
        }
        if(dp[n][rodLen] != -1){
            return dp[n][rodLen];
        }
       if(len[n-1] <= rodLen){ 
        int ans1 = price[n-1]+rodCuttingMemo(len, price, rodLen-len[n-1], n-1,dp);
        int ans2 = rodCuttingMemo(len, price, rodLen, n-1,dp);
        return dp[n][rodLen] = Math.max(ans1,ans2);
       }else{
        return dp[n][rodLen] = rodCuttingMemo(len, price, rodLen, n-1,dp);
       }
        
        
    }
    //longest common subsequence
    public static int lcm(String s1,String s2,int n,int m){
        if(m == 0 || n == 0){
            return 0;
        }
        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return lcm(s1, s2, n-1, m-1)+1;
        }else{
            int ans1 = lcm(s1, s2, n-1, m);
            int ans2 = lcm(s1, s2, n, m-1);
            return Math.max(ans1,ans2);

        }
    }
    public static int lcmMemo(String s1,String s2,int n,int m,int dp[][]){
        if(n == 0 || m == 0){
            return 0;
        }
        if(dp[n][m] != -1){
            return dp[n][m];
        }
        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return dp[n][m] = lcmMemo(s1,s2,n-1,m-1,dp)+1;
        }else{
            int ans1 = lcmMemo(s1, s2, n-1, m,dp);
            int ans2 = lcmMemo(s1,s2,n,m-1,dp);
            return dp[n][m] = Math.max(ans1,ans2);
        }
    }
    //longest common subsequence using tabulation 
    public static int lcm2(String s1,String s2){
        int n = s1.length(),m = s2.length();
        int dp[][] = new int[n+1][m+1];
        for(int i = 0;i<dp.length;i++){
            for(int j = 0;j<dp[0].length;j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
            }
        }
        for(int i = 1;i<dp.length;i++){
            for(int j = 1;j<dp[0].length;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    int a1 = dp[i-1][j];
                    int a2 = dp[i][j-1];
                    dp[i][j] = Math.max(a1,a2);
                }
            }
        }
        //printDp(dp);
        return dp[n][m];

    } 

    public static int lcmA(int arr1[],int arr2[]){
        int n = arr1.length;
        int m = arr2.length;
        int dp[][] = new int[n+1][m+1];
        for(int i = 0 ;i<n+1;i++){
            for(int j = 0;j<m+1;j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
            }
        }
        for(int i = 1;i<n+1;i++){
            for(int j = 1;j<m+1;j++){
                if(arr1[i-1] == arr2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    int ans1 = dp[i-1][j];
                    int ans2 = dp[i][j-1];
                    dp[i][j] = Math.max(ans1,ans2);
                }
            }
        }
        return dp[n][m];
    }
    //longest increasing subsuquence
    public static int lis(int arr1[]){
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0;i<arr1.length;i++){
            set.add(arr1[i]);
        }
        int arr2[] = new int[arr1.length];
        int j = 0;
        for(int i : set){
            arr2[j] = i;
            j++;
        }
        Arrays.sort(arr2);
        return lcmA(arr1,arr2);
    } 
    public static int longestComSubstring(String s1,String s2){
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n+1][m+1];
        for(int i = 0;i<n+1;i++){
            for(int j = 0;j<m+1;j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
            }
        }
        int ans = 0;
        for(int i = 1;i<n+1;i++){
            for(int j = 1;j<m+1;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1 ]+1;
                    ans = Math.max(ans,dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        printDp(dp);
        return ans;
        
    }
    //edit distance
    public static int editDis(String str1,String str2){
        int n = str1.length();
        int m = str2.length();
        int dp[][] = new int[n+1][m+1];
        for(int i = 0;i<n+1;i++){
            for(int j = 0;j<m+1;j++){
                if(j == 0){
                    dp[i][j] = i;
                }
                if(i == 0){
                    dp[i][j] = j;
                }
            }
        }  
        for(int i = 1;i<n+1;i++){
            for(int  j = 1;j<m+1;j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    int a = dp[i][j-1]+1;
                    int d = dp[i-1][j]+1;
                    int r = dp[i-1][j-1]+1;
                    dp[i][j] = Math.min(a,Math.min(d,r));
                }
            }
        }
        return dp[n][m];
    }
    
    public static int stringCon(String s1,String s2){
        int lcm = lcm2(s1,s2);
        int diff = s1.length()-lcm;
        int add = s2.length() - lcm;
        return diff+add;
    }

    //wildcard matching 
    public static boolean wildCard(String text,String p){
        int n = text.length();
        int m = p.length();
        boolean dp[][] = new boolean[n+1][m+1];
        //initialize
        for(int j= 1;j<n+1;j++){
            dp[j][0] = false;
        }
        dp[0][0] = true;
        for(int i =1; i<m+1; i++){ 
            if(p.charAt(i-1) == '*'){
                dp[0][i] = dp[0][i-1];
            }else{
                dp[0][i] = false;
            }
        }
        for(int i = 1; i<n+1; i++){
            for(int j=1; j<m+1; j++){
                if(text.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }else if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }else{
                    dp[i][j] = false;
                }
            }
        }
        return dp[n][m];
    } 

    //catalan number 
    public static int catalan(int n){
        if(n == 0 || n == 1){
            return 1;
        }
        int cn = 0;
        for(int i = 0;i<=n-1;i++){
            int ci = catalan(i);
            int cni = catalan(n-i-1);
            cn += ci*cni;
        }
        return cn;
    }

    public static int catalanMemo(int n,int dp[]){
        if(n == 0 || n == 1){
            return 1;
        }
        if(dp[n] != -1){
            return dp[n];
        }
        int ans = 0;
        for(int i = 0; i<=n-1; i++){
            ans+= catalan(i) * catalan(n-i-1);
        }
        return dp[n] = ans;
    }
    //tabulation
    public static int catalanTab(int n){
        int dp[] = new int[n+1];
        dp[0] = dp[1] = 1;
        for(int i = 2; i<=n; i++){
            int cn= 0;
            for(int j = 0; j<=i-1; j++){
                cn+= dp[j]*dp[i-j-1];
                //int cnj = dp[i];
            }
            dp[i] = cn;
        }
        return dp[n];
    }

    //matrix chain multiplication(mcm)
    public static int mcm(int arr[],int i, int j){
        if(i == j){
            return 0;
        }
        int ans = Integer.MAX_VALUE ;
        for(int k = i; k<=j-1; k++){
            int cost1 = mcm(arr,i,k);
            int cost2 = mcm(arr,k+1,j);
            int cost3 = arr[i-1]*arr[k]*arr[j];
            ans = Math.min(ans, cost1+cost2+cost3);
        }
        return ans;
    }
    public static int mcmMemo(int arr[],int i, int j, int dp[][]){
        if(i == j){
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int ans = Integer.MAX_VALUE;
        for(int k=i; k<=j-1; k++){
            int cost1 = mcmMemo(arr, i, k, dp);
            int cost2 = mcmMemo(arr, k+1, j, dp);
            int cost3 = arr[i-1]*arr[k]*arr[j];
            ans = Math.min(ans, cost1+cost2+cost3);

        }
        return dp[i][j]= ans;
    }
    public static int mcmTab(int arr[]){
        int n = arr.length;
        int dp[][] = new int[n+1][n+1];
        for(int i =0; i<n+1; i++){
            dp[i][i] = 0;
        }

        for(int len = 2; len<=n-1; len++){
            for(int i=1; i<=n-len; i++){
                int j = i+len-1;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=i; k<=j-1; k++){
                    int cost1 = dp[i][k];
                    int cost2 = dp[k+1][j];
                    int cost3 = arr[i-1]*arr[k]*arr[j];
                    dp[i][j] = Math.min(dp[i][j], cost1+cost2+cost3);
                }
            }
        }
        return dp[1][n-1];
    }

    public static int minDiff(int arr[]){
        int sum = 0;
        int n = arr.length;
        for(int i = 0;i<arr.length;i++){
            sum+= arr[i];
        }
        //0 1 knapsack
        int W = sum/2;
        int dp[][] = new int[n+1][W+1];
        //initialize 

        //buttom up fill
        for(int i = 1; i<arr.length+1; i++){
            for(int j=1; j<W+1; j++){
                int v = arr[i-1];
                int w = arr[i-1];
                if(arr[i-1] <= j){
                    int ans1 = v+dp[i-1][j-w];
                    int ans2 = dp[i-1][j];
                    dp[i][j] = Math.max(ans1, ans2);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        int sum1 = dp[n][W];
        int sum2 = sum - sum1;
        return Math.abs(sum1 - sum2);
    }

    public static void print(int dp[]){
        for (int i : dp) {
            System.out.print(i+" ");
        }System.out.println();
    }
    public static int minJumps(int arr[]){
        int n = arr.length;
        int dp[] = new int[n   ];
        dp[n-1] = 0;
        for(int i=n-2; i>=0; i--){
            int steps = arr[i];
            int ans = Integer.MAX_VALUE;
            for(int j=i+1; j<=i+steps && j<n; j++){
               
                if(dp[j] != -1){
                    ans = Math.min(ans,dp[j]+1);  
                    
                }
               
            }
            if(ans != Integer.MAX_VALUE){
                dp[i] = ans;
            }
        }
        
        return dp[0];
       
    }

    public static void main(String args[]){
        int arr[] = {2,3,1,1,4};
        System.out.println(minJumps(arr));
        
    }   
}
  