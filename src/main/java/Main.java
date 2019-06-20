import dao.book.BookDaoImplement;
import entity.Book;
import org.pmw.tinylog.Logger;
import service.BookService;
import service.BookServiceImplement;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookServiceImplement(new BookDaoImplement());

        List<Book> books = bookService.findByTitle("Kaznodzieja");
        if(books != null && books.isEmpty() == false) {
            Book book = books.get(0);
            book.setTitle("Kaznodzieja 2");
            boolean update = bookService.update(book);
            Logger.info(update);
        }

         /*  BookDao bookDao = new BookDaoImplement();
        List<Book> books = bookDao.findAll();
        Logger.info(books);
        List<Book> booksByTitle = bookDao.findByTitle("Kaznodzieja");
        Logger.info(booksByTitle);
        List<String> titles = bookDao.findBookWitchGivenNumberRange(200,400);
        Logger.info(titles);
        Book book = new Book();
        book.setTitle("Instukcja Ikea");
        book.setPagesNumber(20);
        book.setIsbn(111111111111L);
        book.setPublisherId(1L);
        book.setCategoryId(1L);
        bookDao.delete(41L);*/

        //bookDao.insert(book);

    }
}
