package SegmentTree;
public class Segment{
    static int tree[];
    public static void init(int n){
        tree = new int[4*n];
    }
    public static int buildSt(int arr[], int i, int start, int end){
        if(start == end){
            return tree[i]= arr[start];
        }
        int mid = (start+end)/2;
        int left = buildSt(arr,2*i+1,start, mid);
        int right = buildSt(arr,2*i+2,mid+1,end);
        tree[i] = left+right;
        return tree[i];
        
    } 
    public static int queryUtil(int i, int si, int sj, int qi, int qj){
        if(si > qj || sj < qi){
            return 0;
        }
        if(si >= qi && sj<= qj){
            return tree[i];
        }else{
            int mid = (si+sj)/2;
            int left = queryUtil(2*i+1, si, mid, qi, qj);
            int right = queryUtil(2*i+2, mid+1, sj, qi, qj);
            return left+right;
        }
    }
    public static int querySum(int arr[], int qi, int qj){//calculate sum
        int n = arr.length;
        return queryUtil(0,0,n-1,qi, qj);
    }
    public static void updateUtil(int i, int si, int sj, int idx, int diff){
        if(idx > sj || idx < si){
            return;
        }
        tree[i]+= diff;
        if(si != sj){
            int mid = (si+sj)/2;
            updateUtil(2*i+1,si,mid,idx,diff);
            updateUtil(2*i+2,mid+1,sj,idx,diff);
        }
    }
    //update
    public static void update(int arr[],int idx,int newVal){
        int n = arr.length;
        int diff = newVal - arr[idx];
        arr[idx] = newVal;
        updateUtil(0,0,n-1,idx, diff);
    }
    //to find max of a given range
    public static void buildSt2(int arr[],int i, int si, int ei){
        if(si == ei){
            tree[i] = arr[si];
            return;
        }
        int mid = (si+ei)/2;
        buildSt2(arr, 2*i+1, si, mid);
        buildSt2(arr, 2*i+2, mid+1, ei);
        tree[i] = Math.max(tree[2*i+1],tree[2*i+2]);
    }

    public static int getMaxUtil(int i, int si, int sj, int qi, int qj){
        if(sj < qi || si > qj){
            return Integer.MIN_VALUE;
        }
        if(si >= qi && sj <= qj){
            return tree[i];
        }else{
            int mid = (si+sj)/2;
            int left = getMaxUtil(2*i+1, si, mid, qi, qj);
            int right = getMaxUtil(2*i+2,mid+1,sj,qi,qj);
            tree[i] = Math.max(left,right);
            return tree[i];
        }
    }
    public static int getMax(int arr[],int qi,int qj){
        int n = arr.length;
        return getMaxUtil(0,0,n-1,qi,qj);
    }
    public static void update2(int arr[],int idx,int newVal){
        int n = arr.length;
       
        arr[idx] = newVal;
       
        updateUtil2(0,0,n-1,idx,newVal);
    }
    public static void updateUtil2(int i, int si, int sj, int idx, int newVal){
        if(idx > sj || idx < si){
            return;
        }
        if(si== sj){
            tree[i] = newVal;
        }
       
        
        if(si != sj){
            tree[i] = Math.max(tree[i],newVal);
            int mid = (si+sj)/2;
            updateUtil2(2*i+1,si,mid,idx,newVal);
            updateUtil2(2*i+2, mid+1, sj, idx, newVal);
        }
        
    }  
    //to convert min change max to min in update2,buildsT2 and also util2
    public static void main(String[] agrs){
        System.out.println("Last chapter of DSA series");
        int arr[] = {6,8,-1,2,17,1,3,2,4};
        int n = arr.length;
        init(n);
        buildSt2(arr, 0, 0, n-1);
        for(int i=0; i<tree.length; i++){
            System.out.print(tree[i]+ " ");
        }System.out.println();
        System.out.println(getMax(arr, 2, 5));
        update2(arr, 2, 20);
        System.out.println(getMax(arr, 2, 5));
        for(int i=0; i<tree.length; i++){
                System.out.print(tree[i]+ " ");
            }System.out.println();
    }
}