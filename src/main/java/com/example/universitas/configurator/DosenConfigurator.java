package com.example.universitas.configurator;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DosenConfigurator {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
