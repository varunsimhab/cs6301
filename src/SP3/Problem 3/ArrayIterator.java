/*
 * Created by
 * Group 50
 *
 * Varun Simha Balaraju
 * Venkata Sarath Chandra Prasad Nelapati
 * Jithin Paul
 * Sunit Mathew
 * on 09/16/2017.
 */

package cs6301.g50;
import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {
    T[] arr;
    int startIndex, endIndex, cursor;

    public ArrayIterator(T[] a) {
        arr = a;
        startIndex = 0;
        endIndex = a.length-1;
        cursor = -1;
    }

    public ArrayIterator(T[] a, int start, int end) {
        arr = a;
        startIndex = start;
        endIndex = end;
        cursor = start - 1;
    }

    public boolean hasNext() {
        return cursor < endIndex;
    }

    public T next() {
        return arr[++cursor];
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

