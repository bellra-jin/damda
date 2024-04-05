package com.team_damda.domain.entity;

import com.team_damda.domain.enums.LoginType;
import com.team_damda.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="user_email", nullable = false, unique = true, length = 30)
    private String userEmail;

    @Column(name="password", nullable = true)
    private String password;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="phone", nullable = false, length = 20)
    private String phone;

    @Column(name="sns_ny", length = 1)
    private String snsNy;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name="login_type")
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    private String refreshToken;


    @OneToMany(mappedBy = "manager")
    private List<Class> classes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ClassLike> classLikes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Announce> announces = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Inquiry> inquiries = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ClassReservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ClassReview> reviews = new ArrayList<>();


    public void authorizeUser() {
        this.role = Role.USER;
    }

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

}
