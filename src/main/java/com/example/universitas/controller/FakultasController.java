package com.example.universitas.controller;

import com.example.universitas.model.dto.FakultasDto;
import com.example.universitas.model.entity.FakultasEntity;
import com.example.universitas.service.FakultasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/fakultas")
public class FakultasController {

    @Autowired
    public FakultasService fakultasService;

    @Autowired
    ModelMapper modelMapper;

    private FakultasDto converToDto(FakultasEntity fakultasEntity) {
        FakultasDto fakultasDto = modelMapper.map(fakultasEntity, FakultasDto.class);
        return fakultasDto;
    }

    private FakultasEntity converToEntity(FakultasDto fakultasDto) {
        FakultasEntity fakultasEntity = modelMapper.map(fakultasDto, FakultasEntity.class);
        return fakultasEntity;
    }

    @GetMapping
    public List<FakultasDto> getAllFakultas(){
        List<FakultasEntity> fakultasEntityList = fakultasService.getAllFakultas();
        return fakultasEntityList.stream().map(this::converToDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public FakultasDto getFakultasById(@PathVariable("id") String id){
        return converToDto(fakultasService.getByFakultasId(id));
    }

    @PostMapping
    public FakultasDto insertFakultas(@RequestBody FakultasDto fakultasDto) {
        FakultasEntity fakultasEntity = converToEntity(fakultasDto);
        FakultasEntity fakultasInsert = fakultasService.saveFakultas(fakultasEntity);
        return converToDto(fakultasInsert);
    }

    @PutMapping
    public FakultasDto editFakultas(@RequestBody FakultasDto fakultasDto) {
        FakultasEntity fakultasEntity = converToEntity(fakultasDto);
        FakultasEntity fakultasEdit = fakultasService.saveFakultas(fakultasEntity);
        return converToDto(fakultasEdit);
    }
}
