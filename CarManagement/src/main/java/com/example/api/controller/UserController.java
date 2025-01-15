package com.example.api.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.DTO.SuccessReponse;
import com.example.api.DTO.FailResponse;
import com.example.api.DTO.LoginRequest;
import com.example.api.entity.User;
import com.example.api.security.JwtTokenProvider;
import com.example.api.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	// Đăng ký người dùng mới
	@PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) { 
        try {
            // Kiểm tra xem username đã tồn tại chưa
            if (userService.existsByUsername(user.getUsername())) {
                return ResponseEntity.badRequest()
                        .body(new FailResponse("fail", "Tên đăng nhập đã tồn tại!"));
            }

            // Tạo một đối tượng User mới với các trường bắt buộc
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            newUser.setFullName(user.getFullName());
            newUser.setPhoneNumber(user.getPhoneNumber());
            newUser.setRole(user.getRole());
            LocalDateTime now = LocalDateTime.now();
            newUser.setCreatedAt(now);
            newUser.setUpdatedAt(now);

            userService.createUser(newUser); // Lưu người dùng mới vào database

            return ResponseEntity.ok(new FailResponse("Success", "Đăng ký thành công!")); 

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new FailResponse("fail", "Lỗi trong quá trình đăng ký!"));
        }
    }

	// Đăng nhập và trả về JWT token
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
	    try {
	        // Xác thực người dùng với username và password
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );
	        // Đặt Authentication vào SecurityContext
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        String token = jwtTokenProvider.generateToken(userDetails);
	        String role = userDetails.getAuthorities().stream()
	                .findFirst()
	                .map(GrantedAuthority::getAuthority)
	                .orElse("CUSTOMER");
	        SuccessReponse authResponse = new SuccessReponse(
                    "Success",
                    "Đăng nhập thành công!", 
                    userDetails.getUsername(),
                    role,
                    token
            );
            return ResponseEntity.ok(authResponse);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new FailResponse("Fail", "Tên đăng nhập hoặc mật khẩu không đúng")); 
        }
    }

}
