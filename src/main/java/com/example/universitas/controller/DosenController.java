package com.example.universitas.controller;

import com.example.universitas.model.dto.DosenDto;
import com.example.universitas.model.entity.DosenEntity;
import com.example.universitas.service.DosenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/dosen")
public class DosenController {

    @Autowired
    private DosenService dosenService;

    @Autowired
    ModelMapper modelMapper;

    private DosenDto convertToDto(DosenEntity dosenEntity) {
        DosenDto dosenDto = modelMapper.map(dosenEntity, DosenDto.class);
        return dosenDto;
    }

    private DosenEntity convertToEntity(DosenDto dosenDto) {
        DosenEntity dosenEntity = modelMapper.map(dosenDto, DosenEntity.class);
        return dosenEntity;
    }

    @PostMapping
    public DosenDto create (@RequestBody DosenDto dosenDto) {
        DosenEntity dosenEntity = convertToEntity(dosenDto);
        DosenEntity dosenEntity1 = dosenService.saveDosen(dosenEntity);
        return convertToDto(dosenEntity1);
    }

    @PutMapping
    public DosenDto update (@RequestBody DosenDto dosenDto) {
        DosenEntity dosenEntity = convertToEntity(dosenDto);
        DosenEntity dosenEntity1 = dosenService.saveDosen(dosenEntity);
        return convertToDto(dosenEntity1);
    }

    @GetMapping
    public List<DosenDto> getAllDosen(){
        List<DosenEntity> dosenEntityList = dosenService.getAllDosen();
        return dosenEntityList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/idDosen")
    public DosenDto findDosenId (@RequestParam("idDosen") String idDosen){
        return convertToDto(dosenService.findDosenId(idDosen));
    }

    @GetMapping(value = "/nip")
    public DosenDto findDosenNip (@RequestParam("nip") String nip){
        return convertToDto(dosenService.findDosenNip(nip));
    }

    @GetMapping(value = "/jumlahDosen")
    public long countDosen (@RequestParam("idFakultas") String idFakultas){
        return dosenService.countDosenbyFakultas(idFakultas);
    }

    @GetMapping(value = "/DosenFakultas")
    public List<DosenDto> findDosenFakultas (@RequestParam("idFakultas") String idFakultas){
        List<DosenEntity> dosenEntityList = dosenService.findDosenbyFakultas(idFakultas);
        return dosenEntityList.stream().map(this::convertToDto).collect(Collectors.toList());
    }




}
