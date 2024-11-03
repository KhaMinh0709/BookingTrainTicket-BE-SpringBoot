package com.DevJavaMinh.service.imp;

import com.DevJavaMinh.dto.UserDto;
import com.DevJavaMinh.exception.NotFoundException;
import com.DevJavaMinh.mapper.UserMapping;
import com.DevJavaMinh.model.User;
import com.DevJavaMinh.model.Role;
import com.DevJavaMinh.repository.UserRepository;
import com.DevJavaMinh.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapping.mapToUser(userDto);
        User userSave = userRepository.save(user);
        return UserMapping.mapToUserDto(user);
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));

        user.setUser_id(userDto.getUser_id());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRole(userDto.getRole());
        userRepository.save(user);
        return UserMapping.mapToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
        return UserMapping.mapToUserDto(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();

        return users.stream().map(UserMapping::mapToUserDto).toList();
    }

    @Override
    public List<UserDto> getUserByRole(Role role) {
        List<User> list = userRepository.findByRole(role);
        return list.stream().map(UserMapping::mapToUserDto).toList();
    }
}
