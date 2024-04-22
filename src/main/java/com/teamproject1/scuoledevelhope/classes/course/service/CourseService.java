package com.teamproject1.scuoledevelhope.classes.course.service;

import com.teamproject1.scuoledevelhope.classes.course.Course;
import com.teamproject1.scuoledevelhope.classes.course.repo.CourseDAO;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseElement;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseList;
import com.teamproject1.scuoledevelhope.types.errors.SQLException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    private final CourseDAO courseDAO;

    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public BaseResponseList<Course> findAll() {
        return new BaseResponseList<>(courseDAO.findAll());
    }

    public BaseResponseElement<Course> findById(Long id) {
        Optional<Course> result = courseDAO.findById(id);
        if (result.isEmpty()) {
            throw new SQLException("Course was not present");
        }
        return new BaseResponseElement<>(result.get());
    }

    public BaseResponseElement<Course> save(Course course) {
        return new BaseResponseElement<>(courseDAO.save(course));
    }

    public BaseResponseElement<Course> deleteById(Long id) {
        Optional<Course> temp = courseDAO.findById(id);

        if (temp.isEmpty()) {
            throw new SQLException("Course was not present");
        }
        courseDAO.deleteById(id);

        return new BaseResponseElement<>(temp.get());
    }
}
