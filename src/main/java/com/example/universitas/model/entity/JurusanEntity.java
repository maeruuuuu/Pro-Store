package com.example.universitas.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

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

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "jur"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "kode_jurusan")
    private Long kodeJurusan;

    @Column(name = "fk_Kode_fakultas")
    private Long fkKodeFakultas;

}
