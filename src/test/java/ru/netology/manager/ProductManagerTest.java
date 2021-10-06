package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Product book1 = new Book(1, "English", 1000, "Murfy");
    private Product book2 = new Book(2, "Alphabet", 700, "Petrova");
    private Product book3 = new Book(3, "Cinderella", 500, "Murfy");
    private Product tShirt1 = new TShirt(4, "Clothe1", 1090, "GAP");
    private Product tShirt2 = new TShirt(5, "Clothe2", 1600, "Nike");

    @BeforeEach
    public void manageAdd() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(tShirt1);
        manager.add(tShirt2);
    }

    @Test
    public void shouldSearchByBook() {
        Product[] actual = manager.searchBy("Murfy");
        Product[] expected = new Product[]{book1, book3};

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSearchByTShirt() {
        Product[] actual = manager.searchBy("GAP");
        Product[] expected = new Product[]{tShirt1};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindExistingBookByName() {
        String nameBook = "Cinderella";
        Product[] actual = manager.searchBy(nameBook);
        Product[] expected = new Product[]{book3};

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindExistingTShirtByName() {
        String nameTShirt = "Clothe2";
        Product[] actual = manager.searchBy(nameTShirt);
        Product[] expected = new Product[]{tShirt2};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindExistingTShirtByCreator() {
        String creatorTShirt = "Nike";
        Product[] actual = manager.searchBy(creatorTShirt);
        Product[] expected = new Product[]{tShirt2};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWhenMissingProduct() {
        Product[] actual = manager.searchBy("нет такого продукта");
        Product[] expected = new Product[]{};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByTShortAndBook() {
        Product[] actual = manager.searchBy("Nike");
        Product[] expected = new Product[]{book2, tShirt2};
    }

    @Test
    public void shouldRemoveByIdNoExist() {
        assertThrows(NotFoundException.class, () -> repository.removeById(6));
    }
}
