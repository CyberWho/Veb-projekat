package com.example.vebprojekat.controller;

import com.example.vebprojekat.entity.Admin;
import com.example.vebprojekat.entity.Korisnik;
import com.example.vebprojekat.service.AdminService;
import com.example.vebprojekat.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminKontroler {
    @Autowired
    AdminService adminService;

    @Autowired
    KorisnikService korisnikService;


    @GetMapping(value = "/odobri_registraciju/{id}")
    public String odobri_registraciju(@PathVariable(name = "id") Long id, Model model) throws Exception {
        Korisnik korisnik = korisnikService.findOne(id);
        System.out.println("ID: " + id);
        if(adminService.approveRegistration(korisnik)) return "redirect:/admin_index";
        return "error.html";
    }

    @GetMapping("/admin-bioskopi")
    public String admin_bioskopi(){
        return "admin_bioskopi.html";
    }

    @GetMapping("/admin-menadzeri")
    public String admin_menadzeri(){
        return "admin_menadzeri.html";
    }

}
