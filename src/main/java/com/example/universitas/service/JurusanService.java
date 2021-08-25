package com.example.universitas.service;

import com.example.universitas.exception.ResourceNotFoundException;
import com.example.universitas.model.entity.JurusanEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface JurusanService {

    List<JurusanEntity> getAllJurusan();
    ResponseEntity<Optional<JurusanEntity>> getJurusanById(String id);
    void delJurusanById(String id)throws ResourceNotFoundException ;
    List<JurusanEntity> getJurusanByFak(long fak);
    JurusanEntity saveJurusan(JurusanEntity jurusanEntity);
    ResponseEntity<JurusanEntity> updateJurusan(String idJurusan, JurusanEntity jurusanDetails)throws ResourceNotFoundException;
    List<JurusanEntity> countJurByFak(Long fak);

}
