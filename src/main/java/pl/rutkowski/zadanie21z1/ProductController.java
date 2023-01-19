package pl.rutkowski.zadanie21z1;

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
    public String list(Model model, @RequestParam(name = "kategoria", required = false) Category category) {
        List<Product> products;
        if (category == null) {
            products = productRepository.findAll();
            String productsList = "Lista produktów";
            model.addAttribute("title", productsList);
        } else {
            products = productRepository.findByCategory(category);
            String productsFromCategory = "Lista produktów wybranej kategorii";
            model.addAttribute("title", productsFromCategory);
        }
        double sum = productRepository.getSum(products);
        model.addAttribute("products", products);
        model.addAttribute("priceSum", sum);
        return "products";
    }


    @PostMapping("/zapisz")
    public String add(Product product) {
        productRepository.add(product);
        return "redirect:/lista";
    }

    @GetMapping("/dodaj")
    String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @GetMapping("/")
    String home() {
        return "index";
    }

}
