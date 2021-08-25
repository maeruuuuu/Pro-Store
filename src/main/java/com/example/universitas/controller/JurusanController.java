package com.example.universitas.controller;


import com.example.universitas.exception.ResourceNotFoundException;
import com.example.universitas.model.dto.JurusanDto;
import com.example.universitas.model.entity.JurusanEntity;
import com.example.universitas.service.JurusanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/jurusan")
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
//    public List<JurusanDto> showJurusanById(@PathVariable String id) {
//        List<JurusanEntity> jurusanEntities = jurusanService.getJurusanById(id);
//        return jurusanEntities.stream().map(this::convertToDto).collect(Collectors.toList());
//    }
    public ResponseEntity<Optional<JurusanEntity>> showJurusanById(@PathVariable(value="id") String jurusanId) throws ResourceNotFoundException {
        return jurusanService.getJurusanById(jurusanId);
//        return jurusanUpdate;
    }

    @GetMapping(value = "/kodefak")
    public List<JurusanDto> showJurusanByKodeFak(@RequestParam("fak") Long fak) {
        List<JurusanEntity> jurusanEntities = jurusanService.getJurusanByFak(fak);
        return jurusanEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    public JurusanDto insertJurusan(@RequestBody JurusanDto jurusanDto){
        JurusanEntity jurusanEntity = convertToEntity(jurusanDto);
        JurusanEntity jurusanUpdate = jurusanService.saveJurusan(jurusanEntity);
        return convertToDto(jurusanUpdate);
    }

    //delete jurusan
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteJurusan(@PathVariable(value = "id") String jurusanId)throws ResourceNotFoundException {
        jurusanService.delJurusanById(jurusanId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

    // update jurusan
    @PutMapping("/{id}")
    public ResponseEntity<JurusanEntity> updateJurusan(@PathVariable(value="id") String jurusanId, @RequestBody JurusanEntity jurusanEntity) throws ResourceNotFoundException {
        ResponseEntity<JurusanEntity> jurusanUpdate = jurusanService.updateJurusan(jurusanId, jurusanEntity);
        return jurusanUpdate;
    }

    @GetMapping("/fak/{fak}")
    public List<JurusanEntity> countJurusan(@PathVariable(value = "fak") long fak){
        return jurusanService.countJurByFak(fak);
    }
}
