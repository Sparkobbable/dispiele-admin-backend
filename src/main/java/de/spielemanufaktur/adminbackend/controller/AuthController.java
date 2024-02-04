package de.spielemanufaktur.adminbackend.controller;

import java.net.URI;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.spielemanufaktur.adminbackend.controller.dtos.AccessTokenDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class AuthController {
    @Value("${oauth.admin-site.clientsecret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String authUrl;

    private static final String CLIENT_ID = "dispiele_admin_site";

    @GetMapping("/authproxy/token")
    public ResponseEntity<AccessTokenDTO> getAccessToken(
            @RequestParam String code, @RequestParam String redirect_uri) {
        log.info("Get Accesstoken for code {}", code);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(CLIENT_ID, clientSecret);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        return new RestTemplate().exchange(String.format(authUrl + "/oauth/token?grant_type=%s&code=%s&redirect_uri=%s",
                "authorization_code", code, redirect_uri), HttpMethod.POST, entity, AccessTokenDTO.class);
    }
}
