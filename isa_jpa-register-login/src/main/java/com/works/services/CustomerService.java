package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {

    final CustomerRepository customerRepository;

 //Customer Login İşlemi
 public Customer login(String email, String password) {
     Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCaseAndPasswordEquals(email, password);
     if (optionalCustomer.isPresent()) {
         return optionalCustomer.get();
     }
     return null;
 }

  //Email adresinin database de var olup olmaması kontrolü
    public Customer loginUser(String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(email);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }
        return null;
    }
//Customer Ekleme metodu
    public Customer save(Customer customer) {
        customer.setStatus(true);
        try {
          return customerRepository.save(customer);
        } catch (DataIntegrityViolationException ex) {
            return customer;
        }catch (Exception sql){
            return null;
        }
    }

    //İlgili müşterinin customer id sini bulma
    public Customer single(Long cid){

        return customerRepository.findByCidEquals(cid).get();
    }

}
