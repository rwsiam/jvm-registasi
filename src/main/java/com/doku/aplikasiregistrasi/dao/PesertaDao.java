package com.doku.aplikasiregistrasi.dao;
import com.doku.aplikasiregistrasi.entity.Peserta;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PesertaDao extends PagingAndSortingRepository<Peserta, String> {
}
