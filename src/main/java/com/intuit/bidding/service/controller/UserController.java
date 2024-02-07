package com.intuit.bidding.service.controller;

import com.intuit.bidding.service.DTO.UserInfoResponse;
import com.intuit.bidding.service.entity.AuthRequest;
import com.intuit.bidding.service.entity.UserInfo;
import com.intuit.bidding.service.services.JwtService;
import com.intuit.bidding.service.services.UserInfoService;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Log4j2
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Spring Security tutorials !!";
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo){
        try{
            return userInfoService.register(userInfo);
        } catch (DataIntegrityViolationException e) {
            log.error("Could not register user: " + e);
            throw new ResponseStatusException( HttpStatus.CONFLICT, "Could not register user");
        }
    }
    @PostMapping("/login")
    public String register(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUserName());
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }
    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('ADMIN_ROLES')")
    public List<UserInfo> getAllUsers(){
        return userInfoService.getAllUser();
    }


    @GetMapping("/getUsers/{id}")
    @PreAuthorize("hasAnyAuthority('USER_ROLES','VENDOR_ROLES','ADMIN_ROLES')")
    public UserInfoResponse getAllUsers(@PathVariable Long id){
        UserInfoResponse userInfo = userInfoService.getUser(id);
        if (userInfo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user");
        } else {
            return userInfo;
        }
    }
}
