
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*
Hi there # 1
SHmuel Morris asked a good question!
 */


public class MyLinkedList<T> implements List<T>
{
    class Node<T>
{
    Node<T> next;
    T data;
}
    private Node<T> head, tail;
    private int size;

    public MyLinkedList(){
        head = tail = null; // NC
    }
public void printOut()
{
    for(T t: this) {
        System.out.println(t);
    }
}
    public MyLinkedList(Collection<T> clc)
    {
        addAll(clc);
    }

    @Override //HW4
    public int size() {
        return size;
    }

    @Override //HW4
    public boolean isEmpty() {
        return size == 0;
    }

    @Override //HW4
    public boolean contains(Object o) {
        for(T t: this)
        {
            if(t.equals(o))
            {
                return true;
            }
        }
        return false;
    }

    class MyLinkedListIterator implements Iterator<T>
    {
        private Node<T> currentNode = head;
        @Override
        public boolean hasNext() {
            if (currentNode == null)
                return false;
            else
                return true;
        }

        @Override
        public T next() {
            T temp = currentNode.data;
            currentNode = currentNode.next;
            return temp;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override //HW4
    public boolean add(T t) {
        Node newNode = new Node();
        newNode.data = t;
        if (head == null) // first elt
        {
            head = tail = newNode;
        }
        else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override //HW4
    public boolean remove(Object o) {
        if(!contains(o))
        {
            return false;
        }
        Node<T> prev, current;
        prev = current = head;
        for(T t: this)
        {
            if(t.equals(o))
            {
                if(current == head)
                {
                    head = current.next;
                }
                else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }

            prev = current;
            if((indexOf(current.next.data)<=size()&&indexOf(current.next.data)>=0)) {
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean changed = false;
        for(Object o: collection)
        {
            changed = true;
            remove(o);
        }
        return changed;

    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override //HW4
    public void clear() {
        head = tail = null;
        size = 0;

    }
    private boolean checkForOutOfBoundsException(int i,boolean throwIt)
    {
        if(i>size()|| i < 0)
        {
            if(throwIt)
                throw new IndexOutOfBoundsException();
            return true;
        }
        return false;
    }
    @Override //HW4
    public T get(int i) {
        checkForOutOfBoundsException(i,true);
        Node<T> current = head;
        while(i > 0)
        {
            current = current.next;
            i--;
        }
        return current.data;
    }

    @Override
    public T set(int i, T t) {
        return null;
    }

    @Override
    public void add(int i, T t) {

    }

    @Override
    public T remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int counter = 0;
        for(T t : this)
        {
            if(t.equals(o))
            {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return null;
    }

    @Override
    public List<T> subList(int i, int i1) {
        return null;
    }
}
