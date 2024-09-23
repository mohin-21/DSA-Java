import java.util.ArrayList;

public class ALImplement {
    static class StackAL{
        static ArrayList<Integer> list = new ArrayList<>();

        public static boolean isEmpty(){
            return list.size() == 0;
        }

        //push - add
        public static void push(int data){
            list.add(data);
        }

        //pop - remove
        public static int pop(){
            if(isEmpty()){ //corner case
                return -1;
            }

            int top = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return top;
        }

        //peek - watch the top element 
        public static int peek(){
            if(isEmpty()){
                return -1;
            }

            return list.get(list.size() - 1);
        }
    }
    public static void main(String[] args){
       StackAL s = new StackAL();
       s.push(1);
       s.push(2);
       s.push(3);
       s.push(4);
       s.push(5);

       while(!s.isEmpty()){
        System.out.println(s.peek());
        s.pop();
       }
        
    }
}
