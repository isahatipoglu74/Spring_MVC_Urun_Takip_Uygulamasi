package com.works.repositories;

import com.works.entities.Customer;
import com.works.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {



    Optional<Manager> findByEmailEqualsIgnoreCase(String email);

    Optional<Manager> findByMidEquals(Long mid);
}