import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MyIterator<T> implements Iterator<T> {
    private Iterator<T> iterator1;
    private Iterator<T> iterator2;
    int curr = 0;

    public MyIterator(Iterator<T> iterator1, Iterator<T> iterator2) {
        this.iterator1 = iterator1;
        this.iterator2 = iterator2;
    }

    @Override
    public boolean hasNext() {
        if (curr % 2 == 0) {
            return iterator1.hasNext();
        }
        else {
            return iterator2.hasNext();
        }
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (curr % 2 == 0) {
            curr++;
            return iterator1.next();
        }
        else {
            curr++;
            return iterator2.next();
        }

    }

    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numbers2 = Arrays.asList(11,12,13,14,15,16,17,18,19,20);
        MyIterator<Integer> iterator = new MyIterator<>(numbers1.iterator(), numbers2.iterator());

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " "); // Output: 3 6 9
        }
    }
}
