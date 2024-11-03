package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.UserDto;
import com.DevJavaMinh.model.User;

public class UserMapping {
    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getUser_id(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole(),
                userDto.getPhoneNumber(),
                userDto.getEmail()
        );
    }
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUser_id(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getPhoneNumber(),
                user.getEmail()
        );
    }
}
