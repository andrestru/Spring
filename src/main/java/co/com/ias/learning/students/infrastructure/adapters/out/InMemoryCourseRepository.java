package co.com.ias.learning.students.infrastructure.adapters.out;


import co.com.ias.learning.students.application.domain.Course;
import co.com.ias.learning.students.application.domain.IdentificationNumber;
import co.com.ias.learning.students.application.ports.out.CourseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class InMemoryCourseRepository implements CourseRepository {

    private final Map<IdentificationNumber, Course> databasecourse = new HashMap<>();



    @Override
    public Optional<Course> GetCourseById(IdentificationNumber IdentificationCourse) {
        Course course = databasecourse.get(IdentificationCourse);
        return Optional.ofNullable(course);
    }

    @Override
    public void StoreCourse(Course course) {
        databasecourse.put(course.getIdentificationCourse(), course);
    }

    @Override
    public Collection<Course> ListCourse() {
        return databasecourse.values();
    }
}
