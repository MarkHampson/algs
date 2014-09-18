//import java.util.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.Iterator;
 
public class RandomizedQueue<Item> implements Iterable<Item>{ 

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
//    public String toString(){
//      String s = new String();
//      for(int i=0; i<size(); i++) s+= items[i];
//      return s;
//  }

    //randomly select and remove an item
    public Item dequeue(){
        int randomIndex;
        randomIndex = StdRandom.uniform(N);        
        //swap the randomly selected index with the last element in the array
        Item temp = items[N-1];
        items[N-1] = items[randomIndex];
        items[randomIndex] = temp; 
        N--;
        return items[N]; //N is now the last element since it was just decremented
    }
    //randomly select and return, but don't remove item
    public Item sample(){
        int randomIndex;
        randomIndex = StdRandom.uniform(N);
        return items[randomIndex];
    }
    //implement the iterator() method:
    public Iterator<Item> iterator(){
        return new RandomIterator<Item>(this);
    }
    //implement the Iterator<Item> class:
    private class RandomIterator<Item> implements Iterator<Item>{
        private RandomizedQueue<Item> iterateMe;
        private int[] indexArray;
        private int current;

        public RandomIterator(RandomizedQueue<Item> iterateMe){
            this.iterateMe = iterateMe;  //save the pointer    
            indexArray = new int[iterateMe.size()];
            for(int i=0; i<iterateMe.size(); i++){
                indexArray[i] = i; //initialize by counting
            }
            //now randomize the indexing array
            StdRandom.shuffle(indexArray);                
            current = 0;
        }
        public boolean hasNext(){ 
            return current < iterateMe.size();
        }
        public void remove(){ 
            throw new UnsupportedOperationException(); 
        }
        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            int index = indexArray[current]; //get the random index
            Item item = iterateMe.items[index]; //retrieve the item
            current++;
            return item;
        }
    }


    public static void main(String[] args){
        if(args.length < 2) throw new java.lang.IllegalArgumentException("More than one arg pls");        
        RandomizedQueue<String> myQ = new RandomizedQueue();
        System.out.println("Size: " + myQ.size());
        System.out.println("Empty?: " + myQ.isEmpty());
        for(int i=0; i<args.length; i++){
            myQ.enqueue(args[i]);
            System.out.print(args[i]);
        }
        System.out.println("");
        System.out.println("Size: " + myQ.size());
        System.out.println("Empty?: " + myQ.isEmpty());
        //Give me an iterator
        Iterator<String> iter1 = myQ.iterator();
        //Give me another
        Iterator<String> iter2 = myQ.iterator();
        while( iter1.hasNext() || iter2.hasNext() ){
            System.out.print(iter1.next());
            System.out.print(iter2.next());
            System.out.println("");
        }
    }
}
