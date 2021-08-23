package com.example.universitas.controller;


import com.example.universitas.model.dto.JurusanDto;
import com.example.universitas.model.entity.JurusanEntity;
import com.example.universitas.service.JurusanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/jurusan/")
public class JurusanController {

    @Autowired
    public JurusanService jurusanService;

    @Autowired
    ModelMapper modelMapper;

    private JurusanDto convertToDto(JurusanEntity jurusanEntity){
        JurusanDto jurusanDto = modelMapper.map(jurusanEntity, JurusanDto.class);
        return jurusanDto;
    }

    private JurusanEntity convertToEntity(JurusanDto jurusanDto){
        JurusanEntity jurusanEntity = modelMapper.map(jurusanDto, JurusanEntity.class);
        return jurusanEntity;
    }

    @GetMapping()
    public List<JurusanDto> showAllJurusan() {
        List<JurusanEntity> jurusanEntities = jurusanService.getAllJurusan();
        return jurusanEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<JurusanDto> showJurusanById(@PathVariable Long id) {
        List<JurusanEntity> jurusanEntities = jurusanService.getJurusanById(id);
        return jurusanEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/kodefak")
    public List<JurusanDto> showJurusanByKodeFak(@RequestParam("fak") Long fak) {
        List<JurusanEntity> jurusanEntities = jurusanService.getJurusanByFak(fak);
        return jurusanEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
