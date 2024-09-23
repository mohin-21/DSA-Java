package Heap;

import java.util.ArrayList;

public class HeapImplement {

    static class Heap {
        static ArrayList<Integer> heap = new ArrayList<>();

        // add
        public static void add(int data) {
            heap.add(data);
            int x = heap.size() - 1;
            int par = (x - 1) / 2;
            while (heap.get(x) < heap.get(par)) {
                int temp = heap.get(x);
                heap.set(x, heap.get(par));
                heap.set(par, temp);
                x = par;
                par = (x - 1) / 2;
            }
        }

        // get element
        public static int peek() {
            return heap.get(0);
        }

        // heapify -> fix the heap
        public static void heapify(int i) {
            int minIdx = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < heap.size() && heap.get(minIdx) > heap.get(left)) {
                minIdx = left;
            }
            if (right < heap.size() && heap.get(minIdx) > heap.get(right)) {
                minIdx = right;
            }
            if (minIdx != i) {
                int temp = heap.get(i);
                heap.set(i, heap.get(minIdx));
                heap.set(minIdx, temp);
                heapify(minIdx);
            }
        }

        // remove element
        public static int remove() {
            int data = heap.get(0);
            // step 1
            int temp = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.set(heap.size() - 1, temp);
            // step 2
            heap.remove(heap.size() - 1);
            // step 3 heapify
            heapify(0);
            return data;

        }

        public static boolean isEmpty() {
            return heap.size() == 0;
        }

    }

    public static void main(String[] args){
        //Heap is implmented by priority queue in JCF
    }

}
