package com.example.universitas.service;

import com.example.universitas.exception.ResourceNotFoundException;
import com.example.universitas.model.entity.JurusanEntity;
import com.example.universitas.repository.JurusanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class JurusanServiceImpl implements JurusanService{

    @Autowired
    public JurusanRepo jurusanRepo;

    //------------------------- Service Jurusan ----------------------------
    @Override
    public List<JurusanEntity> getAllJurusan() {
        List<JurusanEntity> jurusanEntities = new ArrayList<>();
        jurusanRepo.findAll().forEach(jurusanEntities::add);
        return jurusanEntities;
    }

    @Override
    public List<JurusanEntity> getJurusanById(Long idJurusan) {
        List<JurusanEntity> jurusanEntities = new ArrayList<>();
        jurusanRepo.findByIdJurusan(idJurusan).forEach(jurusanEntities::add);
        return jurusanEntities;
    }

    @Override
    public List<JurusanEntity> getJurusanByFak(Long fkKodeFakultas) {
        List<JurusanEntity> jurusanEntities = new ArrayList<>();
        jurusanRepo.findByFak(fkKodeFakultas).forEach(jurusanEntities::add);
        return jurusanEntities;
    }

    @Override
    public JurusanEntity saveJurusan(JurusanEntity jurusanEntity){
        return jurusanRepo.save(jurusanEntity);
    }

    @Override
    public ResponseEntity<JurusanEntity> updateJurusan(Long idJurusan, JurusanEntity jurusanDetails)throws ResourceNotFoundException {

        JurusanEntity jurusanEntity = jurusanRepo.findById(idJurusan)
                .orElseThrow(() -> new ResourceNotFoundException("Jurusan not found for this id :: " + idJurusan));
        jurusanEntity.setNamaJurusan(jurusanDetails.getNamaJurusan());
        jurusanEntity.setKodeJurusan(jurusanDetails.getKodeJurusan());
        jurusanEntity.setFkKodeFakultas(jurusanDetails.getFkKodeFakultas());
        final JurusanEntity updatedJurusan = jurusanRepo.save(jurusanEntity);
        return ResponseEntity.ok(this.jurusanRepo.save(jurusanEntity));
    }

    @Override
    public Long countJurByFak(Long fkKodeFakultas){
        Long jumlah;
        jumlah = jurusanRepo.countJurByFak(fkKodeFakultas);
        return jumlah;
}
