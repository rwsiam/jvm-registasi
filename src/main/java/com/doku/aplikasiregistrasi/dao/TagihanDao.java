package com.doku.aplikasiregistrasi.dao;
import com.doku.aplikasiregistrasi.entity.Tagihan;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagihanDao extends PagingAndSortingRepository<Tagihan, String> {

    Tagihan findByNomorInvoice(String nomorInvoice);
}
