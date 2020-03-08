package com.doku.aplikasiregistrasi.dao;

import com.doku.aplikasiregistrasi.entity.Materi;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MateriDao extends PagingAndSortingRepository<Materi, String> {
}
