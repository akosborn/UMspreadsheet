package com.umspreadsheet.v1.privilege;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long>
{
    Privilege findByName(String name);
}
