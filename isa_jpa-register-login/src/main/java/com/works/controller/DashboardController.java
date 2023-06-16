package com.works.controller;

import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    final ProductService productService;
    private Long updateID = 0l;
    @Autowired
    private ProductService service;

    @GetMapping("/dashboard")
    public String dashboard(Model model ,@Param("keyword") String keyword,@RequestParam(defaultValue = "0") int page){
        List<Product> listProducts = service.listAll(keyword);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("keyword", keyword);
        model.addAttribute("products",productService.allProduct(page));
        updateID = 0l;
        return "dashboard";
    }


    @GetMapping("/dashboard/{pid}")
    public String dashboardUpdate(Model model, @PathVariable Long pid, @RequestParam(defaultValue = "0") int page) {
        updateID = pid;
        model.addAttribute("products", productService.allProduct(page));
        model.addAttribute("product", productService.getSingleProduct(pid));
        return "dashboardUpdate";
    }

    @PostMapping("/productUpdate")
    public String productUpdate( Product product ) {
        product.setPid(updateID);
        productService.updateProduct(product);
        return "redirect:/dashboard";
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product){
        productService.save(product);
       return "redirect:/dashboard";
    }

    @GetMapping("/productDelete/{stpid}")
    public String productDelete(@PathVariable String stpid){
       boolean status= productService.productDelete(stpid);
        System.out.println("Status : "+status);
        return "redirect:/dashboard";
    }


}
