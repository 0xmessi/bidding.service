package com.intuit.bidding.service.DTO;

import com.intuit.bidding.service.entity.UserInfo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponse {
    private Integer id;
    private String name;
    private String email;
    private String roles;

    public static UserInfoResponse createFromUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            return null;
        }
        return UserInfoResponse.builder()
            .id(userInfo.getId())
            .name(userInfo.getName())
            .email(userInfo.getEmail())
            .build();
    }
}
