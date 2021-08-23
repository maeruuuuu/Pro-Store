package com.example.universitas.service;

import com.example.universitas.model.dto.MahasiswaDto;
import com.example.universitas.model.entity.Mahasiswa;
import com.example.universitas.model.projection.MahasiswaProjection;

import java.util.List;

public interface MahasiswaService {

    List<MahasiswaProjection> getAllMahasiswa();

    MahasiswaProjection getMahasiswaById(String idMahasiswa);

    Mahasiswa saveMahasiswa(Mahasiswa mahasiswa);
}
