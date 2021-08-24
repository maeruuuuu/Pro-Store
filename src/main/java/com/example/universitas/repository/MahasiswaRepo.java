package com.example.universitas.repository;

import com.example.universitas.model.entity.MahasiswaEntity;
import com.example.universitas.model.projection.MahasiswaCountByFakultasProjection;
import com.example.universitas.model.projection.MahasiswaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MahasiswaRepo extends JpaRepository<MahasiswaEntity, String> {

    @Query("SELECT mahasiswa FROM MahasiswaEntity mahasiswa")
    List<MahasiswaProjection> findAllProjection();

    MahasiswaProjection findByIdMahasiswa(String idMahasiswa);

    MahasiswaProjection findByNim(String nim);


    @Query("SELECT f.idFakultas as idFakultas, f.namaFakultas as namaFakultas, COUNT(m.idFakultas) as jumlahMahasiswa FROM FakultasEntity f left join MahasiswaEntity m on f.idFakultas = m.idFakultas where f.idFakultas = ?1 GROUP BY f.idFakultas")
    MahasiswaCountByFakultasProjection countByIdFakultas(String idFakultas);

}
