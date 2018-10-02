package com.umspreadsheet.v1.signup;

import com.umspreadsheet.v1.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long>
{
    VerificationToken findByToken(String verificationToken);

    VerificationToken findByUser(User user);
}
