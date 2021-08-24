package com.example.universitas.repository;

import com.example.universitas.model.entity.JurusanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JurusanRepo extends JpaRepository<JurusanEntity, String> {

//    @Query (
//            value = "SELECT *" +
//                    "FROM jurusan" +
//                    "WHERE jurusan.id_jurusan =: idJurusan",
//            nativeQuery = true
//    )
//    List<JurusanEntity> findByIdJurusan(@Param("idJurusan") String idJurusan);

    @Query (
            value = "SELECT *" +
                    "FROM jurusan" +
                    "WHERE jurusan.fk_Kode_fakultas =: fkKodeFakultas",
            nativeQuery = true
    )
    List<JurusanEntity> findByFak(@Param("fkKodeFakultas") Long fkKodeFakultas);

    @Query (
            value = "SELECT *, COUNT(jurusan) AS jumlah" +
                    "FROM jurusan" +
                    "GROUP BY jumlah" +
                    "WHERE jurusan.fk_kode_fakultas = :fkKodeFakultas",
            nativeQuery = true
    )
    List<JurusanEntity> countJurByFak(@Param("fkKodeFakultas") Long fkKodeFakultas);

}
