package com.birdhss.birdhss.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    void deleteCourseById(int id);
    Optional<Course> findById(int id);
}
