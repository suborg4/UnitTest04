package hw4.task01;

import java.util.List;

public interface BookRepository {
    Book findById(String id);
    List<Book> findAll();
}
