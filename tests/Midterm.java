import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Midterm {
    //3
//class MyBackwardsIterator<T> implements Iterator<T>
//{
//    int nextIndex = size()-1;
//    @Override
//    public boolean hasNext() {
//        return  nextIndex < 0;
//    }
//
//    @Override
//    public T next() {
//        return backingArray[nextIndex--];
//    }
//}
//4 First of all, can use clone() that come in a stack, but if shouldnt use that, then:
    //O(n^3)
    public static Stack<String> duplicateStack(Stack<String> stack)
    {
        Stack<String> stackCopy = new Stack<String>();
        Queue<String> q = new LinkedList<String>();
        while(!stack.isEmpty())
        {
            stackCopy.add(stack.pop());
        }
        while (! stackCopy.isEmpty())
        {
            q.add(stackCopy.pop());
        }


        while (!q.isEmpty())
        {
            stack.add(q.peek());
            stackCopy.add(q.remove());
        }
        return stackCopy;
    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        s.push("Bottom");
        s.push("Middle");
        s.push("Top");
        System.out.println(s);
        Stack<String> s2 = duplicateStack(s);
        System.out.println(s);
        System.out.println(s2);
    }

}
//5
/*
Generally, non-amortized would be better. Amortized constant time means that usually an operation is order 1, and once in a while
an operation that takes more time will be called, but divide that up to how long it generally takes and its still constant time. This will
mean that once in a while the system will have a hiccup, but will usually be quick. Addng to an arraylist at the end is an example of this. Usually, this
would be order 1, but on occasion it has to shift all of its elements to a larger backing array, which is order n. On average, adding is still order 1.
Adding to a linked list at the end is non-amortized because this operation will never have to ocassionally take longer. If mcdonalds usually takes
one minute to serve a customer, and occasionally two hours, making it an average of 2 minutes per customer, but burger king has an average of 3 minutes per customer,
people might prefer to use burger king even if it is on average slower.

 */

//6 More efficient for arraylist - get from specific index, set at specific index
// more efficient for linked list - add to beginning, remove from beginning
//arraylists are random access, so requesting a specific index is order 1, for arraylist its order n
//removing and adding for an arraylists requires it to shift all elements. Linked lists just have to adjust its references so its order 1.