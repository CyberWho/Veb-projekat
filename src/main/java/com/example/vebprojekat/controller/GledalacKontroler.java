package com.example.vebprojekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GledalacKontroler {
    @GetMapping("/pregled_rezervacija")
    public String rezervacije(Model model){
        return "pregled_rezervacija.html";
    }

}
