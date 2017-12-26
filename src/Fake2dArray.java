import java.util.HashMap;

public class Fake2dArray<T> {
    private static class Node
    {
        private int x,y,hashCode;
        Node(int xVal, int yVal)
        {

            x = xVal;
            y = yVal;

        }
        @Override
        public boolean equals(Object o)
        {
            if(! (o instanceof Node))
            {
                return false;
            }
            Node that = (Node) o;
            return x == that.x && y == that.y;
        }
        @Override
        public int hashCode()
        {

            int result = 17;
            result = 31* result + x;
            result = 31* result + y;
            return result;
        }
    }
    HashMap<Node,T> hashMap = new HashMap<>();
    public Fake2dArray()
    {

    }
    public void set(int x,int y, T elt)
    {
        hashMap.put(new Node(x,y),elt);

    }
    public T get(int x,int y)
    {
        return hashMap.get(new Node(x,y));
    }
}
