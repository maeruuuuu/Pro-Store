package com.example.universitas.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "fakultas")
public class FakultasEntity {

    @Id
    @GeneratedValue(generator = "fak-generator")
    @GenericGenerator(name = "fak-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "FAK"),
            strategy = "com.example.universitas.identifier.MyGenerator"
    )
    @Column(name = "id_fakultas")
    private String idFakultas;

    @Column(name = "nama_fakultas")
    private String namaFakultas;

    @Column(name = "kode_fakultas", unique = true)
    private String kodeFakultas;
}
