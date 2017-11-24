/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SchwartzPC
 */
public class MyListTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    public DoublyLinkedList<String> populatedList()
    {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        for(int i = 'A'; i < 'J'; i++)
        {
            list.add("" + (char)i);
        }
        return list;
    }
    @Test
    public void subListTest()
    {
        DoublyLinkedList<String> sut = populatedList();
        DoublyLinkedList<String> subSut = (DoublyLinkedList) sut.subList(1,3);//b,c,d
        assertTrue(subSut.contains("B"));
        assertEquals(subSut.size(),3);
        assertEquals(sut.size(),9);
        subSut.add("D2");
        assertEquals(subSut.size(),4);
        assertEquals(sut.size(),10);
        sut.printOut();
        subSut.clear();
        assertTrue(sut.size() == 6);
        assertFalse(sut.contains("D"));
        assertFalse(sut.contains("B"));
        sut.printOut();

        subSut = (DoublyLinkedList)sut.subList(0,2);
        subSut.clear();
        assertTrue(sut.size()==3);
        sut.printOut();

        sut.addAll(populatedList());
        assertEquals(12,sut.size());
        subSut = (DoublyLinkedList) sut.subList(11,11);

        assertTrue(subSut.contains("I")&&subSut.size()==1);
    }
    @Test
    public void listIteratorTest()
    {
        DoublyLinkedList<String> sut = populatedList();
        ListIterator<String> listIterator = sut.listIterator();
        while (listIterator.hasNext())
        {
            System.out.println(listIterator.next());
        }
        System.out.println();
        while (listIterator.hasPrevious())
        {
            System.out.println(listIterator.previous());
        }

        listIterator = sut.listIterator(1);
        assertEquals(listIterator.next(),"B");
        assertEquals("C",listIterator.next());
    }
    @Test
    public void containTest()
    {
        DoublyLinkedList<String> sut = populatedList();
        DoublyLinkedList<String> sut2 = new  DoublyLinkedList<>();
        sut2.add("A");
        sut2.add("I");
        sut2.add("D");
        assertTrue(sut.containsAll(sut2));
        sut2.add("W");
        assertFalse(sut.containsAll(sut2));
        sut.add(5,"W");
        assertTrue(sut.containsAll(sut2));
        assertTrue(sut.containsAll(sut));
    }
    @Test
    public void equalsTest()
    {
        DoublyLinkedList<String> a1 = populatedList();
        DoublyLinkedList<String> a2 = populatedList();
        assertEquals(a1,a2);
        a1.addAll(a2);
        assertFalse(a1.equals(a2));
        a2.addAll(new DoublyLinkedList<>(a2));
        assertEquals(a1,a2);
        a1.add(4,"hello");
        assertNotEquals(a1,a2);
        a2.add(4,"hello");
        assertEquals(a1,a2);
    }

    @Test
    public void removeTest()
    {
        DoublyLinkedList<String> a1 = new DoublyLinkedList<>();
        String s = "K";
        for(int i = 'A';i<'J';i++)
        {
            a1.add(""+(char)i);
        }
        a1.add(s);
        a1.printOut();
        assertTrue(a1.remove(s));
        assertFalse(a1.remove(s));
        a1.remove("D");
        System.out.println();
        a1.printOut();

    }
    @Test
    public void removeAllTest()
    {
        DoublyLinkedList<String> a1 = new DoublyLinkedList<>();
        String s = "K";
        for(int i = 65;i<75;i++)
        {
            a1.add(""+(char)i);
        }
        ArrayList<String> a2 = new ArrayList<>();
        a2.add("C");
        a2.add("F");
        a2.add("A");
        a1.removeAll(a2);
        a1.printOut();

    }
    @Test
    public void addTest()
    {
        DoublyLinkedList<String> a1 = new DoublyLinkedList<>();

        for(int i = 'A';i<'J';i++)
        {
            a1.add(""+(char)i);
        }

        a1.printOut();
        System.out.println();

        ArrayList<String> a2 = new ArrayList<>();
        a2.add("Hello");
        a2.add("");
        a2.add("yes");
        a1.addAll(8,a2);
        a1.addAll(3,a2);
        a1.printOut();
        //  a1.add(new Object());

    }
    @Test
    public void addiTest()
    {
        DoublyLinkedList<String> a1 = new DoublyLinkedList<>();
        for(int i = 'A';i<'P';i++)
        {
            a1.add(""+(char)i);
        }
        a1.add(10,"W");
        a1.printOut();


    }
    @Test public void forEachTest()
    {
        DoublyLinkedList<Integer> a1 = new DoublyLinkedList<>();
        for(int i = 0; i < 9; i++)
        {
            a1.add(i);
        }
        for(Integer in: a1)
        {
            System.out.println(in);
        }
    }
    @Test
    public void getTest()
    {

        DoublyLinkedList<String> a1 = populatedList();
       // a1.printOut();
        System.out.println(a1.get(a1.size()-1));
        for(int i = 0; i < a1.size(); i++)
        {
            System.out.println(i + a1.get(i));
        }
        assertEquals(a1.get(0),"A");
        System.out.println(a1.get(8));
        System.out.println(a1.size());
        assertEquals(a1.get(a1.size()-1),"I");
    }
    @Test
    public void getAndIndexTest()
    {
        DoublyLinkedList<String> l1 = new DoublyLinkedList<>();
        l1.add("Hello");
        l1.add("World");
        assertEquals(l1.get(1),"World");
        assertEquals(l1.indexOf("World"),1);
    }
    @Test
    public void regularAddTest()
    {
        DoublyLinkedList<String> a1 = new DoublyLinkedList<>();

        for(int i = 'A';i<'J';i++)
        {
            a1.add(""+(char)i);
        }

        a1.printOut();
        System.out.println();

        ArrayList<String> a2 = new ArrayList<>();
        a2.add("Hello");
        a2.add("");
        a2.add("mama");
        a2.add("yes");
        a1.addAll(a2);
        a1.printOut();
    }

}
