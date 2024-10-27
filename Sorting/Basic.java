package Sorting;

public class Basic {
    public static void bubbleSort(int arr[]){
        int n = arr.length;
        for(int i=0; i<n; i++){
            for(int j=0; j<n-i-1; j++){
                if(arr[j] > arr[j+1]){
                    //swap
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int arr[]){
        //arr = 5,4,1,3,2
        int n = arr.length;
        for(int i=0; i<n-1; i++){
            int minPos = i;
            for(int j=i; j<n-1; j++){
                if(arr[minPos] > arr[j]){
                    minPos = j;
                }
            }
            //only necessary swap/ swap directly with largest and smallest of ith iteration
            int temp = arr[minPos];
            arr[minPos] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(int arr[]){
        int n = arr.length;
        for(int i=1; i<n; i++){
            int cur = arr[i];
            int prev = i-1;
            while(prev >=0 && cur < arr[prev]){
                arr[prev+1] = arr[prev];
                prev--;
            }
            arr[prev+1] = cur;
        }
    }

    public static void coutingSort(int arr[]){
        int n = arr.length;
        int largest = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            largest = Math.max(largest, arr[i]);
        }
        int count[] = new int[largest+1];
        for(int i=0; i<n; i++){
            count[arr[i]]++;
        }

        int j = 0;
        //sorting
        for(int i=0; i<count.length; i++){
            while(count[i] > 0){
                arr[j] = i;
                j++;
                count[i]--;
            }
        }
    }

    public static void printAr(int arr[]){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + ", ");
        }System.out.println();
    }
   
    public static void main(String[] args){
        int arr[] = {2,3,1,2,3,5,5,6,4,1,5,9};
        coutingSort(arr);
        printAr(arr);

    }
}
