package com.ticxar.FeingClient.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "login_log")
@Data
@Builder
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @Column(name= "username")
    private String userName;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name= "login_time")
    private Date loginTime;

    @Column(name = "access_token", length = 1000)
    private String accessToken;

    @Column(name = "refresh_token", length = 1000)
    private String  refreshToken;

    @PrePersist
    protected void onCreate() {
        loginTime = new Date();
    }

}
