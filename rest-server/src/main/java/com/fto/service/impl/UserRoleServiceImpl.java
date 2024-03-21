package com.fto.service.impl;

import com.fto.model.entity.UserRoleEntity;
import com.fto.model.enums.UserRoleEnum;
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
            admin.setRole(UserRoleEnum.ADMIN);
            userRoleRepository.save(admin);
            UserRoleEntity moderator =new UserRoleEntity();
            moderator.setRole(UserRoleEnum.FAMILY_MODERATOR);
            userRoleRepository.save(moderator);

        }

    }
}
