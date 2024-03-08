package com.fto.service.impl;

import com.fto.model.entity.AgeCategoryEntity;
import com.fto.model.enums.AgeCategoryEnum;
import com.fto.repository.AgeCategoryRepository;
import com.fto.service.AgeCategoryService;
import org.springframework.stereotype.Service;

@Service
public class AgeCategoryServiceImpl implements AgeCategoryService {
    private final AgeCategoryRepository ageCategoryRepository;

    public AgeCategoryServiceImpl(AgeCategoryRepository ageCategoryRepository) {
        this.ageCategoryRepository = ageCategoryRepository;
    }

    @Override
    public void init() {
        if(ageCategoryRepository.count()==0){
            AgeCategoryEntity adult=new AgeCategoryEntity();
            adult.setAgeCategory(AgeCategoryEnum.ADULT);
            ageCategoryRepository.save(adult);

            AgeCategoryEntity teen=new AgeCategoryEntity();
            teen.setAgeCategory(AgeCategoryEnum.TEEN);
            ageCategoryRepository.save(teen);

            AgeCategoryEntity infant =new AgeCategoryEntity();
            infant.setAgeCategory(AgeCategoryEnum.INFANT);
            ageCategoryRepository.save(infant);


        }

    }
}
