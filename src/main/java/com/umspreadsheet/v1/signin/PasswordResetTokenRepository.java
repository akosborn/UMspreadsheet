package com.umspreadsheet.v1.signin;

import com.umspreadsheet.v1.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long>
{
    PasswordResetToken findByToken(String token);

    @Transactional
    void deleteByUser(User user);

    PasswordResetToken findByUser(User user);
}
