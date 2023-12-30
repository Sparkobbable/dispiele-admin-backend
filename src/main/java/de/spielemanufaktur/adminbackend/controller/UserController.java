package de.spielemanufaktur.adminbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.spielemanufaktur.adminbackend.model.User;

@RestController
@RequestMapping("/admin")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<String> getMyEmail() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user.getEmail());
    }
}
