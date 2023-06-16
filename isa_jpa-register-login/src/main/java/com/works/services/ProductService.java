package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ProductService {

    final ProductRepository productRepository;
    final HttpServletRequest req;

    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }


    //Ürün listesi
    public Page<Product> allProduct(int page) {
        Long cid =  (Long) req.getSession().getAttribute("cid");
        Pageable pageable = PageRequest.of(page, 5);
        Page<Product> productPage = productRepository.findByCidEqualsAllIgnoreCase(cid,pageable);
        return productPage;
    }

    //Yeni ürün ekleme
    public Product save(Product product){
        Long cid= (Long) req.getSession().getAttribute("cid");
        product.setCid(cid);
        return productRepository.save(product);
    }

    //Ürün silme metodu
    public boolean productDelete(String stpid) {
        try {
            long pid = Long.parseLong(stpid);
            Long cid= (Long) req.getSession().getAttribute("cid");
            boolean status = productRepository.existsByPidEqualsAndCidEquals(pid,cid);
            if ( status ) {
                productRepository.deleteById(pid);
                return true;
            }
        }catch (Exception ex) { }
        return  false;
    }

    //Ürün kontrolü yapma ( pid ) varmı yokmu?
    public Product getSingleProduct( Long pid ) {
        Optional<Product> optionalProduct = productRepository.findById(pid);
        if ( optionalProduct.isPresent() ){
            return optionalProduct.get();
        }
        return null;
    }

    //Ürün kaydet ve  güncelleme işlemi
    public boolean updateProduct( Product product ) {
        try {
            productRepository.saveAndFlush(product);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }




}
