package ShelfPckg;

import org.junit.*;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static ShelfPckg.Shelf.transferAndTrim;
import static org.junit.Assert.*;


public class ShelfTest {
    Tool screwdriver = new Tool("screwdriver");
    Book HarryPotter = new Book("Harry Potter", "J.K. Rowling", 200);
    Book ArtOfWar = new Book("Art of War", "KA", 200);
    Tool Scissors = new Tool("Scissors");

    @Test
    public void testShelfConstructor() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(screwdriver, HarryPotter, ArtOfWar, Scissors);
        assertEquals(screwdriver, testShelf.getUpperLeft());
        assertEquals(HarryPotter, testShelf.getUpperRight());
        assertEquals(ArtOfWar, testShelf.getLowerLeft());
        assertEquals(Scissors, testShelf.getLowerRight());
    }

    @Test
    public void testShelfULNull() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(null, HarryPotter, ArtOfWar, Scissors);
        assertNull(testShelf.getUpperLeft());
        assertEquals(HarryPotter, testShelf.getUpperRight());
        assertEquals(ArtOfWar, testShelf.getLowerLeft());
        assertEquals(Scissors, testShelf.getLowerRight());
    }

    @Test
    public void testShelfURNull() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(screwdriver, null, ArtOfWar, Scissors);
        assertEquals(screwdriver, testShelf.getUpperLeft());
        assertNull(testShelf.getUpperRight());
        assertEquals(ArtOfWar, testShelf.getLowerLeft());
        assertEquals(Scissors, testShelf.getLowerRight());
    }

    @Test
    public void testShelfLLNull() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(screwdriver, HarryPotter, null, Scissors);
        assertEquals(screwdriver, testShelf.getUpperLeft());
        assertEquals(HarryPotter, testShelf.getUpperRight());
        assertNull(testShelf.getLowerLeft());
        assertEquals(Scissors, testShelf.getLowerRight());
    }

    @Test
    public void testShelfLRNull() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(screwdriver, HarryPotter, ArtOfWar, null);
        assertEquals(screwdriver, testShelf.getUpperLeft());
        assertEquals(HarryPotter, testShelf.getUpperRight());
        assertEquals(ArtOfWar, testShelf.getLowerLeft());
        assertNull(testShelf.getLowerRight());
    }

    @Test
    public void testShelfAllNull() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(null, null, null, null);
        assertNull(testShelf.getUpperLeft());
        assertNull(testShelf.getUpperRight());
        assertNull(testShelf.getLowerLeft());
        assertNull(testShelf.getLowerRight());
    }

    @Test
    public void testGet() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(screwdriver, HarryPotter, ArtOfWar, Scissors);
        assertEquals(screwdriver, testShelf.get(0));
        assertEquals(HarryPotter, testShelf.get(1));
        assertEquals(ArtOfWar, testShelf.get(2));
        assertEquals(Scissors, testShelf.get(3));
    }

    @Test
    public void testGetNSEE() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(screwdriver, HarryPotter, ArtOfWar, Scissors);
        try {
            testShelf.get(4);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(screwdriver, testShelf.get(0));
            assertEquals(HarryPotter, testShelf.get(1));
            assertEquals(ArtOfWar, testShelf.get(2));
            assertEquals(Scissors, testShelf.get(3));
        }
    }

    @Test
    public void testSet() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(null, null, null, null);
        testShelf.set(0, Scissors);
        testShelf.set(1, HarryPotter);
        testShelf.set(2, ArtOfWar);
        testShelf.set(3, Scissors);
        assertEquals(Scissors, testShelf.get(0));
        assertEquals(HarryPotter, testShelf.get(1));
        assertEquals(ArtOfWar, testShelf.get(2));
        assertEquals(Scissors, testShelf.get(3));
    }

    @Test
    public void testSetNSEE() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(screwdriver, null, null, null);
        try {
            testShelf.set(4, Scissors);
            fail();
        } catch (IndexOutOfBoundsException e) {
            testShelf.set(0, Scissors);
            testShelf.set(1, HarryPotter);
            assertEquals(Scissors, testShelf.get(0));
            assertEquals(HarryPotter, testShelf.get(1));
            assertNull(testShelf.get(2));
            assertNull(testShelf.get(3));
        }

    }

    @Test
    public void testIteratorNext() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(screwdriver, HarryPotter, ArtOfWar, Scissors);
        Iterator<ShelfItem> iterator = testShelf.iterator();
        assertEquals(screwdriver, iterator.next());
        assertEquals(HarryPotter, iterator.next());
        assertEquals(ArtOfWar, iterator.next());
        assertEquals(Scissors, iterator.next());
    }

    @Test
    public void testIteratorHasNext() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(screwdriver, HarryPotter, ArtOfWar, Scissors);
        Iterator<ShelfItem> iterator = testShelf.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNextNoSuchElements() {
        Shelf<ShelfItem> testShelf = new Shelf<ShelfItem>(screwdriver, HarryPotter, ArtOfWar, Scissors);
        Iterator<ShelfItem> iterator = testShelf.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        try {
            iterator.next();
            fail();
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testTakeFrom() {
        Shelf<ShelfItem> other = new Shelf<>(screwdriver, HarryPotter, ArtOfWar, Scissors);
        Shelf<ShelfItem> testShelf = new Shelf<>(null,null,null,null);
        testShelf.takeFrom(other);
        assertEquals(screwdriver, testShelf.get(0));
        assertEquals(HarryPotter, testShelf.get(1));
        assertEquals(ArtOfWar, testShelf.get(2));
        assertEquals(Scissors, testShelf.get(3));
    }
    @Test
    public void testTakeFromIAE (){
        Shelf<ShelfItem> other = null;
        Shelf<ShelfItem> testShelf = new Shelf<>(screwdriver, HarryPotter, ArtOfWar, Scissors);
        try {
            testShelf.takeFrom(other);
        } catch (IllegalArgumentException e) {
            assertEquals(screwdriver, testShelf.get(0));
            assertEquals(HarryPotter, testShelf.get(1));
            assertEquals(ArtOfWar, testShelf.get(2));
            assertEquals(Scissors, testShelf.get(3));
        }
    }
    @Test
    public void testTakeFromNull (){
        Shelf<ShelfItem> other = new Shelf<>(null,null,null,null);
        Shelf<ShelfItem> testShelf = new Shelf<>(screwdriver, HarryPotter, ArtOfWar, Scissors);
        try {
            testShelf.takeFrom(other);
        } catch (IllegalArgumentException e) {
            assertNull(testShelf.get(0));
            assertNull(testShelf.get(1));
            assertNull(testShelf.get(2));
            assertNull(testShelf.get(3));
        }
    }

    @Test
    public void testMax() {
        // Create a Comparator for books that compares them based on the number of pages
        Comparator<Book> comparator = Comparator.comparingInt(Book::getPages); //lernen !!

        // Create some books
        Book book1 = new Book("Book 1","a" ,100);
        Book book2 = new Book("Book 2","o" ,200);
        Book book3 = new Book("Book 3","k" ,150);
        Book book4 = new Book("Book 4", "y",50);

        // Create a ShelfPckg with the books
        Shelf<Book> shelf = new Shelf<>(book1, book2, book3, book4);

        // Call the max method with the comparator
        Book maxBook = shelf.max(comparator);

        // Check if the max method returned the book with the most pages
        assertEquals(book2, maxBook);
    }

    @Test (expected = NoSuchElementException.class)
    public void testMaxNoSuchElementException() {
        // Create a Comparator for books that compares them based on the number of pages
        Comparator<Book> comparator = Comparator.comparingInt(Book::getPages);

        // Create a ShelfPckg with the books
        Shelf<Book> shelf = new Shelf<>(null, null, null, null);

        // Call the max method with the comparator (throws Exception)
        Book maxBook = shelf.max(comparator);
    }

    @Test (expected = NoSuchElementException.class)
    public void testMaxEquals() {
        // Create a Comparator for books that compares them based on the number of pages
        Comparator<Book> comparator = Comparator.comparingInt(Book::getPages);

        // Create some books
        Book book1 = new Book("Book 1","a" ,100);
        Book book2 = new Book("Book 2","o" ,100);
        Book book3 = new Book("Book 3","k" ,100);
        Book book4 = new Book("Book 4", "y",100);

        // Create a ShelfPckg with the books
        Shelf<Book> shelf = new Shelf<>(book1, book2, book3, book4);

        // Call the max method with the comparator
        Book maxBook = shelf.max(comparator); // throws IllegalArgumentException

    }

    @Test
    public void testTransferAndTrim() {
        Shelf<ShelfItem> from = new Shelf<ShelfItem>(screwdriver, null, HarryPotter, ArtOfWar);
        Shelf<ShelfItem> to = new Shelf<ShelfItem>(null, screwdriver, HarryPotter, ArtOfWar);

        transferAndTrim(from,to);

        assertEquals(screwdriver, to.get(0));
        assertEquals(HarryPotter, to.get(1));
        assertEquals(ArtOfWar, to.get(2));
        assertNull(to.get(3));
    }
}
