package br.comvarejonline.projetoinicial.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @PostMapping("sign-in")
    public ResponseEntity<Object> login(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(authentication.getAuthorities());
    }


}
