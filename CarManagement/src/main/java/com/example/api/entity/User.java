package com.example.api.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String role; // Gồm 3 loại người dùng: CUSTOMER, STAFF, ADMIN
    private String fullName;
    private String phoneNumber;
    private String identityCardNumber; //CCCD
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private List<CarProfile> carProfiles;

    @OneToMany(mappedBy = "user")
    private List<Schedule> schedules;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters for fields...

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public List<CarProfile> getCarProfiles() {
        return carProfiles;
    }

    public void setCarProfiles(List<CarProfile> carProfiles) {
        this.carProfiles = carProfiles;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

	/* Kiểm tra xem tài khoản của người dùng có bị hết hạn hay không. */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
	/* Kiểm tra xem tài khoản của người dùng có bị khóa hay không. */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

	/*
	 * Kiểm tra xem thông tin xác thực (thường là mật khẩu) của người dùng có bị hết
	 * hạn hay không.
	 */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    
	/* Kiểm tra xem tài khoản của người dùng có được kích hoạt hay không. */
    @Override
    public boolean isEnabled() {
        return true;
    }
}