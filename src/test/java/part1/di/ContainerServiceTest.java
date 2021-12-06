package part1.di;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ContainerServiceTest {

    @Test
    void getObject_BookRepository() throws Exception{
        BookRepository object = ContainerService.getObject(BookRepository.class);
        assertNotNull(object);
    }

    @Test
    void getObject_BOokService() throws Exception{
        BookService bookService = ContainerService.getObject(BookService.class);
        assertNotNull(bookService);
        assertNotNull(bookService.bookRepository);
    }

}