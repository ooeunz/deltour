package com.chatbot.deltour.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
public class JwtFactory {

    private static String signingKey = "jwttest";

    private static final Logger log = LoggerFactory.getLogger(JwtFactory.class);

    public String generateToken(AccountContext context) {

        String token = null;

        try {
            token = JWT.create()
                    .withIssuer("ooeunz")
                    .withClaim("EMAIL", context.getAccount().getEmail())
                    .withClaim("USER_ROLE", context.getAccount().getUserRole().getRoleName())
                    .sign(generateAlgorithm());

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return token;
    }

    private Algorithm generateAlgorithm() throws UnsupportedEncodingException {
        return Algorithm.HMAC256(signingKey);
    }
}
