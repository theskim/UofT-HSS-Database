package com.birdhss.birdhss.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) {
        return courseRepository.findAll().stream()
                .filter(course -> id == course.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Course with ID " + id + " not found."));
    }

    public List<Course> getCoursesByDept(String dept) {
        return courseRepository.findAll().stream()
                .filter(course -> dept.toLowerCase().contains(course.getDept().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesByCode(int code) {
        return courseRepository.findAll().stream()
                .filter(course -> code == course.getCode())
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesByDeptAndCode(String dept, int code) {
        return courseRepository.findAll().stream()
                .filter(course ->
                        dept.toLowerCase().contains(course.getDept().toLowerCase()) && code == course.getCode())
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesByCode(String campus) {
        return courseRepository.findAll().stream()
                .filter(course -> course.getCampus().toLowerCase().contains(campus.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesByName(String name) {
        return courseRepository.findAll().stream()
                .filter(course -> course.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    private static final List<String> COURSE_AVERAGES = Arrays.asList(
            "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F", "N"
    );

    public List<Course> getCoursesAboveAverage(String cavg) {
        int index = COURSE_AVERAGES.indexOf(cavg);
        if (index == -1) {
            throw new IllegalArgumentException("Invalid average value: " + cavg);
        }

        return courseRepository.findAll().stream()
                .filter(course -> {
                    int courseIndex = COURSE_AVERAGES.indexOf(course.getAvg());
                    return courseIndex <= index;
                })
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesByType(String ctype) {
        return courseRepository.findAll().stream()
                .filter(course -> course.getType().equals(ctype))
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesOfferedInSummer() {
        return courseRepository.findAll().stream()
                .filter(Course::isOfferedInSummer)
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesOfferedInFall() {
        return courseRepository.findAll().stream()
                .filter(Course::isOfferedInFall)
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesOfferedInWinter() {
        return courseRepository.findAll().stream()
                .filter(Course::isOfferedInWinter)
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesOfferedInSummerAndFall() {
        return courseRepository.findAll().stream()
                .filter(course -> course.isOfferedInSummer() && course.isOfferedInFall())
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesOfferedInSummerAndWinter() {
        return courseRepository.findAll().stream()
                .filter(course -> course.isOfferedInSummer() && course.isOfferedInWinter())
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesOfferedInFallAndWinter() {
        return courseRepository.findAll().stream()
                .filter(course -> course.isOfferedInFall() && course.isOfferedInWinter())
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesOfferedInAll() {
        return courseRepository.findAll().stream()
                .filter(course ->
                        course.isOfferedInSummer() && course.isOfferedInFall()
                                && course.isOfferedInWinter())
                .collect(Collectors.toList());
    }

    public Course addCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    public Course updateCourse(Course course) {
        Optional<Course> existingCourse =
                courseRepository.findById(course.getId());
        if (existingCourse.isPresent()){
            Course courseToUpdate = existingCourse.get();
            courseToUpdate.setDept(course.getDept());
            courseToUpdate.setCode(course.getCode());
            courseToUpdate.setCampus(course.getCampus());
            courseToUpdate.setName(course.getName());
            courseToUpdate.setUrl(course.getUrl());
            courseToUpdate.setWorkload(course.getWorkload());
            courseToUpdate.setAvg(course.getAvg());
            courseToUpdate.setType(course.getType());
            courseToUpdate.setSummer(course.isOfferedInSummer());
            courseToUpdate.setFall(course.isOfferedInFall());
            courseToUpdate.setWinter(course.isOfferedInWinter());
            courseRepository.save(courseToUpdate);
            return course;
        }
        return null;
    }

    public void deleteCourse(int id) {
        Course course = getCourseById(id);
        if (course != null) {
            courseRepository.delete(course);
        }
    }
}
