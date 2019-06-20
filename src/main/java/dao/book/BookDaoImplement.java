package dao.book;

import connection.BookstoreConnectionManager;
import entity.Book;
import org.pmw.tinylog.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookDaoImplement implements BookDao {
    /* rozwiązanie dla Statment nieużywanego bo niebezpiecznego
    public List findByTitle(String title) {
        String sql = "select * from books where title = '" + title + "'";

        try(Connection connection = BookstoreConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            List books = new ArrayList<>();
            while ( resultSet.next()){
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                book.setIsbn(resultSet.getLong("isbn"));
                book.setPagesNumber(resultSet.getInt("pages_number"));
                book.setCategoryId(resultSet.getLong("category_id"));
                book.setPublisherId(resultSet.getLong("publisher_id"));
                books.add(book);

            }
            return books;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }*/

    @Override
    public List findByTitle(String title) {
        String sql = "select * from books where title = ? ";//to jest zapytanie sql z czego zdaje sobie sprawę Intelij
        //w formie Stringa

        try(Connection connection = BookstoreConnectionManager.getConnection(); //ustanawiamy połączenie
            PreparedStatement ps = connection.prepareStatement(sql)) { //w połaczeniu przekazujemy sql-a poprzez "oświadczenie"

            ps.setString(1,title); // w oświadczeniu używamy metody String bo mamy szukać po tytule, oświadczeniem w naszym sql
            //jest znak ?. jego podmienimy na preparedStatment
            //1 oznacza ilośc parametrów jakie przekazujemy, gdyby były dwa byłaby 2

            try(ResultSet resultSet = ps.executeQuery()){ // tutaj otrzymujemy wynik w postaci książki
                List books = new ArrayList<>();
                while(resultSet.next()){ // wartości książki pobieramy i ustawiamy setami
                    Book book = new Book();
                    book.setId(resultSet.getLong("id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setIsbn(resultSet.getLong("isbn"));
                    book.setPagesNumber(resultSet.getInt("pages_number"));
                    book.setCategoryId(resultSet.getLong("category_id"));
                    book.setPublisherId(resultSet.getLong("publisher_id"));
                    books.add(book); // gotową książkę wrzucamy do listy
                }
                return books;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<String> findBookWitchGivenNumberRange(int min, int max) {
//        String sql = "select title from book where pages_number between ? and ?"; moja wersja
        String sql = "SELECT title FROM books WHERE pages_number > ? and pages_number < ?";

        try (Connection connection = BookstoreConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, min);
            ps.setInt(2, max);

            try (ResultSet resultSet = ps.executeQuery()) {
                List<String> titles = new ArrayList<>();
                while (resultSet.next()) {
                    titles.add(resultSet.getString("title"));
                }
                return titles;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List findAll() {
        String sql = "select * from books";

        try(Connection connection = BookstoreConnectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery()) {
            List<Book> booksList = BookResultSet.mapToList(resultSet);

            return booksList;
        }catch (SQLException e){
            Logger.error(e);
        }
        return Collections.emptyList();
    }

    public Book findById(long id) {
        String sql = "select * from books where id = ?";

        try(Connection connection = BookstoreConnectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setLong(1,id);

            try(ResultSet resultSet = ps.executeQuery()) {
                Book book = new Book();
                while (resultSet.next()) {
                    book.setId(resultSet.getLong("id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setIsbn(resultSet.getLong("isbn"));
                    book.setPagesNumber(resultSet.getInt("pages_number"));
                    book.setCategoryId(resultSet.getLong("category_id"));
                    book.setPublisherId(resultSet.getLong("publisher_id"));
                }
                return book;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public int insert(Book object) {
       String sql = "insert into books(title,pages_number,isbn,category_id,publisher_id) "
               + "values(?,?,?,?,?)";
       try(Connection connection = BookstoreConnectionManager.getConnection();
       PreparedStatement ps = connection.prepareStatement(sql)){

           ps.setString(1,object.getTitle());
           ps.setInt(2,object.getPagesNumber());
           ps.setLong(3,object.getIsbn());
           ps.setLong(4,object.getCategoryId());
           ps.setLong(5,object.getPublisherId());

           int i = ps.executeUpdate();
           Logger.info(i + " rows has been inserted");
           return i;
       }catch (SQLException e){
           Logger.error(e);
       }
       return 0;
    }

    public int update(Book object) {
       String sql = "update books set title = ?,pages_number = ?,isbn = ?,category_id = ?,publisher_id = ?";

       try(Connection connection = BookstoreConnectionManager.getConnection();
       PreparedStatement ps = connection.prepareStatement(sql)){

           ps.setString(1,object.getTitle());
           ps.setInt(2,object.getPagesNumber());
           ps.setLong(3,object.getIsbn());
           ps.setLong(4,object.getCategoryId());
           ps.setLong(5,object.getPublisherId());

           return ps.executeUpdate();
       }catch (SQLException e){
           Logger.error(e);
       }
       return 0;
    }

    public int delete(long id) {
        String sql = " delete from books where id = ?";

        try(Connection connection = BookstoreConnectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            ps.setLong(1, id);

            int i = ps.executeUpdate();
            //sql
            connection.commit();
            Logger.info("book with id: " + id + " has been deleted");
            return i;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return 0;
    }
}
