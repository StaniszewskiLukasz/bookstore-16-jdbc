package dao.book;

import dao.AbstractDao;
import entity.Book;

import java.util.List;

public interface BookDao extends AbstractDao<Book> {
    List findByTitle(String title);
    List<String> findBookWitchGivenNumberRange(int min,int max);


}
