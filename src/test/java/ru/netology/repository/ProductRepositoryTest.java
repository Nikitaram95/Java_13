package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.*;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;
import ru.netology.repository.ProductRepository;

public class ProductRepositoryTest {
    Product book1 = new Book(1, "book1", 200, "author1");
    Product smartphone1 = new Smartphone(2, "Phone2", 600, "producer2");

    Product book2 = new Book(3, "book3", 150, "author3");

    @Test
void emptyProductRepository(){
        ProductRepository repository = new ProductRepository();

        Product[] excepted = {};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(excepted,actual);
    }

    @Test
    void save() {
        ProductRepository repository = new ProductRepository();
        repository.save(book1);
        repository.save(smartphone1);
        repository.save(book2);

        Product[] excepted = {book1, smartphone1, book2};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(excepted, actual);
    }

    @Test
    public void removeById() {
        ProductRepository repository = new ProductRepository();
        repository.save(book1);
        repository.save(smartphone1);
        repository.save(book2);

        repository.removeProductById(1);

        Product[] expected = {smartphone1, book2};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findById(){
        ProductRepository repo = new ProductRepository();

        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);


        Product excepted = book1;
        Product actual = repo.findById(1);

        Assertions.assertEquals(excepted,actual);
    }

    @Test

    public void removeByNonExistentId(){

        ProductRepository repo = new ProductRepository();

        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeProductById(25);
        });
    }

    @Test
    public void saveAlreadyAddedItem(){
        ProductRepository repo = new ProductRepository();

        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(book1);
        });
    }

}
