import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
    
    private int N;  //size of list
    private Node<Item> first;
    private Node<Item> last;

    private static class Node<Item>{

        private Item item;
        private Node<Item> next;

    }
    
    public Deque(){
        first = null;
        last = null;
        N = 0;
    }
    public boolean isEmpty() { return first == null; }
    public int size() { return N; }
    //public void addFirst(Item item)
    //public void addLast(Item item)
    //public Item removeFirst()
    //public Item removeLast()

    //make the compiler happy for now:
    public Iterator<Item> iterator(){ return new DequeIterator<Item>(first); }
    
    //This whole class is a placeholder -
    //it's just bullshit for now.
    private class DequeIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public DequeIterator(Node<Item> first){
            current = first;
        }

        public boolean hasNext(){ return false; }
        public void remove(){ throw new UnsupportedOperationException(); }
        public Item next(){ return current.item; }

    }

    public static void main(String[] args){
        Deque<Integer> intList = new Deque<Integer>();
        System.out.println("Size : " + intList.size());
        if(intList.isEmpty()) System.out.println("It's empty.");
        else System.out.println("It's not empty.");
    }
}
