package hw4.task01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository mockBookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testFindBookByIdShouldReturnBookWithCorrectId() {
        // Arrange
        String expectedId = "1";
        Book expectedBook = new Book(expectedId, "SomeTitle", "SomeAuthor");
        when(mockBookRepository.findById(expectedId)).thenReturn(expectedBook);

        // Act
        Book actualBook = bookService.findBookById(expectedId);

        // Assert
        assertEquals(expectedId, actualBook.getId(), "Метод findBookById вернул неправильный ID");
    }

    @Test
    public void testFindAllBooksShouldReturnCorrectNumberOfBooks() {
        // Arrange
        List<Book> expectedBooks = Arrays.asList(
            new Book("1"),
            new Book("2"),
            new Book("3"),
            new Book("4")
        );
        when(mockBookRepository.findAll()).thenReturn(expectedBooks);

        // Act
        List<Book> actualBooks = bookService.findAllBooks();

        // Assert
        assertEquals(expectedBooks.size(), actualBooks.size(), "Метод findAllBooks вернул неправильное количество элементов");
    }

    @Test
    public void testFindBookByIdWithInvalidIdShouldThrowException() {
        // Arrange
        String invalidId = "invalidId";
        when(mockBookRepository.findById(invalidId)).thenReturn(null);

        // Assert
        try {
            bookService.findBookById(invalidId);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid book ID", e.getMessage());
        }
    }
}