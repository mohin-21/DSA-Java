package Queue;

public class CircularQueue {
    static class CQ{
        static int arr[];
        static int size;
        static int front;
        static int rear;

        public CQ(int n){
            arr = new int[n];
            size = n;
            front = -1;
            rear = -1;
        }

        public static boolean isEmpty(){
            return front == - 1 && rear == -1;
        }

        public static boolean isFull(){
            return (rear+1) % size == front;
        }

        public static void add(int data){
            if(isFull()){
                System.out.println("Queue is Full");
                return;
            }
            //1st element
            if(front == -1){
                front = 0;
            }
            rear = (rear+1) % size;
            arr[rear] = data;
        }

        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is Empty");
                return -1;
            }

            int res = arr[front];
            if(front == rear){
                front = rear = -1;
            }else{
                front = (front + 1) % size;
            }
            return res;
        }

        public static int peek(){
            if(isEmpty()){
                System.out.println("Queue is Empty");
                return -1;
            }
            return arr[front];
        }
    }
    public static void main(String[] args){
        CQ q = new CQ(3);
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q.remove());
        q.add(4);
        System.out.println(q.remove());
        q.add(5);
        while(!q.isEmpty()){
            System.out.println(q.peek());
            q.remove();
        }
    }
}
