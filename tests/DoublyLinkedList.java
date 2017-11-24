

import java.util.*;

/**
 * @author Judah Schwartz
 * Very thoroughly unit-tested.
 * This took forever, so I'm hoping for a lot of extra credit =D
 * @param <T>
 */
public class DoublyLinkedList<T> implements List<T> {
    private Node<T> head, tail; //endOfParentLinkConnection;//afterTail is usefull for sublist
    private DoublyLinkedList<T> mainList;//in the case that this is a sublist, this will refer to the main list
    private boolean appendMainListSize(int i)
    {
        if(mainList== null)
        {
            return false;
        }
        mainList.size = mainList.size + i;
        /*
        In the event that the main list is itself a sublist
        This will recursively append all the lists sizes
         */
        if(mainList.mainList != null)
        {
            mainList.appendMainListSize(i);
        }
        return true;
    }

    private int size = 0;

    private static class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private T data;

        public Node() {
        }

        public Node(T t) {
            data = t;
        }

        // void foo(){LinkedList.this.size=0;}
    }
    @Override
    public boolean equals(Object o)
    {
        if(! (o instanceof DoublyLinkedList))
        {
            return false;
        }
        DoublyLinkedList<T> that = (DoublyLinkedList) o;
        if(that.size != size)
        {
            return false;
        }
        Iterator<T> iterator = iterator();
        Iterator<T> thaterator = that.iterator();
        while(iterator.hasNext())
        {
            if(!thaterator.hasNext())
            {
                return false;
            }
            if(!iterator.next().equals(thaterator.next()))
            {
                return false;
            }
        }
        if(thaterator.hasNext())
        {
            return false;
        }
        return true;
    }
    // private (inner) class
    // static (inner) class = used when the inner class object does not need access to outer class



    public DoublyLinkedList() {
        head = new Node<T>(); tail = new Node<T>();//two dummy nodes
        head.next = tail;
        tail.prev = head;
    }


    public DoublyLinkedList(Collection<T> clc) {
        this();
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

       for(T t : this)
       {
            if(t.equals(o))
            {
                return true;
            }
       }
       return false;
    }

    //HW5 XTRA so that a ConcrrentModificationException is thrown appropriately
    class MyLinkedListIterator implements Iterator<T> {
        int size = size();

        private Node<T> currentNode = head;

        @Override
        public boolean hasNext() {
            if (currentNode.next == tail)
                return false;
            else
                return true;
        }

        @Override
        public T next() {
            if (size != size()) {
                throw new ConcurrentModificationException("The list was changed");
            }
            T temp = currentNode.next.data;
            currentNode = currentNode.next;
            return temp;
        }
    }

    @Override
    public Iterator<T> iterator()
    {
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
        Node<T> newNode = new Node<>(t);
        newNode.prev = tail.prev;

        newNode.next = tail;
        newNode.prev.next = newNode;
        tail.prev = newNode;


        size++;
        appendMainListSize(1);
        return true;

    }

    @Override //HW4
    public boolean remove(Object o) {

        Node<T> current = head.next;
        while(!current.data.equals(o))
        {
            if(current.next == tail)
            {
                return false;//wasn't in the list
            }
            current = current.next;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        return true;
    }

    @Override //HW5
    public boolean containsAll(Collection<?> collection) {

        for (Object o : collection) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override//HW5
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.size() == 0) {
            return false;
        }
        for (Object o : collection) {
            add((T) o);
        }
        return true;
    }

    @Override//HW5
    public boolean addAll(int i, Collection<? extends T> collection) {

        Node<T> beforeNode = getNode(i - 1);
        Node<T> afterNode = beforeNode.next;
        Node<T> current = beforeNode;
        for (Object o : collection) {
            Node<T> newNode = new Node<>((T) o);

            newNode.next = current.next;
            newNode.prev = current;
            newNode.next.prev = newNode;
            current.next = newNode;



            current = newNode;
            size++;
            appendMainListSize(1);
        }
        current.next = afterNode;
        return true;
    }

    @Override//HW5
    public boolean removeAll(Collection<?> collection) {
        boolean changed = false;
        for (Object o : collection) {
            if (remove(o)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override //HW4
    public void clear() {
        head.next = tail;
        tail.prev = head;
        appendMainListSize(-size);
        size = 0;

    }

    @Override //HW4
    public T get(int i)
    {
       return getNode(i).data;

    }

    private Node<T> getNode(int i) {
        throwOutOfBoundsExceptionIfNeeded(i);
        Node<T> current;
        if(i<= size/2) {
            current = head.next;
            while (i > 0) {
                current = current.next;
                i--;
            }
        }
        else
        {
            current = tail.prev;
            int tempSize = size-1;
            while(tempSize > i){
                current = current.prev;
                tempSize--;
            }
        }
        return current;
    }

    private void throwOutOfBoundsExceptionIfNeeded(int i) {
        if (i >= size() || i < 0)
            throw new IndexOutOfBoundsException();
    }

    @Override //HW5
    public T set(int i, T t) {
        Node<T> node = getNode(i);
        T temp = node.data;
        node.data = t;
        return temp;
    }

    @Override //HW5
    public void add(int i, T t) {
        Node<T> newNode = new Node(t);
        Node<T> beforeNode = getNode(i - 1);
        Node<T> afterNode = beforeNode.next;
        newNode.prev = beforeNode;
        newNode.next = afterNode;
        beforeNode.next = newNode;
        afterNode.prev = newNode;
        size++;
        appendMainListSize(i);
    }

    @Override //HW5
    public T remove(int i) {
        Node<T> node = getNode(i);
        T data = node.data;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return data;

    }

    @Override //HW5
    public int indexOf(Object o) {
        Node<T> current = head.next;
        for(int i = 0; i < size; i++)
        {
           if(current.data.equals(o))
           {
               return i;
           }
           current = current.next;
        }
        return -1;
    }

    @Override //HW5
    public int lastIndexOf(Object o) {
        Node<T> current = tail.prev;
        for(int i = size-1; i >= 0; i--)
        {
            if(current.data.equals(o))
            {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    @Override //HW5 XTRA
    public ListIterator<T> listIterator() {

        return new DLLIterator();
    }

    private class DLLIterator implements ListIterator<T> {
        int nextIndex = 0;
        boolean addOrRemoveCalledRecently = true;//starts off true because must call next or prev before using add/remove
        Node<T> recentlyReturnedNode;
        Node<T> nextNode =  head.next;
        Node<T> prevNode = null;

        @Override
        public boolean hasNext() {
            if (nextNode == tail) {
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            nextIndex++;
            T temp = nextNode.data;
            recentlyReturnedNode = nextNode;
            prevNode = nextNode;
            nextNode = nextNode.next;
            addOrRemoveCalledRecently = false;
            return temp;

        }

        @Override
        public boolean hasPrevious() {
            if (prevNode == head) {
                return false;
            }
            return true;
        }

        @Override
        public T previous() {
            nextIndex--;
            T temp = prevNode.data;
            recentlyReturnedNode = prevNode;
            nextNode = prevNode;
            prevNode = prevNode.prev;
            addOrRemoveCalledRecently = false;
            return temp;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            if (addOrRemoveCalledRecently)
                throw new IllegalStateException("Can't call add or remove twice before calling next or prev");
            if(hasPrevious())
                recentlyReturnedNode.prev = recentlyReturnedNode.next;
            else if(hasNext())
                recentlyReturnedNode.next = recentlyReturnedNode.prev;
            else {
                clear();
                nextNode = tail;
                prevNode = head;

            }// this was the only thing in list. reset the list


            addOrRemoveCalledRecently = true;//to ensure that can't be called twice in a row
            size--;
            appendMainListSize(-1);

        }

        @Override
        public void set(T t) {
            recentlyReturnedNode.data = t;
        }

        @Override
        public void add(T t) {
            Node<T> newNode = new Node<>(t);
            newNode.prev = prevNode;
            newNode.next = nextNode;
            prevNode.next = newNode;
            nextNode.prev = newNode;
            prevNode = newNode;//cursor is now past the new node
            addOrRemoveCalledRecently = true;
            size++;
            appendMainListSize(1);
            nextIndex++;
        }


    }

    @Override //HW5 XTRA
    public ListIterator<T> listIterator(int i) {
        DLLIterator literator = new DLLIterator();
        if (i < size / 2) {

            throwOutOfBoundsExceptionIfNeeded(i);
            for (int j = 0; j < i; j++) {
                literator.next();
            }
        } else {

            literator.prevNode = tail.prev;
            literator.nextIndex = size;
            for (int j = size; j > i; j--) {
                literator.previous();
            }
        }
        return literator;

    }
    public void printOut()//for testing purposes
    {
        for(T t: this)
        {
            System.out.println(t);
        }
    }
    @Override //HW5 XTRA If I've understood the API correctly, this is NOT supposed to be a deep copy
    public List<T> subList(int i, int i1) {
        throwOutOfBoundsExceptionIfNeeded(i);
        throwOutOfBoundsExceptionIfNeeded(i1);
        if(i > i1)
        {
            throw new IndexOutOfBoundsException("second argument is less than first");
        }

        DoublyLinkedList<T> sut = new DoublyLinkedList<>();
        sut.mainList = this;
        Node<T> futureFirst = getNode(i);
        Node<T> futureLast = futureFirst;
        for(int ind = i;ind < i1;ind++)
            futureLast = futureLast.next;

        sut.head = futureFirst.prev;
        sut.tail = futureLast.next;
        //this is so that sublist(i,i1).clear() will clear a section of the original but connect the ends

        sut.size = i1 - i+1;
        return sut;


    }
}