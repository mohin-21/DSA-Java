package ArrayList;

//ArrayList implementation from Scratch

// import java.rmi.server.ObjID;

public class AL  <T> { //Generics

    private T[] asArray;
    
    @SuppressWarnings("unchecked")
    public AL(){
        asArray = (T[]) new Object[0];
    }

    //add in Al
    public void add(T t){
        @SuppressWarnings("unchecked")
        T[] temp = (T []) new Object[asArray.length + 1];
        for(int i=0; i<asArray.length; i++){
            temp[i] = asArray[i];
        }
        //add new eleement
        temp[asArray.length] = t;
        asArray = temp;
    }
    //remove from Al
    public void remove(int index){
        if(index < 0 || index > asArray.length){
            return;
        } 
        @SuppressWarnings("unchecked")
        T [] temp = (T[]) new Object[asArray.length-1];
        boolean isFound = false;
        //copy each item
        for(int i=0; i<asArray.length; i++){
            if(i == index){
                isFound = true;
                continue;
            }

            temp[i - (isFound ? 1 : 0)] = asArray[i];

        }
        asArray = temp;
    }

    //get a element from Al
    public T get(int index){
        return asArray[index];
    }

    //clone() size() 
    public int size(){
        return asArray.length;
    }

    //clear() 
    public void clear(){
        //  approach 1
        // @SuppressWarnings("unchecked")
        // T [] temp =(T[]) new Object[0];
        // asArray = temp;
      
        //approach 2
        removeRange(0, asArray.length);
    }
    
    //removeRange(si, ei)
    public void removeRange(int si, int ei){ //ei is exclude
        if(si < 0 || ei < 0 || si > asArray.length || ei > asArray.length){
            return;
        }
        int size = asArray.length - (ei - si);
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[size];
        int j = 0;
        for(int i=0; i<asArray.length; i++){
            if(i == si){
                i = ei;
                if(i == asArray.length){
                    continue;
                }
            }
            temp[j] = asArray[i];
            j++;
        }
        asArray = temp;
    }

    //clone
    // public T [] clone(){
    //     return print();
    // }

    //print al the element
    public void print(){
        for(int i=0; i<asArray.length; i++){
            System.out.print(asArray[i] + " ");
        }System.out.println();

    }

    public static void main(String[] args){
        System.out.println("Scratch Implement ");

        AL<Integer> al = new AL<Integer>();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);
        al.add(5);
        al.add(6);
        al.print();
        

        // AL<String> sal = new AL<String>();

        // sal.add("Mohin");
        // sal.add("cpi");
        // sal.add("Rahim");
        // sal.add("shraya");
        // sal.add("Rahi");
        // sal.print();
        // System.out.println(sal.size());

        

    }
}
