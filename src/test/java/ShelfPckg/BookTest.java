package ShelfPckg;

import org.junit.*;

import static org.junit.Assert.*;


public class BookTest {
    @Test
    public void testBook() {
        Book book = new Book("Test Title", "Test Author", 100);
        System.out.println(book);
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
        assertEquals(100, book.getPages());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBookAuthorNull() {
        Book book = new Book("Test Title", null, 100);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testBookAuthorEmpty() {
        Book book = new Book("Test Title", "", 100);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBookTitleNull() {
        Book book = new Book(null, "Test Author", 100);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testBookTitleEmpty() {
        Book book = new Book("", "Test Author", 100);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBookZeroPages() {
        Book book = new Book("Test Title", "Test Author", 0);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testBookNegativePages() {
        Book book = new Book("Test Title", "Test Author", -1);
    }


    @Test
    public void testEdgeCasePage() {
        Book book = new Book("Test Title", "Test Author", 1);
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
        assertEquals(1, book.getPages());
    }

}
