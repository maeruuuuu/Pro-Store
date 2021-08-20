package com.example.universitas.controller;

import com.example.universitas.service.FakultasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakultasController {

    @Autowired
    public FakultasService fakultasService;

    @Autowired
    ModelMapper modelMapper;


}
