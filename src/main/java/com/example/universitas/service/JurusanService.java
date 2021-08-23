package com.example.universitas.service;

import com.example.universitas.exception.ResourceNotFoundException;
import com.example.universitas.model.entity.JurusanEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JurusanService {

    List<JurusanEntity> getAllJurusan();
    List<JurusanEntity> getJurusanById(Long id);
    List<JurusanEntity> getJurusanByFak(Long fak);
    JurusanEntity saveJurusan(JurusanEntity jurusanEntity);
    ResponseEntity<JurusanEntity> updateJurusan(Long idJurusan, JurusanEntity jurusanDetails)throws ResourceNotFoundException;
    Long countJurByFak(Long fak);

}
