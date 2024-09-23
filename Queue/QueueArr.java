package Queue;

public class QueueArr {
    static class QueueA{
        int arr[];
        int size;
        int rear;
        public QueueA(int n){
            arr = new int[n];
            size = n;
            rear = -1;
        }

        public boolean isEmpty(){
            return rear == -1;
        }

        //add - enqueue
        public void add(int data){
            if(rear == size - 1){
                System.out.println("Queue is full");
                return;
            }
            rear++;
            arr[rear] = data;
        }

        //remove - deque
        public int remove(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return - 1;
            }
            int front = arr[0];
            for(int i=0; i<rear; i++){
                arr[i] = arr[i+1];
            }
            rear--;
            return front;
        }

        //peek 
        public int peek(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return - 1;
            }
            return arr[0];
        }
    }

    public static void main(String[] args){
        QueueA qa = new QueueA(3);
        qa.add(1);
        qa.add(2);
        qa.add(3);
        
        while(!qa.isEmpty()){
            System.out.println(qa.peek());
            qa.remove();
        }
    }
}
