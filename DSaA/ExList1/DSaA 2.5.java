import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MyIterator implements Iterator<Integer> {
    int number;
    int curr = 1;

    public MyIterator(int n) {
        number = n;
    }

    public boolean isPrime(int n){
        if (n < 2){
            return false;
        }

        for (int i = 2; i < n; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasNext() {
        int temp = curr;
        while (temp <= number){
            if (isPrime(temp)){
                return true;
            }
            temp++;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (curr <= number){
            if (isPrime(curr)){
                return curr++;
            }
            curr++;
        }
        return null;
    }

    public static void main(String[] args) {
        MyIterator iterator = new MyIterator(29);

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
