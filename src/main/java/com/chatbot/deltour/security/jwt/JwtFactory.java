package com.chatbot.deltour.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.chatbot.deltour.security.AccountContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

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
                    .withClaim("USERNAME", context.getAccount().getUsername())
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
