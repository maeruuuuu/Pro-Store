package com.example.universitas.service;

import com.example.universitas.model.entity.MahasiswaEntity;
import com.example.universitas.model.projection.MahasiswaCountByFakultasProjection;
import com.example.universitas.model.projection.MahasiswaProjection;

import java.util.List;

public interface MahasiswaService {

    List<MahasiswaProjection> getAllMahasiswa();

    MahasiswaProjection getMahasiswaById(String idMahasiswa);

    MahasiswaProjection getMahasiswaByNim(String nim);

    MahasiswaEntity saveMahasiswa(MahasiswaEntity mahasiswaEntity);

    MahasiswaCountByFakultasProjection countMahasiswaByIdFakultas(String idFakultas);
}
