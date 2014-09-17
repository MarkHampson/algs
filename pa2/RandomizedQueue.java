//import java.util.UnsupportedOperationException;
//import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> { //implements Iterable<Item>

    private Item[] items;   //The generic array
    private int N;          //The item count

    public RandomizedQueue(){
        items = (Item[]) new Object[2]; //Initialize the array,
                                        //have to cast to generic type
                                        //because of lameness 
        N = 0;                          //Initialize array count
    }

    public boolean isEmpty(){
        return (N == 0);
    }

    public int size(){
        return N;
    }

    public void enqueue(Item item){
        if(items.length == size()){                          //queue is full, double it
            Item[] newItems = (Item[]) new Object[2*size()]; //doubled array size
            for(int i=0; i<size(); i++){
                newItems[i] = items[i];     //copy each element of old array
            }
            items = newItems;       //reassign our private array reference
        }
        items[N++] = item;      //stick the new item in the array,
                                //and increment the item count

    }
    
//This cannot be in the final version (extra public method):
    public String toString(){
        String s = new String();
        for(int i=0; i<size(); i++) s+= items[i];
        return s;
    }


    //public Item dequeue()
    //public Item sample()
    //public Iterator<Item> iterator()
    public static void main(String[] args){
        RandomizedQueue<String> myQ = new RandomizedQueue();
        System.out.println("Size: " + myQ.size());
        System.out.println("Empty?: " + myQ.isEmpty());
        
        for(int i=0; i<args.length; i++){
            myQ.enqueue(args[i]);
        }
        System.out.println("Size: " + myQ.size());
        System.out.println("Empty?: " + myQ.isEmpty());
        System.out.println(myQ);
    }
}
