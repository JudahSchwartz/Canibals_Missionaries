

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.junit.Test;

import java.util.*;
import java.util.function.UnaryOperator;
public class MyArraylist<T> implements List<T> {
    private void throwExceptionIfOutOfBounds(int i)
    {
        if(i<0 || i> insertionPoint)
        {
            throw new IndexOutOfBoundsException("Given parameter is out of range");
        }
    }
    private T backingArray[];
    private int insertionPoint = 0, // next elt will be inserted here
            capacity = 10; // current length of backing array

    public MyArraylist(){
        backingArray = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return insertionPoint;
    }


    public void printOut(){
        for(int i = 0; i < insertionPoint; i++)
        {
            System.out.println(backingArray[i]);
        }

    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    /**
     *
     * @param o object to be found
     * @return index of object or -1 if not in list
     */
    @Override
    public int indexOf(Object o)
    {
        for(int i = 0; i < insertionPoint;i++)
        {
            if(backingArray[i].equals(o))
            {
                return i;
            }
        }
        return -1;
    }
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
           private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                return backingArray[index++];
            }
        };
    }

    @Override
    public T[] toArray() {
        return backingArray.clone();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(T t) {

        if (insertionPoint >= capacity) // must grow array
        {
            growArray();
        }
        backingArray[insertionPoint++] = t;

        return true;
    }

    private void growArray() {
        T [] copy =(T[]) new Object[capacity * 2 + 1];
        System.arraycopy(backingArray, 0, copy, 0, capacity);
        backingArray = copy;
        capacity = capacity * 2 + 1;
    }
    /**
     * Will grow the array at least to needed size, if the array isn't currently big enough
     * @param i number of additional indeces that are needed
     */
    private void growArrayToSize(int i)
    {
        if(capacity-insertionPoint>i)
        {
            return;//the array is already big enough
        }
        T [] copy =(T[]) new Object[capacity + i];
        System.arraycopy(backingArray, 0, copy, 0, capacity);
        backingArray = copy;
        capacity = capacity+i;
    }

    @Override
    public boolean remove(Object o) {

        int index = indexOf(o);
        if(index == -1)//o is not in the list
        {
            return false;
        }
        System.arraycopy(backingArray,index+1,backingArray,index,size()-index-1);
        insertionPoint--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        boolean containsAll = true;
        for(Object o: collection)
        {
            if(!contains(o))
            {
                containsAll = false;
            }
        }
        return containsAll;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if(collection.size()==0)
            return false;

        growArrayToSize(collection.size());
        for (T s : collection) {

            this.add(s);//can reuse this. Grow array will never be called in the add method
        }
        return true;
    }

    @Override //much more efficient then calling add on each element, only has to shift
    //the array once
    public boolean addAll(int i, Collection<? extends T> collection) {
        throwExceptionIfOutOfBounds(i);
        if(collection.size()==0)
            return false;
        growArrayToSize(collection.size());
        //new length of myArrayList
        //move all the data that would be overwritten by new material over, going backwards.
        System.arraycopy(backingArray,i,backingArray,i+collection.size(),size()-i-1);
        insertionPoint = insertionPoint + collection.size();//resetting the insertion point to after the list

        //insert new list into spot made for it
        for(T t: collection)
        {
            backingArray[i++] = t;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean allRemoved = true;
        for(Object o: collection)
        {
            if(!remove(o))//if this elt couldn't be removed
            {
                allRemoved = false;//this way the rest of the elts will still be removed. No return statement
            }

        }
        return allRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection)
    {
        boolean changed = false;
        for(T t: this)
        {
            if(!collection.contains(t))
            {
                remove(t);
                changed = true;//if was changed as a result
            }
        }
        return changed;
    }


    @Override
    public void replaceAll(UnaryOperator<T> unaryOperator) {

    }

    @Override
    public void sort(Comparator<? super T> comparator) {

    }

    @Override // FIXME as per Judah and Ben suggestion
    public void clear() {
        backingArray = (T[]) new Object[10];
        capacity = 10;
        insertionPoint = 0;
    }

    @Override
    public T get(int index) {
        throwExceptionIfOutOfBounds(index);
        return backingArray[index];
    }

    @Override //not sure what this is intended to return
    public T set(int i, T s) {
       throwExceptionIfOutOfBounds(i);
        T temp = backingArray[i];
        backingArray[i] = s;
        return temp;

    }

    @Override
    public void add(int i, T s) {
        throwExceptionIfOutOfBounds(i);
        if(insertionPoint>= capacity)
        {
            growArray();
        }
       System.arraycopy(backingArray,i,backingArray,i+1,size()-i);
        backingArray[i] = s;
        capacity++;
        insertionPoint++;
    }

    @Override // FIXME
    public T remove(int i) {
        T temp = backingArray[i];
        remove(backingArray[i]);
        return temp;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = insertionPoint -1; i > -1; i--)
        {
            if(get(i).equals(o))
            {
                return i;
            }
        }
        return -1;
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

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
