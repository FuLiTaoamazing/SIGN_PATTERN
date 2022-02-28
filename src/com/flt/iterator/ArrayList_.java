package com.flt.iterator;

public class ArrayList_ implements Collection_ {
    private Object[] objects = new Object[10];
    private int index = 0;

    @Override
    public void add(Object o) {
        if (index == objects.length) {
            Object[] newObjects = new Object[objects.length * 2];
            System.arraycopy(objects, 0, newObjects, 0, objects.length);
            objects = newObjects;
        }
        objects[index] = o;
        index++;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public Iterator_ iterator() {
        return new ArrayListIterator_();
    }

    private class ArrayListIterator_ implements Iterator_ {
        private int currIndex = 0;

        @Override
        public boolean hasNext() {
            return currIndex < index;
        }

        @Override
        public Object next() {
            Object o = objects[currIndex];
            currIndex++;
            return o;
        }
    }
}
