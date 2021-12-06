package part2.proxy;


public interface BookService {
    void rent(Book book);

    void returnBook(Book book);
}
