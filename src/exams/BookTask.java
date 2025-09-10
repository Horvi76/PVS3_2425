package exams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookTask {
    static class Book{
        private int bookId;
        private String title;
        private String author;
        private List<Chapter> chapters = new ArrayList<>();

        public Book(int bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
        }

        public int getBookId() {
            return bookId;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public List<Chapter> getChapters() {
            return chapters;
        }

        public int getTotalPages() {
            return chapters.stream()
                    .mapToInt(Chapter::getPages)
                    .sum();
        }
    }

    static class Chapter {
        private int bookID;
        private String title;
        private int pages;

        public Chapter(int bookID, String title, int pages) {
            this.bookID = bookID;
            this.title = title;
            this.pages = pages;
        }

        public int getBookID() {
            return bookID;
        }

        public void setBookID(int bookID) {
            this.bookID = bookID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }
    }


    static void getAuthorPages(List<Book> books, String name){
       int total = books.stream()
                .filter(book -> book.getAuthor().equals(name))
                .mapToInt(Book::getTotalPages)
                .sum();
        System.out.println("Autor " + name + " napsal celkem " + total + " stran");
    }

    public static void main(String[] args) throws IOException {
        //parsovani
        List<Book> books = Files.lines(Paths.get("data\\books.csv"))
                .skip(1)
                .map(line -> line.split(",", 3))
                .map(parsed -> new Book(
                        Integer.parseInt(parsed[0]),
                        parsed[1],
                        parsed[2]
                )).toList();

        

        List<Chapter> chapters = Files.lines(Paths.get("data\\chapters.csv"))
                .skip(1)
                .map(line -> line.split(",", 4))
                .map(split -> new Chapter(
                        Integer.parseInt(split[1]),
                        split[2],
                        Integer.parseInt(split[3])
                )).toList();

//        System.out.println(chapters.size());
//        System.out.println(books.size());

        Map<Integer, Book> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getBookId, b -> b));

        chapters.forEach(chapter -> {
            Book book = bookMap.get(chapter.getBookID());
            if (book != null){
                book.getChapters().add(chapter);
            }
        });

        books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()))
                .forEach((author, count) -> System.out.println(author +  ": " + count + " books"));

        books.stream()
                .sorted(Comparator.comparingInt(Book::getTotalPages).reversed())
                .limit(5)
                .forEach(b -> System.out.println(b.getTitle() + " " + b.getTotalPages()));
    }
}
