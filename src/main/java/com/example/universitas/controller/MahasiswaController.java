package com.example.universitas.controller;

import com.example.universitas.model.dto.MahasiswaDto;
import com.example.universitas.model.entity.MahasiswaEntity;
import com.example.universitas.model.projection.MahasiswaCountByFakultasProjection;
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
    public MahasiswaDto saveMahasiswa(@RequestBody MahasiswaEntity mahasiswaEntity) {
        return modelMapper.map(mahasiswaService.saveMahasiswa(mahasiswaEntity), MahasiswaDto.class);
    }

    @GetMapping("all")
    public List<MahasiswaProjection> getAllMahasiswa() {
        return mahasiswaService.getAllMahasiswa();
    }

    @GetMapping
    public MahasiswaProjection getMahasiswaById(@RequestParam(name = "data", defaultValue = "") String id, @RequestParam(name = "by", defaultValue = "") String findBy) {

        if (findBy.equalsIgnoreCase("nim")) {
            return mahasiswaService.getMahasiswaByNim(id);
        }

        return mahasiswaService.getMahasiswaById(id);

    }

    @GetMapping("jumlah")
    public MahasiswaCountByFakultasProjection countMahasiswaByIdFakultas(@RequestParam(name = "id", defaultValue = "") String id, @RequestParam(name = "findBy",defaultValue = "") String countBy) {

        if (countBy.equalsIgnoreCase("fakultas")) {
            return mahasiswaService.countMahasiswaByIdFakultas(id);
        }

        return null;
    }
}
