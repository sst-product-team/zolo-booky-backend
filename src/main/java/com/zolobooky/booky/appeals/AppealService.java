package com.zolobooky.booky.appeals;

import com.zolobooky.booky.appeals.dto.AppealDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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

    public AppealEntity getAppeal(Integer trans_id) {
        Optional<AppealEntity> appealEntity = appealRepository.findById(trans_id);
        AtomicReference<AppealEntity> responseEntity = new AtomicReference<>(new AppealEntity());

        appealEntity.ifPresentOrElse(responseEntity::set, () -> {
            responseEntity.get().setTrans_id(-1);
        });

        return responseEntity.get();
    }

    public AppealEntity createAppeal(AppealDTO appealDTO) {
        AppealEntity appealEntity = modelMapper.map(appealDTO, AppealEntity.class);
        appealEntity.setTrans_id((int) (appealRepository.count() + 1));

        return appealRepository.save(appealEntity);
    }

    public Optional<AppealEntity> updateAppealStatus(Integer trans_id, AppealDTO appealDTO) {
        AppealEntity appealDataEntity = modelMapper.map(appealDTO, AppealEntity.class);

//        savedEntity.ifPresent(appealEntity -> appealEntity.setTrans_status(CustomStatus.TransactionStatus.valueOf(appealDTO.getTrans_status())));
        return appealRepository.findById(trans_id);
    }

}
