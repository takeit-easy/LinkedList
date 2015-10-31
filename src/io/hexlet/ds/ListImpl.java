package io.hexlet.ds;

import java.util.List;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

public class ListImpl<T> implements List<T> {

    private Item<T> first = null;

    private Item<T> last = null;

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        Item currentItem = first;
        while (currentItem != null) {
            if (currentItem.getElement() == o)
                return true;
            currentItem = currentItem.getNext();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] mArray = new Object[size()];
        Item currentItem = first;
        for (int i = 0; i < size(); i++) {
            mArray[i] = currentItem.getElement();
            currentItem = currentItem.getNext();
        }
        return mArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            a = (T1[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }

        Item currentItem = first;
        int i = 0;
        while (currentItem != null) {
            a[i++] = (T1)currentItem.getElement();
            currentItem = currentItem.getNext();
        }

        if (a.length > size)
            a[size] = null;

        return a;
    }

    @Override
    public boolean add(final T t) {
        Item newItem = new Item<T>(t, last, null);
        if (last != null)
            last.next = newItem;
        last = newItem;
        size++;
        if (size == 1)
            first = newItem;
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        Item currentItem = first;
        while (currentItem != null) {
            if (currentItem.getElement() == o) {
                if (size == 1) {
                    first = null;
                    last = null;
                } else if (currentItem == first) {
                    first = currentItem.next;
                } else if (currentItem == last) {
                    last = currentItem.prev;
                } else {
                    currentItem.getPrev().next = currentItem.getNext();
                    currentItem.getNext().prev = currentItem.getPrev();
                }
                size--;
                return true;
            }
            currentItem = currentItem.getNext();
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final Object item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;

    }

    @Override
    public T remove(final int index) {
        if (index > size())
            throw new NoSuchElementException();
        Item currentItem;
        if (size == 1) {
            currentItem = first;
            first = null;
            last = null;
        } else if (index == 0) {
            currentItem = first;
            first = first.getNext();
        } else if (index == size - 1) {
            currentItem = last;
            last = last.getPrev();
        } else {
            currentItem = getItem(index);
            currentItem.getPrev().next = currentItem.getNext();
            currentItem.getNext().prev = currentItem.getPrev();
        }
        size--;
        return (T)currentItem.getElement();
    }

    // BEGIN (write your solution here)

    // END

    @Override
    public List<T> subList(final int start, final int end) {
        return null;
    }

    @Override
    public ListIterator listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object target) {
        int i = 0;
        Item currentItem = first;
        while (currentItem != null) {
            if (currentItem.getElement() == target) {
                return i;
            }
            currentItem = currentItem.getNext();
            i++;
        }
        return -1;
    }

    @Override
    public void add(final int index, final T element) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();
        Item newItem = new Item<T>(element, last, null);
        if (index == 0) {
            first = newItem;
        } else if (index == size) {
            if (last != null)
                last.next = newItem;
            last = newItem;
        } else {
            Item current = getItem(index);
            current.getPrev().next = newItem;
            newItem.prev = current.getPrev();
            newItem.next = current;
            current.prev = newItem;
        }
        size++;
    }

    @Override
    public boolean addAll(final int index, final Collection elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(final int index, final T element) {
        if (index > size())
            throw new NoSuchElementException();
        int i = 0;
        Item currentItem = first;
        while (i != index) {
            currentItem = currentItem.getNext();
            i++;
        }
        currentItem.element = element;
        return element;
    }

    @Override
    public T get(final int index) {
        if (index > size())
            throw new NoSuchElementException();
        int i = 0;
        Item currentItem = first;
        while (i != index) {
            currentItem = currentItem.getNext();
            i++;
        }
        return (T)currentItem.element;
    }

    // BEGIN (write your solution here)

    // END

    private Item getItem(final int index) {
        Item current;
        if (index < size / 2) {
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }
        return current;
    }


    private class ElementsIterator implements ListIterator<T> {

        private Item<T> current;

        private Item<T> last;

        private int index = 0;

        public ElementsIterator() {
            this(0);
        }

        public ElementsIterator(final int index) {
            current = ListImpl.this.first;
            for (int i = 0; i < index; i++)
                current = current.getNext();

        }

        @Override
        public boolean hasNext() {
            if (ListImpl.this.size() == 0)
                return false;
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            T item = current.getElement();
            last = current;
            current = current.getNext();
            index++;
            return item;
        }

        @Override
        public void add(final T element) {
            ListImpl.this.add(element);
        }

        @Override
        public void set(final T element) {
            if (last == null)
                throw new IllegalStateException();
            last.element = element;
        }

        @Override
        public int previousIndex(){
            return  index - 1;
        }

        @Override
        public int nextIndex() {
            return  index;
        }

        @Override
        public boolean hasPrevious() {
            if (ListImpl.this.size() == 0)
                return false;
            return last != null;

        }

        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            T item = last.getElement();
            last = last.getPrev();
            //current = current.getPrev();
            index--;
            return item;
        }

        @Override
        public void remove() {
            if (ListImpl.this.size == 0 )
                throw new NoSuchElementException();
            /*if (current == null)
                throw new IllegalStateException();*/
            if (last == null)
                throw new IllegalStateException();
            ListImpl.this.remove(last.getElement());
            last = null;
            index--;
        }

    }

    private static class Item<T> {

        private T element;

        private Item<T> next;

        private Item<T> prev;

        public Item(final T element, final Item<T> prev, final Item<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public T getElement() {
            return element;
        }

        public Item<T> getNext() {
            return next;
        }

        public Item<T> getPrev() {
            return prev;
        }

    }

}
