package com.chatbot.deltour.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chatbot.deltour.security.AccountContext;
import com.chatbot.deltour.security.InvaildJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtDecoder {

    private static final Logger log = LoggerFactory.getLogger(JwtDecoder.class);

    public AccountContext decodeJwt(String token) {

        DecodedJWT decodedJWT = isVaildToken(token).orElseThrow(() -> new InvaildJwtException("유효한 토큰이 없습니다."));

        String email = decodedJWT.getClaim("EMAIL").asString();
        String role = decodedJWT.getClaim("USER_ROLE").asString();

        return new AccountContext(email, "1234", role);
    }

    private Optional<DecodedJWT> isVaildToken(String token) {

        DecodedJWT jwt = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("jwttest");
            JWTVerifier verifier = JWT.require(algorithm).build();

            jwt = verifier.verify(token);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return Optional.ofNullable(jwt);
    }
}
