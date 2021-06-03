package co.com.ias.learning.students.application.model;

import co.com.ias.learning.students.application.commons.operation.ApplicationResponse;
import co.com.ias.learning.students.application.domain.Course;

import java.util.Collection;

public class ListCourseResponse implements ApplicationResponse {
    private final Collection<Course> items_course;

    public ListCourseResponse(Collection<Course> items_course) {
        this.items_course = items_course;
    }

    public Collection<Course> getItems_course() {
        return items_course;
    }
}
