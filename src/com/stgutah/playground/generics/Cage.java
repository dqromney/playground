package com.stgutah.playground.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Cage collection of element types.
 *
 * User: dqromney
 * Date: Feb 2, 2011
 * Time: 11:59:20 PM
 */
public class Cage<E extends Animal> implements Collection<E> {

    List<E> cageList = new ArrayList<E>(0);

    public boolean add(E e) {
        return cageList.add(e);
    }

    public int size() {
        return cageList.size();
    }

    public boolean isEmpty() {
        return cageList.isEmpty();
    }

    public boolean contains(Object o) {
        return cageList.contains(o);
    }

    public Iterator<E> iterator() {
        return cageList.iterator();
    }

    public Object[] toArray() {
        return cageList.toArray();
    }

    public <T> T[] toArray(T[] ts) {
        return cageList.toArray(ts);
    }

    public boolean remove(Object o) {
        return cageList.remove(o);
    }

    public boolean containsAll(Collection<?> objects) {
        return cageList.containsAll(objects);
    }

    public boolean addAll(Collection<? extends E> es) {
        return cageList.addAll(es);
    }

    public boolean removeAll(Collection<?> objects) {
        return cageList.removeAll(objects);
    }

    public boolean retainAll(Collection<?> objects) {
        return cageList.retainAll(objects);
    }

    public void clear() {
        cageList.clear();
    }

    public boolean equals(Object o) {
        return cageList.equals(o);
    }

    public int hashCode() {
        return cageList.hashCode();
    }

}
