package com.intuit.bidding.service.services;

import com.intuit.bidding.service.DTO.UserInfoResponse;
import com.intuit.bidding.service.entity.UserInfo;
import com.intuit.bidding.service.repository.UserInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
        if (userInfo.isEmpty()) {
            return null;
        }
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"+username));
    }
    public String register(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User added successfully";
    }
    public List<UserInfo> getAllUser(){
         return userInfoRepository.findAll();
    }
    public UserInfoResponse getUser(Integer id){
        if (id == null) {
            return null;
        }
        UserInfo userInfo =  userInfoRepository.findById(id).orElse(null);
        return UserInfoResponse.createFromUserInfo(userInfo);
    }
}
