package com.doku.aplikasiregistrasi.dao;

import com.doku.aplikasiregistrasi.entity.Pembayaran;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PembayaranDao
 */
public interface PembayaranDao extends PagingAndSortingRepository<Pembayaran, String> {

    
}
