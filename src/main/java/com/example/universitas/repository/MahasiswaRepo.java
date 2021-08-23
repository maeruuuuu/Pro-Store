package com.example.universitas.repository;

import com.example.universitas.model.dto.MahasiswaDto;
import com.example.universitas.model.entity.Mahasiswa;
import com.example.universitas.model.projection.MahasiswaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MahasiswaRepo extends JpaRepository<Mahasiswa, String> {

    @Query("SELECT mahasiswa FROM Mahasiswa mahasiswa")
    List<MahasiswaProjection> findAllDto();

    MahasiswaProjection findByIdMahasiswa(String idMahasiswa);

}
