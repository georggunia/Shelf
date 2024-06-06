package ShelfPckg;

public class Book extends ShelfItem{
    private final String title;
    private final String author;
    private final int pages;
    public Book(String title, String author, int pages){
        if (title == null || title.trim().isEmpty()|| author == null || author.trim().isEmpty() || pages < 1){
            throw new IllegalArgumentException("Error: Invalid book data");
        }
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public int getPages(){
        return pages;
    }

    @Override
    public String toString(){
        return "Title: " + title + "\nAuthor: " + author + "\nPages: " + pages;
    }
}
