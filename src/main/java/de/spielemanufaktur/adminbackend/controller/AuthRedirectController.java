package de.spielemanufaktur.adminbackend.controller;

import java.net.URI;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Profile("local")
public class AuthRedirectController {
    @GetMapping("/oauth/authorize")
    public ResponseEntity<Void> redirectAuth(@RequestParam String client_id, @RequestParam String response_type,
            @RequestParam String scope, @RequestParam String redirect_uri) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(String.format(
                "http://localhost:9000/oauth/authorize?client_id=%s&response_type=%s&scope=%s&redirect_uri=%s",
                client_id, response_type, scope, redirect_uri)));
        return new ResponseEntity<>(headers, HttpStatus.TEMPORARY_REDIRECT);
    }
}
