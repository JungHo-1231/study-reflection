package part3.dynamicProxy;


import org.junit.jupiter.api.Test;
import part2.proxy.Book;
import part2.proxy.BookService;
import part2.proxy.DefaultBookService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class BookServiceTest {

    BookService bookService
            = (BookService) Proxy.newProxyInstance(
            BookService.class.getClassLoader(),
            new Class[]{BookService.class},
            new InvocationHandler() {
                BookService bookService = new DefaultBookService();

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    if (method.getName().equals("rent")) {
                        System.out.println("aaa");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("bbb");
                        return invoke;
                    }
                    return method.invoke(bookService,args);
                }
            }
    );

    @Test
    void di() throws Exception{
        Book book = new Book();
        book.setName("spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }
}


