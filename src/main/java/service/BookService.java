package service;

import entity.Book;

import java.util.List;

public interface BookService {

    boolean delete (Long id);
    boolean insert(Book book);
    boolean update(Book book);
    List findByTitle(String title);
    List<Book> findAll();
    List<String> findBookWitchGivenNumberRange(int min, int max);
}
