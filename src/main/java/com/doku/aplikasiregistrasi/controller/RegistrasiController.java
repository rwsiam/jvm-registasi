package com.doku.aplikasiregistrasi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrasiController {

    @GetMapping("/registrasi")
    public void registrasiForm(){

    }

    @PostMapping("/registrasi")
    public String registrasiSubmit(){
        return "redirect:konfirmasi";
    }

    @GetMapping("/konfirmasi")
    public void konfirmasiForm(){

    }
}
