package com.birdhss.birdhss.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "/api/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    private DeptCode splitDeptCode(String deptCode) {
        // Store alphabetic characters in dept, numeric in code
        Pattern pattern = Pattern.compile("^([A-Za-z]*)(\\d*)$");
        Matcher matcher = pattern.matcher(deptCode);
        if (matcher.matches()) {
            String dept = matcher.group(1).isEmpty() ? null : matcher.group(1);
            int code = matcher.group(2).isEmpty() ? -1 : Integer.parseInt(matcher.group(2));
            return new DeptCode(dept, code);
        }
        return null;
    }

    private record DeptCode(String dept, int code) {
    }

    @GetMapping
    public List<Course> getCourses(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String deptCode,
            @RequestParam(required = false) String name
    ) {

        if (id != null && !id.isEmpty()) {
            Course course = courseService.getCourseById(Integer.parseInt(id));
            return Collections.singletonList(course);
        }
        if (deptCode != null && !deptCode.isEmpty()) {
            DeptCode parsedDeptCode = splitDeptCode(deptCode);
            if (parsedDeptCode != null) {
                if (parsedDeptCode.dept() != null && parsedDeptCode.code() != -1) {
                    return courseService.getCoursesByDeptAndCode(
                            parsedDeptCode.dept(),
                            parsedDeptCode.code()
                    );
                } else if (parsedDeptCode.dept() != null) {
                    return courseService.getCoursesByDept(parsedDeptCode.dept());
                } else if (parsedDeptCode.code() != -1) {
                    return courseService.getCoursesByCode(parsedDeptCode.code());
                }
            }
        }
        if (name != null && !name.isEmpty()) {
            return courseService.getCoursesByName(name);
        }
        return courseService.getAllCourses();
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course resultingCourse = courseService.addCourse(course);
        return new ResponseEntity<>(resultingCourse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
        Course resultingCourse = courseService.updateCourse(course);
        if (resultingCourse != null) {
            return new ResponseEntity<>(resultingCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable String id) {
        if (id != null && !id.isEmpty()) {
            courseService.deleteCourse(Integer.parseInt(id));
            return new ResponseEntity<>("Course id " + id + " deleted successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Course id " + id + " not found.", HttpStatus.NOT_FOUND);
    }
}
