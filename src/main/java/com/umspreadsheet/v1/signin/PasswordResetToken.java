package com.umspreadsheet.v1.signin;

import com.umspreadsheet.v1.user.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class PasswordResetToken
{
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expirationDate;

    public PasswordResetToken()
    {
        super();
    }

    public PasswordResetToken(final String token)
    {
        super();

        this.token = token;
        this.expirationDate = calculateExpirationDate(EXPIRATION);
    }

    public PasswordResetToken(final String token, final User user)
    {
        super();

        this.token = token;
        this.user = user;
        this.expirationDate = calculateExpirationDate(EXPIRATION);
    }

    private Date calculateExpirationDate(final int expirationTimeInMinutes)
    {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expirationTimeInMinutes);

        return new Date(cal.getTime().getTime());
    }

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
