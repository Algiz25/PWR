import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator implements Iterator<Integer> {
    private int firstNum = 1;
    private int secondNum = 1;

    public MyIterator() {
        System.out.println(firstNum);
        System.out.println(secondNum);
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int newNum = firstNum + secondNum;
        firstNum = secondNum;
        secondNum = newNum;
        return newNum;
    }

    public static void main(String[] args) {
        MyIterator iterator = new MyIterator();

        for (int i = 0; i < 15; i++) {
            System.out.println(iterator.next());
        }
    }
}
