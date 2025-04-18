import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator<T> implements Iterator<T> {
    private final Iterator<T> baseIterator;
    private final int k;
    private int count;

    public MyIterator(Iterator<T> baseIterator, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be greater than 0");
        }
        this.baseIterator = baseIterator;
        this.k = k;
        this.count = 0;
    }

    @Override
    public boolean hasNext() {
        while (baseIterator.hasNext() && count % k != k - 1) {
            baseIterator.next();
            count++;
        }
        return baseIterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        count++;
        return baseIterator.next();
    }
}

// main starts here

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        MyIterator<Integer> iterator = new MyIterator<>(numbers.iterator(), 5);

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " "); // Output: 3 6 9
        }
    }
}

