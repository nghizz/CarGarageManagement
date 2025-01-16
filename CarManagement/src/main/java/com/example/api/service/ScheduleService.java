package com.example.api.service;

import com.example.api.entity.Schedule;
import com.example.api.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    // Lấy danh sách tất cả lịch trình
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    // Lấy lịch trình theo ID
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    // Tạo lịch trình mới
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // Cập nhật thông tin lịch trình
    public Schedule updateSchedule(Long id, Schedule updatedSchedule) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        if (schedule != null) {
            // Cập nhật các trường
            schedule.setServiceDate(updatedSchedule.getServiceDate());
            schedule.setStatus(updatedSchedule.getStatus());
            // ...
            return scheduleRepository.save(schedule);
        } else {
            return null;
        }
    }

    // Xóa lịch trình
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}