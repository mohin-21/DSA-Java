package LinkedList;
// import java.util.LinkedList;

public class LL {
    public static class Node{
        int val;
        Node next;
        public Node(int v){
            this.val = v;
            this.next = null;
        }
    }
    public static Node head = null;

    //insert() -  (begining, at any point, ending)
    //begining
    public void addFirst(int val){
        Node newNode = new Node(val);
        if(head == null){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }
    //at any point/index
    public void add(int index, int val){
        if(size() < index){
            return;
        }
        Node newNode = new Node(val);
        if(head == null){
            head = newNode;
            return;
        }
        int i = 0;
        Node curNode = head;
        Node preNode = null;
        while(curNode != null){
            if(i == index){
                preNode.next = newNode;
                newNode.next = curNode;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }

    }
    //ending
    public void addLast(int val){
        Node newNode = new Node(val);
        if(head == null){
            head = newNode;
            return;
        }
        Node lastNode = head;
        while(lastNode.next != null){
            lastNode = lastNode.next;
        }
        lastNode.next = newNode;

    }
    //remove() - (begining, at any point, ending)
    //begining
    public static int removeFirst(){
        if(head == null){
            return -1;
        }
        int val = head.val;
        head = head.next;
        return val;
    }

    //remove at any point/index
    public int remove(int index){
        int i = 0;
        int val = 0;
        Node curNode = head;
        Node preNode = null;
        while(curNode != null){
            if(i == index){
                val = curNode.val;
                preNode.next = curNode.next;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return val;
    }
    //ending
    public static int removeLast(){
        if(head == null){
            return -1;
        }
        Node lastNode = head.next;
        Node preNode = head;
        while(lastNode.next != null){
            preNode = preNode.next;
            lastNode = lastNode.next;
        }
        int val = lastNode.val;
        preNode.next = null;
        return val;
    }
    //search()
    public static int search(int index){
        if(size() < index){
            return -1;
        }
        if(head == null){
            return -1;
        } 
        Node temp = head;
        int i = 0;
        while(temp != null){
            if(i == index){
                return temp.val;
            }
            i++;
            temp = temp.next;
        }
        return -1;
    }
    //size()
    public static int size(){
        if(head == null){
            return 0;
        }
        Node temp = head;
        int count = 0;
        while(temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }
    //print()
    public void print(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.val+ "->");
            temp = temp.next;
        }System.out.println("null");
    }
   

    public static void main(String args[]){
        System.out.println("Some code here");
        LL ll = new LL();
        //ll.addLast(0);
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.addLast(5);
        ll.addLast(6);
        
        
        ll.print();
        
        System.out.println( ll.remove(2));
        ll.print();
    }
}
