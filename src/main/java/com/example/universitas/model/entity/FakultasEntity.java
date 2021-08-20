package com.example.universitas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "fakultas")
public class FakultasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fakultas")
    private Long idFakultas;

    @Column(name = "nama_fakutas")
    private String namaFakultas;

    @Column(name = "kode_fakultas")
    private String kodeFakultas;
}
