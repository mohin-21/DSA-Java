package DynamicProgramming;
// import java.util.*;

public class DPQ{
    public static int tribonacci(int n){

        int dp[] = new int[n];
        dp[0]=dp[1] = 0;
        dp[2] = 1;
        for(int i = 3;i<n;i++){
            
            dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
        }
        print(dp);
        return dp[n-1];
    }
    
    public static void print(int dp[]){
        for (int  i : dp) {
            System.out.print(i+" ");
        }System.out.println();
    }
    public static int profit(int prices[],int fee){
        int max = 0;
        int sell = 0;
        int buy = prices[0];
        for(int i=1; i<prices.length; i++){
            sell = prices[i];
            if(sell > buy){
                max = Math.max(max,sell - buy+fee);
            }else{
                buy = prices[i];
            }
            
        }
        return max;
    }
    public static boolean isValid(int matrix[][],int x,int y){
        return x >=0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }
    
    public static int lip(int dp[][],int mat[][],int n,int m,int x,int y){
        // if(x == n-1 || y == m-1){
        //     return 1;
        // }
        
        if(dp[x][y] < 0){
            int result = 0;
            if (x == n - 1 && y == m - 1)
                return dp[x][y] = 1;

            // If reach the corner of the matrix.
            if (x == n - 1 || y == m - 1)
                result = 1;
            if(x+1 < n && mat[x+1][y] > mat[x][y]){
                result = lip(dp,mat, n, m, x+1, y)+1;
            }
            if(y+1 < m && mat[x][y+1] > mat[x][y]){
                result = Math.max(result,lip(dp,mat, n, m, x, y+1)+1);
            }
            dp[x][y] = result;
        }
        
        return dp[x][y];
    }
    
    public static void printP(char str[]){
        for(int i=0; i<str.length; i++){
            System.out.print(str[i]);
        }System.out.println();
        
    }
    //print balanced parentheses
    public static void printParen(char str[],int pos, int o,int c,int n){
        if(c == n){
            printP(str);
            return;
        }
        if(c<o){
            str[pos] = '}';
            printParen(str,pos+1, o, c+1, n);
        }
        if(o<n){
            str[pos] ='{';
            printParen(str, pos+1, o+1, c, n);
            
        }
    }
    //number of parenthesis combination
    public static int validCom(int n){
        if(n == 0 || n == 1){
            return 1;
        }
        int dp[] = new int[n+1];    
        dp[0] = dp[1] =1;
        for(int i=2; i<=n; i++){
            int ans = 0;
            for(int j=0; j<=i-1; j++){
                ans+= dp[j]*dp[i-j-1];
            }
            dp[i] = ans;
        }
        return dp[n];
    }

    public static void main(String[] args){
    //    int prices[] = {6,1,7,2,8,4};
    //    int fee = 2;
    //    System.out.println(profit(prices, fee));
        //int mat[][] = {{1,2,3,4},{2,2,3,4},{3,2,3,4},{4,5,6,7}};

        int n = 3;
        System.out.println(validCom(n));

 
    }
}