package co.com.ias.learning.students.application.model;

import co.com.ias.learning.students.application.commons.operation.ApplicationResponse;
import co.com.ias.learning.students.application.domain.Course;

public class CreateCourseResponse implements ApplicationResponse {

    private final Course course;


    public CreateCourseResponse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "CreateCourseResponse{" +
                "course=" + course +
                '}';
    }
}
