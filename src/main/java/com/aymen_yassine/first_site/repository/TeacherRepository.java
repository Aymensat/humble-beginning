package com.aymen_yassine.first_site.repository;

import com.aymen_yassine.first_site.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    void findAllById(Integer teacherId);

    Optional<Teacher> findByEmail(String username);
}
