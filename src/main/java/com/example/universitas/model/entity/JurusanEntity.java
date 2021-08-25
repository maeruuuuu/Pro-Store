package com.example.universitas.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "jurusan")
public class JurusanEntity {

    @Id
    @GeneratedValue(generator = "jur-generator")
    @GenericGenerator(name = "jur-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "PRODI"),
            strategy = "com.example.universitas.generator.MyGenerator"
    )
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jurusan")
    private String idJurusan;

    @Column(name = "nama_jurusan")
    private String namaJurusan;


//    @GeneratedValue(generator = "sequence-generator")
//    @GenericGenerator(
//            name = "sequence-generator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @Parameter(name = "sequence_name", value = "jur"),
//                    @Parameter(name = "initial_value", value = "1"),
//                    @Parameter(name = "increment_size", value = "1")
//            }
//    )


    @Column(name = "kode_jurusan")
    private String kodeJurusan;
    @Column(name = "fk_Kode_fakultas")
    private Long fkKodeFakultas;

//    @ManyToOne
//    @JoinColumn(name = "jurusanEntity", insertable = false, updatable = false)
//    private JurusanEntity jurusan;

}
