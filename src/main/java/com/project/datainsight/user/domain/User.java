package com.project.datainsight.user.domain;


import com.project.datainsight.user.dto.UserRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id
    private String userId;
    private String userName;
    private String password;

    private String regId;
    private String regDate;
    private String modId;
    private String modDate;


    public User(UserRequest userRequest) {
        this.userId = userRequest.getId();
        this.userName = userRequest.getUsername();
        this.password = userRequest.getPassword();
    }

    public void updateFromRequest(UserRequest userRequest){
        this.userName = userRequest.getUsername();
    }

}
