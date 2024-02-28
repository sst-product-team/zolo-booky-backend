package com.zolobooky.booky.appeals;

import com.zolobooky.booky.appeals.dto.AppealDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppealService {

    @Autowired
    private final AppealRepository appealRepository;

    private final ModelMapper modelMapper;

    public AppealService(AppealRepository appealRepository, ModelMapper modelMapper) {
        this.appealRepository = appealRepository;
        this.modelMapper = modelMapper;
    }

    public List<AppealEntity> getAllAppeals() {
        return appealRepository.findAll();
    }

    public Optional<AppealEntity> getAppeal(Integer trans_id) {
        return appealRepository.findById(trans_id);
    }

    public AppealEntity createAppeal(AppealDTO appealDTO) {
        AppealEntity appealEntity = modelMapper.map(appealDTO, AppealEntity.class);
        return appealRepository.save(appealEntity);
    }

    public Optional<AppealEntity> updateAppealStatus(Integer trans_id, AppealDTO appealDTO) {
        AppealEntity appealDataEntity = modelMapper.map(appealDTO, AppealEntity.class);
        Optional<AppealEntity> savedEntity = appealRepository.findById(trans_id);

//        savedEntity.ifPresent(appealEntity -> appealEntity.setTrans_status(CustomStatus.TransactionStatus.valueOf(appealDTO.getTrans_status())));
        return savedEntity;
    }

}
