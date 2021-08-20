package com.example.universitas.service;

import com.example.universitas.model.entity.FakultasEntity;
import com.example.universitas.repository.FakultasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakultasServiceImpl implements FakultasService {

    @Autowired
    public FakultasRepo fakultasRepo;

    @Override
    public List<FakultasEntity> getAllFakultas(){
        List<FakultasEntity> fakultasEntityList = new ArrayList<>();
        fakultasRepo.findAll().forEach(fakultasEntityList::add);
        return fakultasEntityList;
    }

    @Override
    public FakultasEntity getByFakultasId(String id){
        return fakultasRepo.findById(id).get();
    }

    @Override
    public FakultasEntity saveFakultas(FakultasEntity fakultasEntity){
        return fakultasRepo.save(fakultasEntity);
    }


}
