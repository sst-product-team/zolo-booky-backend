package com.zolobooky.booky.appeals;

import com.zolobooky.booky.appeals.dto.AppealDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AppealService {

    public final List<AppealDTO> appealDemoData = Arrays.asList(new AppealDTO[] {
            new AppealDTO(0, 0, 0),
            new AppealDTO(1, 1, 1),
            new AppealDTO(2, 2, 2)
    });

    public List<AppealDTO> getAllAppeals() {
        return appealDemoData;
    }

    public AppealDTO getAppeal(Integer trans_id) {
        for (AppealDTO appealDTO: appealDemoData) {
            if (appealDTO.getTrans_id().equals(trans_id)) {
                return appealDTO;
            }
        }

        return null;
    }

    public AppealDTO addAppeal(AppealDTO appealDTO) {
        Integer appealId = appealDemoData.size();
        appealDTO.setTrans_id(appealId);

        return appealDTO;
    }

}
