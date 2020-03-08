package com.doku.aplikasiregistrasi.dao;

import com.doku.aplikasiregistrasi.entity.Pendaftaran;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PendaftaranDao extends PagingAndSortingRepository<Pendaftaran, String> {
}
