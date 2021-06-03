package co.com.ias.learning.students.application.services;

import co.com.ias.learning.students.application.domain.Course;
import co.com.ias.learning.students.application.model.ListCourseRequest;
import co.com.ias.learning.students.application.model.ListCourseResponse;
import co.com.ias.learning.students.application.ports.in.ListCourseUseCase;
import co.com.ias.learning.students.application.ports.out.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;


public class ListCourseService implements ListCourseUseCase {

    private final CourseRepository courseRepository;

    public ListCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public ListCourseResponse execute(ListCourseRequest request) {
        Collection<Course> course = courseRepository.ListCourse();
        return new ListCourseResponse(course);
    }
}
