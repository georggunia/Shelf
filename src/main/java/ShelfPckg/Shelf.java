package ShelfPckg;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

//Type T as a placeholder for a class under Shelf
public class Shelf <T extends ShelfItem> implements Iterable<T> {
    private T upperLeft; //T is a variable type used for iterating
    private T upperRight;
    private T lowerLeft;
    private T lowerRight;

    public Shelf (T upperLeft, T upperRight, T lowerLeft, T lowerRight){
        this.upperLeft = upperLeft;
        this.upperRight = upperRight;
        this.lowerLeft = lowerLeft;
        this.lowerRight = lowerRight;
    }

    public T getUpperLeft() {
        return upperLeft;
    }
    public T getUpperRight() {
        return upperRight;
    }
    public T getLowerLeft() {
        return lowerLeft;
    }
    public T getLowerRight() {
        return lowerRight;
    }

    public void setUpperLeft(T upperLeft) {
        this.upperLeft = upperLeft;
    }
    public void setUpperRight(T upperRight) {
        this.upperRight = upperRight;
    }
    public void setLowerLeft(T lowerLeft) {
        this.lowerLeft = lowerLeft;
    }
    public void setLowerRight(T lowerRight) {
        this.lowerRight = lowerRight;
    }

    public T get(int index) {
       if (index < 0 || index > 3) {
           throw new IndexOutOfBoundsException();
       }
        return switch (index) {
            case 0 -> upperLeft;
            case 1 -> upperRight;
            case 2 -> lowerLeft;
            case 3 -> lowerRight;
            default -> null;
        };
    }

    public void set(int index, T item) {
        if (index < 0 || index > 3) {
            throw new IndexOutOfBoundsException();
        }
        switch (index) {
            case 0 -> upperLeft = item;
            case 1 -> upperRight = item;
            case 2 -> lowerLeft = item;
            case 3 -> lowerRight = item;
        }
    }

    //Iteriert über den Typen T in ShelfPckg
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < 4;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(index++);
            }
        };
    }

    //takes from a Shelf which is of any type T or below
    public void takeFrom(Shelf <? extends T> other) { // T because the functions refer to T ( T upperRight etc)
        if (other == null)
            throw new IllegalArgumentException("other is null");
        int index = 0;
        for (T item : other) {
            if (index > 3)
                break;
            set(index, item);
            index++;
        }
    }
//Uses a comperator that can record any type T or above
    public T max(Comparator<? super T> comparator) {
        if (upperLeft == null && upperRight == null && lowerLeft == null && lowerRight == null) {
            throw new NoSuchElementException();
        }

        T maxItem = upperLeft;

        if (comparator.compare(maxItem, upperRight) == 0 && comparator.compare(maxItem, lowerLeft) == 0 && comparator.compare(maxItem, lowerRight) == 0)
            throw new NoSuchElementException();

        if (upperRight != null && comparator.compare(maxItem, upperRight) < 0) {
            maxItem = upperRight;
        }

        if (lowerLeft != null && comparator.compare(maxItem, lowerLeft) < 0) {
            maxItem = lowerLeft;
        }

        if (lowerRight != null && comparator.compare(maxItem, lowerRight) < 0) {
            maxItem = lowerRight;
        }

        return maxItem;
    }

    //Uses T or subclass as type parameter of the method
    public static <T extends ShelfItem> void transferAndTrim(Shelf<T> from, Shelf<T> to) {
        if (from == null)
            throw new IllegalArgumentException("from is null");
        if (to == null)
            throw new IllegalArgumentException("to is null");

        int index = 0;
        for (T item : to) {
            to.set(index++, null);
        }

        index = 0;
        for (T item : from) {
            if (item == null)
                continue;
            to.set(index++, item);
        }
    }

}