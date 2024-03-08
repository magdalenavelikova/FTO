package com.fto.service.impl;

import com.fto.model.entity.UserRoleEntity;
import com.fto.model.enums.UsersRoleEnum;
import com.fto.repository.UserRoleRepository;
import com.fto.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void init() {
        if (userRoleRepository.count()==0){
            UserRoleEntity admin=new UserRoleEntity();
            admin.setRole(UsersRoleEnum.ADMIN);
            userRoleRepository.save(admin);

            UserRoleEntity distributor =new UserRoleEntity();
            distributor.setRole(UsersRoleEnum.DISTRIBUTOR);
            userRoleRepository.save(distributor);

            UserRoleEntity viewer =new UserRoleEntity();
            viewer.setRole(UsersRoleEnum.VIEWER);
            userRoleRepository.save(viewer);

            UserRoleEntity creator =new UserRoleEntity();
            creator.setRole(UsersRoleEnum.CREATOR);
            userRoleRepository.save(creator);
        }

    }
}
