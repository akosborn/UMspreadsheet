package com.umspreadsheet.repository;

import com.umspreadsheet.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByEmail(String email);
}
