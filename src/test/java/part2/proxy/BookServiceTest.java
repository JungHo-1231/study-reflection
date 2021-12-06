package part2.proxy;

import org.junit.jupiter.api.Test;
import part2.proxy.Book;
import part2.proxy.BookProxyService;
import part2.proxy.BookService;
import part2.proxy.DefaultBookService;

public class BookServiceTest {

    BookService bookService = new BookProxyService(new DefaultBookService());

    @Test
    void di() throws Exception{
        Book book = new Book();
        book.setName("spring");
        bookService.rent(book);
    }
}
