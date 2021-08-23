package com.example.universitas.controller;

import com.example.universitas.model.dto.MahasiswaDto;
import com.example.universitas.model.entity.Mahasiswa;
import com.example.universitas.model.projection.MahasiswaProjection;
import com.example.universitas.service.MahasiswaServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mahasiswa")
public class MahasiswaController {

    // Auto wiring
    @Autowired
    private MahasiswaServiceImpl mahasiswaService;

    @Autowired
    private ModelMapper modelMapper;

    // Mapping section
    @PostMapping
    public MahasiswaDto saveMahasiswa(@RequestBody Mahasiswa mahasiswa) {
        return modelMapper.map(mahasiswaService.saveMahasiswa(mahasiswa), MahasiswaDto.class);
    }

    @GetMapping
    public List<MahasiswaProjection> getAllMahasiswa() {
        return mahasiswaService.getAllMahasiswa();
    }

    @GetMapping("/{id}")
    public MahasiswaProjection getMahasiswaById(@PathVariable("id") String idMahasiswa) {
        return mahasiswaService.getMahasiswaById(idMahasiswa);
    }
}
