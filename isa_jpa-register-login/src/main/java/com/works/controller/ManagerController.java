package com.works.controller;

import com.works.entities.Manager;
import com.works.entities.Product;
import com.works.services.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ManagerController {

final ManagerService managerService;
    private Long updateMID = 0l;

    @GetMapping("/manager")
    public String managerList(Model model){
        model.addAttribute("manager",managerService.managerList());
        updateMID = 0l;
        return "manager";
    }
    @GetMapping("/manager/{mid}")
    public String managerUpdate(Model model, @PathVariable Long mid) {
        updateMID = mid;
        model.addAttribute("products", managerService.managerList());
        model.addAttribute("product", managerService.getSingleManager(mid));
        return "ManagerUpdate";
    }

    @PostMapping("/addManager")
    public String addManager(Manager manager){
        managerService.save(manager);
        return "redirect:/manager";
    }
    @GetMapping("/managerDelete/{stmid}")
    public String managerDelete(@PathVariable String stmid){
        boolean status= managerService.managerDelete(stmid);
        System.out.println("Status : "+status);
        return "redirect:/manager";
    }
    @GetMapping("/managerUpdate")
    public String managerUpdate(Manager manager){
        manager.setMid(updateMID);
        managerService.updateManager(manager);
        return "redirect:/manager";
    }
}
