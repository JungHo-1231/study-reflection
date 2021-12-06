package part4.DynamicProxyWithoutInterface;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Test;
import part3.DynamicProxyWithoutInterface.Book;
import part3.DynamicProxyWithoutInterface.BookService;

import java.lang.reflect.Method;

public class BookTestService {

    @Test
    void di() throws Exception {
        /* 인터페이스가 없는 경우 cglib 라이브러리를 사용하면 구현이 가능  */
        MethodInterceptor handler = new MethodInterceptor() {
            BookService bookService = new BookService();
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//                System.out.println("ccc");
//                Object invoke = method.invoke(bookService, args);
//                System.out.println("ddd");
//                return invoke;

                if (method.getName().equals("rent")) {
                    System.out.println("before rent some common logic");
                    Object invoke = method.invoke(bookService, args);
                    System.out.println("after rent some common logic");
                    return invoke;
                }

                return method.invoke(bookService, args);
            }
        };

        BookService bookService = (BookService) Enhancer.create(BookService.class, handler);
        Book book = new Book();
        book.setName("spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }

}
