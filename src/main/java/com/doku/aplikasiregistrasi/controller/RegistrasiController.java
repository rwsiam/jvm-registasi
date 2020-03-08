package com.doku.aplikasiregistrasi.controller;

import com.doku.aplikasiregistrasi.entity.Peserta;
import com.doku.aplikasiregistrasi.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/registrasi")
public class RegistrasiController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/form")
    public ModelMap tampilkanFormRegistrasi() {
        log.info("Menjalankan method tampilkanFormRegistrasi");
        ModelMap mm = new ModelMap();
        mm.addAttribute("nama", "Endy");
        mm.addAttribute("waktu", LocalDateTime.now());
        return mm;
    }

    @PostMapping("/form")
    public String prosesFormRegistrasi(@ModelAttribute @Valid Peserta peserta,
                                       BindingResult errors,
                                       SessionStatus status) {
        log.info("Seharusnya nanti di sini insert ke database");

        if (errors.hasErrors()) {
            return "form";
        }

        registrationService.registrasiPesertaBaru(peserta);
        status.setComplete();

        /* jangan return html, return redirect supaya tidak dobel submit
        ModelAndView mav = new ModelAndView("konfirmasi");
        return mav;
         */

        return "redirect:confirmation";
    }

    @GetMapping("/confirmation")
    public void tampilkanHalamanKonfirmasi() {

    }

    @GetMapping("/verify")
    public String verifikasiEmail(@RequestParam String token) {
        registrationService.verifikasiToken(token);
        return "redirect:verified";
    }

    @GetMapping("/verified")
    public void emailTerverifikasi() {

    }
}
