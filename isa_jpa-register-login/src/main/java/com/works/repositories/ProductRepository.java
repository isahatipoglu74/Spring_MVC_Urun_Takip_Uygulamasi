package com.works.repositories;

import com.works.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

   /* @Query("SELECT p FROM Product p WHERE p.title LIKE %?1%"
            + " OR p.detail LIKE %?1%"
            + " OR CONCAT(p.stock,'')  LIKE %?1%"
            + " OR CONCAT(p.price, '') LIKE %?1%")*/
   @Query("SELECT p FROM Product p WHERE CONCAT(p.title, ' ', p.detail, ' ', p.price, ' ', p.stock) LIKE %?1%")

   public List<Product> search(String keyword);

   List<Product> findByCidEquals(Long cid);



   boolean existsByPidEqualsAndCidEquals(Long pid, Long cid);

   Page<Product> findByCidEqualsAllIgnoreCase(Long cid, Pageable pageable);




}