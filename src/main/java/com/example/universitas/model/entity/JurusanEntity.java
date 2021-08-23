package com.example.universitas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "jurusan")
public class JurusanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY())
    @Column(name = "id_jurusan");
    private Long idJurusan;

    @Column(name = "nama_jurusan")
    private String namaJurusan;

    @Column(name = "kode_jurusan")
    private Long kodeJurusan;

    @Column(name = "fk_Kode_fakultas")
    private Long fkKodeFakultas;

}
