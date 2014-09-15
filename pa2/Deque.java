import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private int N;  //size of list
    private Node<Item> first;
    private Node<Item> last;

    private static class Node<Item>{

        private Item item;
        private Node<Item> next;
        private Node<Item> prev;

    }
    
    public Deque(){
        first = null;
        last = null;
        N = 0;
    }
    
    public boolean isEmpty() { return (first == null) || (last == null); }
    public int size() { return N; }

    //Cut in front of the line
    public void addFirst(Item item){
        boolean empty = isEmpty();
        Node<Item> oldFirst = first; //could be null if empty
        first = new Node<Item>();    //create new Node, point first to it
        first.item = item;           //save the item
        first.next = oldFirst;       //could be null if empty
        first.prev = null;           //nothing comes before first
        if(empty) last = first;      //only one element, last points to it too 
        else oldFirst.prev = first;   
        N++;
    }
    
    //Add to the end of the line
    public void addLast(Item item){
        boolean empty = isEmpty();   //check to see if empty
        Node<Item> oldLast = last;   //could be null if empty
        last = new Node<Item>();     //create new Node, point last to it
        last.item = item;            //store the new item
        last.next = null;            //this should always be null
        last.prev = oldLast;
        if(empty) first = last;      //only one element, first points to it too
        else oldLast.next = last;
        N++;
    }
    
    //Dequeue the front of the line
    public Item removeFirst(){
        if(isEmpty()) throw new NoSuchElementException("stack underflow");
        Item item = first.item;
        first = first.next;         //point to next in line, null if empty
        if(isEmpty()) last = null;  //remove the useless pointer
        else first.prev = null;     //first is not null, need to update prev
        N--;
        return item;
    }

    //Dequeue the back of the line
    public Item removeLast(){
        if(isEmpty()) throw new NoSuchElementException("stack underflow");
        Item item = last.item;
        last = last.prev;
        if(isEmpty()) first = null; //remove dangly pointer
        else last.next = null;      
        N--;
        return item;
    }

    
    //Implement the iterator() method:
    public Iterator<Item> iterator(){ return new DequeIterator<Item>(first); }
    
    //Implement the Iterator<Item> class: 
    private class DequeIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public DequeIterator(Node<Item> first){
            current = first;
        }

        public boolean hasNext(){ return current != null; }
        public void remove(){ throw new UnsupportedOperationException(); }
        public Item next(){ 
            if(!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item; 
            }
    }

    public static void main(String[] args){
        Deque<Integer> intList = new Deque<Integer>();
        System.out.println("Size : " + intList.size());
        if(intList.isEmpty()) System.out.println("It's empty.");
        else System.out.println("It's not empty.");
        for(int j=0; j<5; j++){
            System.out.println("Add " + j);
            intList.addFirst(j);
        }
        System.out.println("Size: " + intList.size());
        for(int i : intList) {
            System.out.println("Remove " + i);
            intList.removeLast();
        }
        System.out.println("Size: " + intList.size());
        System.out.println("Remaining: ");
        for(int k : intList) System.out.println(k);
    }
}
