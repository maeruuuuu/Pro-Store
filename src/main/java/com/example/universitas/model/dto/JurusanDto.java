package com.example.universitas.model.dto;

import lombok.Data;

@Data
public class JurusanDto {

    private long idJurusan;
    private String namaJurusan;
    private long kodeJurusan;
    private long fkKodeFakultas;
}
