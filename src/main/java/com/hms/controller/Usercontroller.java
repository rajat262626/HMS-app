package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.payload.LoginDto;
import com.hms.payload.TokenDto;
import com.hms.repository.AppUserRepository;
import com.hms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class Usercontroller {

    private AppUserRepository appUserRepository;
    private UserService userService;

    public Usercontroller(AppUserRepository appUserRepository, UserService userService) {
        this.appUserRepository = appUserRepository;
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> createUser(@RequestBody AppUser user)
    {
        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent())
        {
            return new ResponseEntity<>("UserName already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent())
        {
            return new ResponseEntity<>("Email already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword(encryptedPassword);
        user.setRole("ROLE_USER");
        appUserRepository.save(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }


    //http://localhost:8080/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto)
    {
        String token = userService.verifyLogin(dto);
        if (token!=null)
        {
            TokenDto tokenDto = new TokenDto();
             tokenDto.setToken(token);
             tokenDto.setType("JWT");
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid username/password", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/signUp-property-owner")
    public ResponseEntity<?> createPropertyOwnerUser(@RequestBody AppUser user)
    {
        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent())
        {
            return new ResponseEntity<>("UserName already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent())
        {
            return new ResponseEntity<>("Email already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword(encryptedPassword);
        user.setRole("ROLE_OWNER");
        appUserRepository.save(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
}
