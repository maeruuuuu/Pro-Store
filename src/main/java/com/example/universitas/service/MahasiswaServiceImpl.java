package com.example.universitas.service;

import com.example.universitas.model.dto.MahasiswaDto;
import com.example.universitas.model.entity.Mahasiswa;
import com.example.universitas.model.projection.MahasiswaProjection;
import com.example.universitas.repository.MahasiswaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MahasiswaServiceImpl implements MahasiswaService{

    @Autowired
    private MahasiswaRepo mahasiswaRepo;

    @Override
    public List<MahasiswaProjection> getAllMahasiswa() {
        return mahasiswaRepo.findAllDto();
    }

    @Override
    public MahasiswaProjection getMahasiswaById(String idMahasiswa) {
        return mahasiswaRepo.findByIdMahasiswa(idMahasiswa);
    }

    @Override
    public Mahasiswa saveMahasiswa(Mahasiswa mahasiswa) {
        return mahasiswaRepo.save(mahasiswa);
    }
}
