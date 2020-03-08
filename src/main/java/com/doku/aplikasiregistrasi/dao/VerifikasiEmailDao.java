package com.doku.aplikasiregistrasi.dao;
import com.doku.aplikasiregistrasi.entity.VerifikasiEmail;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VerifikasiEmailDao extends PagingAndSortingRepository<VerifikasiEmail, String> {
    VerifikasiEmail findByToken(String token);
}
