package com.DevJavaMinh.service.imp;

import com.DevJavaMinh.dto.UserDto;
import com.DevJavaMinh.model.User;
import com.DevJavaMinh.repository.UserRepository;
import com.DevJavaMinh.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public String login(UserDto userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("người dùng không tồn tại!"));

        // Kiểm tra mật khẩu
        if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            return "Đăng nhập thành công!";
        } else {
            throw new RuntimeException("Mật khẩu không chính xác!");
        }
    }
}
