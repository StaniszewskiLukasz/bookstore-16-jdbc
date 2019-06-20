package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private long id;
    private String title;
    private int pagesNumber;
    private long categoryId;
    private long publisherId;
    private long isbn;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pagesNumber=" + pagesNumber +
                ", categoryId=" + categoryId +
                ", publisherId=" + publisherId +
                ", isbn=" + isbn +
                '}' + "\n";
    }
}
