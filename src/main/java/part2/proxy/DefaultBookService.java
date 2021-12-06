package part2.proxy;

public class DefaultBookService implements  BookService {

    @Override
    public void rent(Book book) {
        System.out.println("runt book= " + book.getName());
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("return book = " + book.getName());
    }
}
