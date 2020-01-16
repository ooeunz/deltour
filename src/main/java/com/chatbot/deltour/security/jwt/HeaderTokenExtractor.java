package com.chatbot.deltour.security.jwt;

import com.chatbot.deltour.security.InvaildJwtException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class HeaderTokenExtractor {

    public static final String HEADER_PREFIX = "Bearer ";

    public String extract(String header) {

        if (StringUtils.isEmpty(header) | header.length() < HEADER_PREFIX.length()) {
            throw new InvaildJwtException("올바른 토큰 정보가 아닙니다!");
        }

        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}
