package com.flt.iterator;

public class Main {
    public static void main(String[] args) {
        Collection_ collection = new LinkedList_();
        collection.add("1");
        collection.add("2");
        collection.add("3");
        Iterator_ iterator = collection.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }
    }
}
