package com.example.universitas.service;

import com.example.universitas.exception.ResourceNotFoundException;
import com.example.universitas.model.entity.JurusanEntity;
import com.example.universitas.repository.JurusanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JurusanServiceImpl implements JurusanService {

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
    public ResponseEntity<Optional<JurusanEntity>> getJurusanById(String idJurusan) {
        Optional<JurusanEntity> jurusanEntities = jurusanRepo.findById(idJurusan);
        return ResponseEntity.ok().body(jurusanEntities);
    }

    @Override
    public ResponseEntity delJurusanById(String idJurusan) throws ResourceNotFoundException {
        JurusanEntity jurusanEntity = jurusanRepo.findById(idJurusan)
                .orElseThrow(() -> new ResourceNotFoundException("Jurusan not found for this id = " + idJurusan));
        jurusanRepo.deleteById(idJurusan);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @Override
    public List<JurusanEntity> getJurusanByFak(long fkKodeFakultas) {
        List<JurusanEntity> jurusanEntities = new ArrayList<>();
        jurusanRepo.findByFak(fkKodeFakultas).forEach(jurusanEntities::add);
        return jurusanEntities;
    }

    @Override
    public JurusanEntity saveJurusan(JurusanEntity jurusanEntity) {
        return jurusanRepo.save(jurusanEntity);
    }

    @Override
    public ResponseEntity<JurusanEntity> updateJurusan(String idJurusan, JurusanEntity jurusanDetails) throws ResourceNotFoundException {

        JurusanEntity jurusanEntity = jurusanRepo.findById(idJurusan)
                .orElseThrow(() -> new ResourceNotFoundException("Jurusan not found for this id :: " + idJurusan));
        jurusanEntity.setNamaJurusan(jurusanDetails.getNamaJurusan());
        jurusanEntity.setKodeJurusan(jurusanDetails.getKodeJurusan());
        final JurusanEntity updatedJurusan = jurusanRepo.save(jurusanEntity);
        return ResponseEntity.ok(this.jurusanRepo.save(jurusanEntity));
    }

    @Override
    public List<JurusanEntity> countJurByFak(Long fkKodeFakultas) {
        List<JurusanEntity> jumlah;
        jumlah = jurusanRepo.countJurByFak(fkKodeFakultas);
        return jumlah;
    }
}
