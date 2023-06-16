package com.works.services;

import com.works.entities.Customer;
import com.works.entities.Manager;
import com.works.entities.Product;
import com.works.repositories.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManagerService {

    final ManagerRepository managerRepository;

    public List<Manager> managerList(){
      return managerRepository.findAll();

    }
    //Manager Ekleme metodu
    public Manager save(Manager manager) {
        manager.setStatus(true);
        try {
            return managerRepository.save(manager);
        } catch (Exception sql) {
            return null;
        }
    }
    //Mnager silme metodu
    public boolean managerDelete(String stmid) {
        try {
            long mid=Long.parseLong(stmid);
            boolean status=managerRepository.existsById(mid);
            if(status){
                managerRepository.deleteById(mid);
                return true;
            }else {
                return false;
            }
        }catch (Exception exception){
            System.err.println("Delete Error"+exception);
            return false;
        }
    }
    //Ürün kontrolü yapma ( pid ) varmı yokmu?
    public Manager getSingleManager(Long mid ) {
        Optional<Manager> optionalManager = managerRepository.findById(mid);
        if ( optionalManager.isPresent() ){
            return optionalManager.get();
        }
        return null;
    }

    //Ürün kaydet ve  güncelleme işlemi
    public boolean updateManager( Manager manager ) {
        try {
            managerRepository.saveAndFlush(manager);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }
}
