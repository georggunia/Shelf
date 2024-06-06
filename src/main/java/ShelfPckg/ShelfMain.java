package ShelfPckg;


import java.util.Comparator;

import static ShelfPckg.Shelf.transferAndTrim;

public class ShelfMain {
    public static void printShelf(Shelf<? extends ShelfItem> shelf) { // makes function flexible to iterate over any given clas
        for (ShelfItem item : shelf) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        Shelf<Book> bookShelf = new Shelf<>(new Book("Java ist auch eine Insel", "Ullenbloom", 1246), new Book("Schuld", "Schirach", 208), null, new Book("Bibi und Tina", "Börnstädt", 34));
        printShelf(bookShelf);
        Shelf <Tool> toolShelf = new Shelf<>(new Tool("Schraubenzieher"), null, null, new Tool("Saege"));
        printShelf(toolShelf);

        Shelf<Book> newBookShelf = new Shelf<>(null,null,null,null);
        newBookShelf.takeFrom(bookShelf);
        Shelf<Book> generalShelf = new Shelf<>(null,null,null,null);
        generalShelf.takeFrom(newBookShelf);

        Shelf<Book> CompareBookShelf = new Shelf<>(bookShelf.max(Comparator.comparingInt(Book::getPages)),null,null,null);
        printShelf(CompareBookShelf);

        transferAndTrim(bookShelf,newBookShelf);
        printShelf(newBookShelf);
    }


}
