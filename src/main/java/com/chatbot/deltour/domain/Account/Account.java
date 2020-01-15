package com.chatbot.deltour.domain.Account;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column
    private String socialId;

    @Column
    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    public Account testBuilder(String email, String username, String password) {
        return Account.builder()
                .email(email)
                .username(username)
                .password(password)
                .userRole((UserRole.USER))
                .build();
    }
}