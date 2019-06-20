package dao.book;

import entity.Book;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookResultSet {
    public static List<Book> mapToList(ResultSet resultSet) throws SQLException {
        List<Book> books = new ArrayList<>();

        while (resultSet.next()) {
            Book book = new Book();
            if (isResultSetHasColumn(resultSet, "id")) {
                book.setId(resultSet.getLong("id"));
            }

            if (isResultSetHasColumn(resultSet, "category_id")) {
                book.setCategoryId(resultSet.getLong("category_id"));
            }

            if (isResultSetHasColumn(resultSet, "isbn")) {
                book.setIsbn(resultSet.getLong("isbn"));
            }

            if (isResultSetHasColumn(resultSet, "pages_number")) {
                book.setPagesNumber(resultSet.getInt("pages_number"));
            }

            if (isResultSetHasColumn(resultSet, "publisher_id")) {
                book.setPublisherId(resultSet.getLong("publisher_id"));
            }

            if(isResultSetHasColumn(resultSet,"title")){
                book.setTitle(resultSet.getString("title"));
            }
            books.add(book);
        }
return books;
}

    private static boolean isResultSetHasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            if (metaData.getColumnName(i).equals(columnName)) {
                return true;
            }
        }
        return false;
    }

}
