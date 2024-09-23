package Greedy;
import java.util.*;

public class Greedy {
    public static String balancedString(String str){
        int l = 0,r = 0;
        int ans = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<str.length();i++){
            if(str.charAt(i) == 'L'){
                l++;
                sb.append(str.charAt(i));
            }else if(str.charAt(i) == 'R'){
                r++;
                sb.append(str.charAt(i));
            }
            if(r == l){
               System.out.println(sb+" ");
               sb = new StringBuilder(); 
                ans++;
            }
        }
        System.out.println(ans);
        return sb.toString();
    }
    //kth largest odd number a given range
    public static int kthLargestOdd(int range[],int k){
        int L = range[0];
        int R = range[1];
        System.out.println((R & 1));
        if((R & 1)>0){//odd case
            int count = (R-L+1)/2;
            if(k>count){
                return 0;
            }
            return (R-2*k+2);

        }else{//even case/ number
            int count = (R-L+1)/2;
            if(k>count ){
                return 0;
            }
            return (R-2*k+1);
        }
    } 
    //lexicographically smallest string
    public static char[] lexico(int n,int k){
        char arr [] = new char[n];
        Arrays.fill(arr,'a');
        for(int i = n-1;i>=0;i--){
            // k-=i;
            if(k > 0){
                if(k>= 26){
                    arr[i] ='z';
                    k-=26;
                }else{
                    char c = (char)(k+97-1);
                    arr[i] = c;
                    k-= arr[i]-'a'+1;
                }
            }else{
                break;
            }
            //k+=i;
           
           
        }
        return arr;
    } 
    public static boolean isProbablePrime(int n){
        for(int i = 2;i<n-1;i++){
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }
    //buy and sell stock
    public static int buyAndSell(int prices[]){
        int maxProfit = 0;
        int buyPrice = prices[0];
        for(int i = 1;i<prices.length;i++){
            int curPrice = prices[i];
            if(curPrice> buyPrice){
                maxProfit = Math.max(maxProfit,(curPrice - buyPrice));
            }else{
                buyPrice = curPrice;
            }
        }
        return maxProfit;
    }
    public static void main(String[] args){
        //split the given array into k subarrays remind

        int prices[] = {7,6,4,3,1};
        System.out.println(buyAndSell(prices));
        
      
      


    }
}
 