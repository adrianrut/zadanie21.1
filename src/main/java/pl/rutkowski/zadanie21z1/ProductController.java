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
            getSum(model, products);
            return "products";
        } else {
            products = productRepository.findByCategory(category);
            getSum(model, products);
            return "category";
        }
    }

    private void getSum(Model model, List<Product> products) {
        model.addAttribute("products", products);
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        model.addAttribute("priceSum", sum);
    }


    @PostMapping("/save")
    public String add(Product product) {
        productRepository.add(product);
        return "redirect:/lista";
    }

    @GetMapping("/dodaj")
    String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

//    @PostMapping("/dodaj")
//    public String add(@RequestParam String name,
//                      @RequestParam double price,
//                      @RequestParam Category category) {
//        Product product = new Product(name, price, category);
//        productRepository.add(product);
//        return "redirect:/lista";
//    }
}
