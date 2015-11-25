package org.azure.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by adil on 25/11/15.
 */
@Entity
@Table(name = "users")
public class User {
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "password_salt")
    private String passwordSalt;
    @Column(name = "created_at")
    LocalDateTime createdAt;


}
