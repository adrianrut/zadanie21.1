package pl.rutkowski.zadanie21_1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/lista")
    public String list(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        model.addAttribute("priceSum", sum);
        return "products";
    }

    @GetMapping("/kategoria")
    public String listByCategory(@RequestParam Category category, Model model) {
        List<Product> products = productRepository.findByCategory(category);
        model.addAttribute("products", products);
        return "category";
    }

    @PostMapping("/dodaj")
    public String add(@RequestParam String name,
                      @RequestParam double price,
                      @RequestParam Category category) {
        Product product = new Product(name, price, category);
        productRepository.add(product);
        return "redirect:/lista";
    }
}
