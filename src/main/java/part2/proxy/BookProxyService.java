package part2.proxy;

public class BookProxyService implements BookService{

    private BookService bookService;

    public BookProxyService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("aaaaaa");
        bookService.rent(book);
        System.out.println("bbbbbb");

        // rent : book 이름이 아닌 rent: hibernate 를 찍고 싶다면?
        // 리얼 서브젝트를 쓰지말고 System.out.println 에 넣으면 된다.
        // 경우에 따라 위와 것들이 필요할 수 있다. (ex 접근 제어자)
    }

    @Override
    public void returnBook(Book book) {
        // rent 코드와 중복 발생 => 프록시 패턴의 단점
        System.out.println("aaaaaa");
        bookService.returnBook(book);
        System.out.println("bbbbbb");
    }
}
