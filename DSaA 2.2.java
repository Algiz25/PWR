import java.util.Iterator;

public class MyIterator implements Iterator<Integer> {
    private int curr;

    public MyIterator(int start) {
        curr = start;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        //will always be true
        if (!hasNext()) { 
            throw new NoSuchElementException();
        }
        return curr++;
    }

    public static void main(String[] args) {
        MyIterator iterator = new MyIterator(5);

        for (int i = 0; i < 2; i++) {
            System.out.println(iterator.next());
        }
    }
}
