package com.umspreadsheet.signup;

import com.umspreadsheet.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long>
{
    VerificationToken findByToken(String verificationToken);

    VerificationToken findByUser(User user);
}
