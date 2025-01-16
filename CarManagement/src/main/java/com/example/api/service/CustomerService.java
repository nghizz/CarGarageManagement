package com.example.api.service;

import com.example.api.entity.User;
import com.example.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private UserRepository userRepository;

    // Lấy danh sách tất cả khách hàng
    public List<User> getAllCustomers() {
        return userRepository.findAll();
    }

    // Lấy khách hàng theo ID
    public User getCustomerById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Tạo khách hàng mới
    public User createCustomer(User user) {
        return userRepository.save(user);
    }

    // Cập nhật thông tin khách hàng
    public User updateCustomer(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            // Cập nhật các trường
            user.setFullName(updatedUser.getFullName());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            // ...
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    // Xóa khách hàng
    public void deleteCustomer(Long id) {
        userRepository.deleteById(id);
    }
}