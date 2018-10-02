package com.umspreadsheet.v1.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByEmail(String email);

    User findByUsername(String username);

    User findByUserId(String id);
}
