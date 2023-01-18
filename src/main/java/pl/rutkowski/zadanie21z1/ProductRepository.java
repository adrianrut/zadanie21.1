package pl.rutkowski.zadanie21z1;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private final List<Product> productList;

    public ProductRepository() {
        productList = new ArrayList<>();
        productList.add(new Product("Mleko", 4.40, Category.GROCERIES));
        productList.add(new Product("Czekolada", 4, Category.GROCERIES));
        productList.add(new Product("Pralka", 1000, Category.AGD));
        productList.add(new Product("Suszarka", 100, Category.AGD));
        productList.add(new Product("Wihajster", 50, Category.OTHER));
        productList.add(new Product("Kamfora", 10, Category.OTHER));
    }

    public List<Product> findByCategory(Category category) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory() == category) {
                result.add(product);
            }
        }
        return result;
    }

    public double getSum(List<Product> products) {
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;

    }

    public List<Product> findAll() {
        return productList;
    }

    public void add(Product product) {
        productList.add(product);
    }
}
