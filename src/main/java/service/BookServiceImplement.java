package service;

import dao.book.BookDao;
import entity.Book;

import java.util.Collections;
import java.util.List;

public class BookServiceImplement implements BookService {

    private BookDao bookDao;

    public BookServiceImplement(BookDao bookDao){
        this.bookDao=bookDao;
    }


    @Override
    public boolean delete(Long id) {
        if(id != null) {
            int result = bookDao.delete(id);
            if(result>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insert(Book book) {
        if(book!=null && book.getTitle()!=null){
            int result = bookDao.insert(book);
            if(result>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Book book) {
        if(book!=null&& book.getTitle()!=null){
            int result = bookDao.update(book);
            if(result>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Book> findByTitle(String title) {
        if(title!=null){
            return bookDao.findByTitle(title);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public List<String> findBookWitchGivenNumberRange(int min, int max) {
        return bookDao.findBookWitchGivenNumberRange(min,max);
    }
}
