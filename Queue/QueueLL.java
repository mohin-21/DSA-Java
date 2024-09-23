package Queue;

public class QueueLL {
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    
    static class QueueL{
        static Node head = null;
        static Node tail = null;

        public static boolean isEmpty(){
            return head == null;
        }

        //add
        public static void add(int data){
            Node newNode = new Node(data);
            if(head == null){
                head = tail = newNode;
                return;
            }

            tail.next = newNode;
            tail = newNode;
        }

        //remove
        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return - 1;
            }

            int front = head.data;
            if(head == tail){
                head = tail = null;
            }else{
                head = head.next;
            }
            return front;
        }

        //peek
        public static int peek(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return - 1;
            }

            return head.data;
        }
    }

    public static void main(String[] args){
        QueueL ql = new QueueL();

        ql.add(1);
        ql.add(2);
        ql.add(3);

        while(!ql.isEmpty()){
            System.out.println(ql.peek());
            ql.remove();
        }
    }

}
