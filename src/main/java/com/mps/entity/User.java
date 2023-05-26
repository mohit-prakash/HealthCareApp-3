package com.mps.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_tab")
public class User {
    @Id
    @Column(name = "user_id_col")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "username_col")
    private String username;
    @Column(name = "password_col")
    private String password;
    @Column(name = "role_col")
    private String role;
    @Column(name = "display_name_col")
    private String displayName;
}
