package com.DevJavaMinh.dto;

import com.DevJavaMinh.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long user_id;

    private String username;

    private String password;

    private Role role;

    private String email;

    private String phoneNumber;

}
