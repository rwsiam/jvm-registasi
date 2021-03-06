package com.doku.aplikasiregistrasi.controller;

import java.time.LocalDateTime;

import com.doku.aplikasiregistrasi.dao.PembayaranDao;
import com.doku.aplikasiregistrasi.dao.TagihanDao;
import com.doku.aplikasiregistrasi.service.DokuService;
import com.doku.aplikasiregistrasi.service.dto.request.DokuHostedIdentifyDTO;
import com.doku.aplikasiregistrasi.service.dto.request.DokuHostedNotifyDTO;
import com.doku.aplikasiregistrasi.service.dto.request.DokuHostedRedirectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * DokuController
 */
@Controller
@RequestMapping("/doku")
@Slf4j
public class DokuController {

    @Autowired
    DokuService dokuService;

    @Autowired
    TagihanDao tagihanDao;

    @Autowired
    PembayaranDao pembayaranDao;
    
    @GetMapping("/continue")
    public String kirimCustomerKeDoku(ModelMap model){
        return "doku";
    }

    @PostMapping(value = "/notify")
    @ResponseBody public String notify(DokuHostedNotifyDTO request){
        log.info(request.toString());
        dokuService.processNotify(request);
        return "CONTINUE";
    }

    //Controller untuk notify dari DOKU merchant
    @PostMapping(value = "/identify")
    @ResponseBody public String identify(DokuHostedIdentifyDTO request, Model model) {
        return "CONTINUE";
    }
    
    //Controller untuk menghandle redirect dari DOKU merchant
    @PostMapping("/redirect")
    public String redirect(DokuHostedRedirectDTO request) {
        log.info(request.toString());   
        // Pasang logic membaca redirect
        
        return "doku_redirect";
    }
    
}
