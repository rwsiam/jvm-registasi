package com.doku.aplikasiregistrasi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrasiController {

    @GetMapping("/registrasi")
    public void registrasiForm(){

    }

    @GetMapping("/konfirmasi")
    public void konfirmasiForm(){

    }
}
