package com.DevJavaMinh.service;

import com.DevJavaMinh.dto.UserDto;
import com.DevJavaMinh.model.Role;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUserById(Long id, UserDto userDto);
    UserDto getUserById(Long id);
    void deleteUserById(Long id);
    List<UserDto> getAllUser();
    List<UserDto> getUserByRole(Role role);
}
