package com.umspreadsheet.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserConnection", uniqueConstraints = {
        @UniqueConstraint(name = "UserConnectionRank", columnNames = { "userId", "providerId", "rank" }),
        @UniqueConstraint(name = "providerUser", columnNames = { "providerId", "providerUserId" }) })
public class UserConnection implements Serializable
{
    @Id
    @Column(name = "userId")
    private String userId;

    @Id
    @Column(name = "providerId")
    private String providerId;

    @Id
    @Column(name = "providerUserId")
    private String providerUserId;

    @Column(name = "rank", nullable = false)
    private int rank;

    @Column(name = "displayName")
    private String displayName;

    @Column(name = "profileUrl", length = 512)
    private String profileUrl;

    @Column(name = "imageUrl", length = 512)
    private String imageUrl;

    @Column(name = "accessToken", length = 512, nullable = false)
    private String accessToken;

    @Column(name = "secret", length = 512)
    private String secret;

    @Column(name = "refreshToken", length = 512)
    private String refreshToken;

    @Column(name = "EXPIRETIME", columnDefinition = "BIGINT")
    private Long expireTime;
}

