package com.chatbot.deltour.security;

import com.chatbot.deltour.domain.Account.Account;
import com.chatbot.deltour.domain.Account.UserRole;
import com.chatbot.deltour.security.tokens.JwtPostProcessingToken;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AccountContext extends User {

    private Account account;

    private AccountContext(Account account, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.account = account;
    }

    public AccountContext(String email, String password, String role) {
        super(email, password, parseAuthorities(role));
    }

    public static AccountContext fromAccountModel(Account account) {
        return new AccountContext(account, account.getEmail(), account.getPassword(), parseAuthorities(account.getUserRole()));
    }

    public static AccountContext fromJwtPostToken(JwtPostProcessingToken token) {
        return new AccountContext(null, token.getEmail(), token.getPassword(), token.getAuthorities());
    }

    public static List<SimpleGrantedAuthority> parseAuthorities(UserRole role) {
        return Arrays.asList(role).stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(String role) {
        return parseAuthorities(UserRole.getRoleByName(role));
    }
}
