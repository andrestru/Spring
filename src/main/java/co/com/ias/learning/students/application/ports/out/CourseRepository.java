package co.com.ias.learning.students.application.ports.out;

import co.com.ias.learning.students.application.domain.Course;
import co.com.ias.learning.students.application.domain.IdentificationNumber;

import java.util.Collection;
import java.util.Optional;

public interface CourseRepository {

    Optional<Course> GetCourseById(IdentificationNumber IdentificationNumber);

    void StoreCourse(Course course);

    Collection<Course> ListCourse();

}
