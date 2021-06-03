package co.com.ias.learning.students.application.services;


import co.com.ias.learning.students.application.commons.NonEmptyString;
import co.com.ias.learning.students.application.domain.Course;
import co.com.ias.learning.students.application.domain.IdentificationNumber;
import co.com.ias.learning.students.application.errors.CourseAlreadyExistsError;
import co.com.ias.learning.students.application.errors.InputDataError;
import co.com.ias.learning.students.application.model.CreateCourseRequest;
import co.com.ias.learning.students.application.model.CreateCourseResponse;
import co.com.ias.learning.students.application.ports.in.CreateCourseUseCase;
import co.com.ias.learning.students.application.ports.out.CourseRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class CreateCourseService implements CreateCourseUseCase {

    private final CourseRepository courseRepository;

    public CreateCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CreateCourseResponse execute(CreateCourseRequest request){
        Course course = ValidateCourse(request);

        IdentificationNumber identificationNumber = course.getIdentificationCourse();
        Optional<Course> courseId = courseRepository.GetCourseById(identificationNumber);

        if(courseId.isPresent()){
            throw new CourseAlreadyExistsError(identificationNumber);
        }

        courseRepository.StoreCourse(course);

        return new CreateCourseResponse(course);
    }

    public Course ValidateCourse(CreateCourseRequest request){
        try{
            NonEmptyString name = new NonEmptyString(request.getName());
            NonEmptyString location = new NonEmptyString(request.getLocation());
            IdentificationNumber identificationCourse = new IdentificationNumber(request.getIdentificationCourse());
            return new Course(
                    name,
                    location,
                    identificationCourse
            );
        }catch (RuntimeException e){
            throw new InputDataError(e.getMessage());
        }
    }

}
