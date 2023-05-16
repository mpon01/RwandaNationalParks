package com.mer.RWANDAPARKS.model;

import javax.persistence.*;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "fullname", unique = true, nullable = false, length = 40)
    private String fullname;

    @Column(name = "email", unique = true, nullable = false, length = 40)
    private String email;

    @Column(name = "password", unique = true, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @Transient
//    private MultipartFile profilePhoto;
//    @Lob
//    @Column(name = "photo", columnDefinition = "mediumblob")
//    private byte[] photo;
//
//    @Column(name = "fileName")
//    private String fileName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Visit> visits = new ArrayList<>();

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate createdAt;

    @Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate updatedAt;

    public User(String fullname, String email, String password, Role role) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
