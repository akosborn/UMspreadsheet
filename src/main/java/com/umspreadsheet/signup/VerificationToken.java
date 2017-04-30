package com.umspreadsheet.signup;

import com.umspreadsheet.user.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
public class VerificationToken
{
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expirationDate;

    public VerificationToken(String token, User user)
    {
        this.token = token;
        this.user = user;
        this.expirationDate = calculateExpirationDate(EXPIRATION);
    }

    private Date calculateExpirationDate(int expirationTimeInMinutes)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, expirationTimeInMinutes);

        return new Date(calendar.getTime().getTime());
    }

    public VerificationToken(){}

    public static int getEXPIRATION()
    {
        return EXPIRATION;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Date getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate)
    {
        this.expirationDate = expirationDate;
    }
}
