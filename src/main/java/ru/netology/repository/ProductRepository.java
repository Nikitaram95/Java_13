package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;

public class ProductRepository {
    private Product[] items = new Product[0];

    public void save(Product product) {

        Product[] tmp = new Product[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            if(items[i].getId() == product.getId()){
                throw new AlreadyExistsException(
                        "Element with id: "  + product.getId() +  " already added"
                );

            }
            tmp[i] = items[i];
        }
        tmp[tmp.length - 1] = product;
        items = tmp;
    }

    public void removeProductById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException(
                    "Element with id: "  + id +  " not found"
            );

        }
        Product[] tmp = new Product[items.length - 1];
        int copyToIndex = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[copyToIndex] = item;
                copyToIndex++;
            }
        }
        items = tmp;

    }

    public Product findById(int id){
        for(Product product: items){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }


    public Product[] findAll() {
        return items;
    }
}
