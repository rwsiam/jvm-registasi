package com.doku.aplikasiregistrasi.service;

import com.doku.aplikasiregistrasi.dao.PesertaDao;
import com.doku.aplikasiregistrasi.dao.VerifikasiEmailDao;
import com.doku.aplikasiregistrasi.entity.Peserta;
import com.doku.aplikasiregistrasi.entity.VerifikasiEmail;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service @Transactional
public class RegistrationService {

    @Value("${token.expiry.days}")
    private Integer tokenExpiryDays;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${gmail.account.username}")
    private String mailFrom;

    @Autowired private PesertaDao pesertaDao;
    @Autowired private VerifikasiEmailDao verifikasiEmailDao;
    @Autowired private EmailService emailService;
    @Autowired private MustacheFactory mustacheFactory;

    public void registrasiPesertaBaru(Peserta p) {
        VerifikasiEmail ve = new VerifikasiEmail();
        ve.setPeserta(p);
        ve.setToken(UUID.randomUUID().toString());
        ve.setExpire(LocalDateTime.now().plusDays(tokenExpiryDays));

        pesertaDao.save(p);
        verifikasiEmailDao.save(ve);

        kirimVerifikasi(ve);
    }

    private void kirimVerifikasi(VerifikasiEmail ve) {
        Mustache templateEmail =
                mustacheFactory.compile("templates/notification/verification.html");
        Map<String, String> data = new HashMap<>();
        data.put("nama", ve.getPeserta().getNama());
        data.put("server.url", serverUrl);
        data.put("token", ve.getToken());

        StringWriter output = new StringWriter();
        templateEmail.execute(output, data);

        emailService.kirimEmail(
                mailFrom,
                ve.getPeserta().getEmail(),
                "Verifikasi Email "+ve.getPeserta().getNama(),
                output.toString());
    }

    public void verifikasiToken(String token) {
        VerifikasiEmail v = verifikasiEmailDao.findByToken(token);
        if (v != null) {
            Peserta p = v.getPeserta();
            p.setEmailTerverifikasi(true);
            verifikasiEmailDao.delete(v);
        }
    }
}