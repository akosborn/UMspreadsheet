package com.umspreadsheet.signin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long>
{
    PasswordResetToken findByToken(String token);
}
