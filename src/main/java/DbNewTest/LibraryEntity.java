package DbNewTest;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "library", schema = "public", catalog = "dbtest")
public class LibraryEntity {
    private int bookId;
    private String name;
    private String author;
    private int numofpage;

    @Id
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "author", nullable = false, length = 20)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "numofpage", nullable = false)
    public int getNumofpage() {
        return numofpage;
    }

    public void setNumofpage(int numofpage) {
        this.numofpage = numofpage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryEntity that = (LibraryEntity) o;
        return bookId == that.bookId &&
                numofpage == that.numofpage &&
                Objects.equals(name, that.name) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bookId, name, author, numofpage);
    }
}
