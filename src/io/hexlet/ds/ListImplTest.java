package io.hexlet.ds;

import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

import static org.junit.Assert.*;

public class ListImplTest {

    @Test
    public void testSizeWhenSizeIs0() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testIsEmptyWhenEmpty() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testToArrayWhenInputArrayHaveSizeOne() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[1];

        final Integer[] result = testInstance.toArray(input);
        assertNotEquals(input, result);
        assertEquals((Integer)1, result[0]);
        assertEquals((Integer)2, result[1]);
        assertEquals((Integer)3, result[2]);
        assertEquals(3, result.length);
    }

    @Test
    public void testToArrayWhenInputArrayHaveCorrectSize() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[3];

        final Integer[] result = testInstance.toArray(input);
        assertEquals(input, result);
    }

    @Test
    public void testContains() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(2);

        assertTrue(testInstance.contains(1));
        assertFalse(testInstance.contains(0));
    }

    @Test
    public void testAdd() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(1);

        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveFirstElement() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.remove(1);

        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveLastElement() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.remove(2);

        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testContainsAll() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        final Collection<Integer> testInstance2 = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(1);

        assertTrue(testInstance.containsAll(testInstance2));
    }

    @Test
    public void testAddAll() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance.add(3);
        testInstance.add(4);

        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
    }

    @Test
    public void testRemoveAll() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        final Collection<Integer> testInstance2 = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.removeAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(1));
    }

    @Test
    public void testRetainAll() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        final Collection<Integer> testInstance2 = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.retainAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(2));
    }

    @Test
    public void testClear() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(1);

        testInstance.clear();

        assertTrue(testInstance.isEmpty());
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testRemoveBeforeNext() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        testInstance.add(2);

        final Iterator<Integer> iter = testInstance.iterator();
        try {
            iter.remove();
            fail("remove do not throw the Exception when called before next");
        } catch (final IllegalStateException e) {}
    }

    @Test
    public void testNextOnEmptyCollection() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(2);

        final Iterator<Integer> iter = testInstance.iterator();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
        try {
            iter.next();
            fail("next do not throw the Exception when no more ellements");
        } catch (final java.util.NoSuchElementException e) {}
    }

    @Test
    public void testHasPreviouseWhenIteratorAtTheEndOfTheCollection() {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(2);

        final ListIterator<Integer> listIterator = testInstance.listIterator();
        listIterator.next();

        assertTrue(listIterator.hasPrevious());
    }

    @Test
    public void testPreviouseIndexWhenItEqualsTo1() {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(1);

        final ListIterator<Integer> listIterator = testInstance.listIterator();
        listIterator.next();
        listIterator.next();

        assertEquals(1, listIterator.previousIndex());
    }

    @Test
    public void testSetWhenNeitherNextNorPreviousHaveBeenCalled() {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);

        final ListIterator<Integer> listIterator = testInstance.listIterator();

        try {
            listIterator.set(null);
            fail("set method do not throw IllegalStateException the if neither next nor previous have been called");
        } catch (final IllegalStateException e){}
    }

    @Test
    public void testSet() {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);

        final ListIterator<Integer> listIterator = testInstance.listIterator();
        listIterator.next();
        listIterator.set(2);
        assertEquals((Integer)2, testInstance.get(0));
    }

    @Test
    public void testPreviouseOnCollectionWithOneElement() {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);

        final ListIterator<Integer> listIterator = testInstance.listIterator();
        final Integer next = listIterator.next();
        final Integer previous = listIterator.previous();

        assertEquals(next, previous);
    }

    @Test
    public void testPreviouseIndex() {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);

        final ListIterator<Integer> listIterator = testInstance.listIterator();
        listIterator.next();

        assertEquals(0, listIterator.previousIndex());
    }

    @Test
    public void testPreviouseIndexWhenEmptyCollection() {
        final ListImpl<Integer> testInstance = new ListImpl<>();

        final ListIterator<Integer> listIterator = testInstance.listIterator();

        assertEquals(-1, listIterator.previousIndex());
    }

    @Test
    public void testPreviouseWhenEmptyCollection() {
        final ListImpl<Integer> testInstance = new ListImpl<>();

        final ListIterator<Integer> listIterator = testInstance.listIterator();

        try {
            listIterator.previous();
            fail("list iterator do not throw the Exception when called previous method on empty collection");
        } catch (final java.util.NoSuchElementException e) {}
    }

    @Test
    public void testHasPreviouseWhenEmptyCollection() {
        final ListImpl<Integer> testInstance = new ListImpl<>();

        final ListIterator<Integer> listIterator = testInstance.listIterator();

        assertFalse(listIterator.hasPrevious());
    }

    @Test
    public void testRemoveTwoTimeInTheRow() throws Exception {
        final Collection<Integer> testInstance = new ListImpl<>();
        testInstance.add(1);
        testInstance.add(2);

        final Iterator<Integer> iter = testInstance.iterator();
        iter.next();
        iter.remove();
        assertEquals("Expected collection size is 1, however actual is not", 1, testInstance.size());
        try {
            iter.remove();
            fail("remove do not throw the Exception when called twice");
        } catch (final IllegalStateException e) {}
    }

    @Test
    public void testAddAt0() throws Exception {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        Integer n = 0;
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(0, n);
        assertEquals(testInstance.get(0), n);
    }

    @Test
    public void testAddAtEnd() throws Exception {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        Integer n = 3;
        testInstance.add(0);
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3,n);
        assertEquals(testInstance.get(3), n);
    }

    @Test
    public void testAddAt1() throws Exception {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        Integer n = 1;
        testInstance.add(0);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(1,n);
        assertEquals(testInstance.get(1), n);
    }

    @Test
    public void testAddAt4() throws Exception {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        Integer n = 4;
        testInstance.add(0);
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(5);
        testInstance.add(6);
        testInstance.add(4,n);
        assertEquals(testInstance.get(4), n);
    }

    @Test
    public void testRemoveAt0() throws Exception {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        Integer n = 1;
        testInstance.add(0);
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.remove(0);
        assertEquals(testInstance.get(0), n);
    }

    @Test
    public void testRemoveAt6() throws Exception {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        Integer n = 1;
        testInstance.add(0);
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.remove(5);
        assertEquals(testInstance.size(), 5);
    }

    @Test
     public void testRemoveAt2() throws Exception {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        Integer n = 2;
        testInstance.add(0);
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        assertEquals(testInstance.remove(2), n);
        assertEquals(testInstance.size(), 5);
    }

    @Test
    public void testRemoveAt0WhenSizeIs0() throws Exception {
        final ListImpl<Integer> testInstance = new ListImpl<>();
        Integer n = 0;
        testInstance.add(0);
        assertEquals(testInstance.remove(0), n);
        assertEquals(testInstance.size(), 0);
    }
}
