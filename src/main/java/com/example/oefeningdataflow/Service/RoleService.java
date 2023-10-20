package com.example.oefeningdataflow.Service;

import com.example.oefeningdataflow.DTO.RoleDto;
import com.example.oefeningdataflow.Models.Role;
import com.example.oefeningdataflow.Repository.RoleRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository repos;

    public RoleService(RoleRepository repos) {
        this.repos = repos;
    }

    public List<RoleDto> getRoles() {


        List<RoleDto> roleDtos = new ArrayList<>();

        for (Role r : repos.findAll()) {
            RoleDto rdto = new RoleDto();
            rdto.setRolename(r.getRolename());

            roleDtos.add(rdto);
        }
        return roleDtos;
    }
}