package com.project.datainsight.user.dto;

import com.project.datainsight.user.dao.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    private String id;
    private String username;
    private String password;

    private String regId;
    private String regDate;
    private String modId;
    private String modDate;

    public UserResponse(User user){
        this.id = user.getUserId();
        this.username = user.getUserName();
        this.password = user.getPassword();
    }
}
