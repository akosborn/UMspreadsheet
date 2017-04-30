package com.umspreadsheet.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService
{
    private PrivilegeRepository privilegeRepository;

    @Autowired
    public PrivilegeService(PrivilegeRepository privilegeRepository)
    {
        this.privilegeRepository = privilegeRepository;
    }

    public Privilege findByName(String name)
    {
        return privilegeRepository.findByName(name);
    }

    public Privilege save(Privilege privilege)
    {
        return privilegeRepository.save(privilege);
    }
}
