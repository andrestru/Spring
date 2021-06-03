package co.com.ias.learning.students.infrastructure.configuration;

import co.com.ias.learning.students.application.ports.out.CourseRepository;
import co.com.ias.learning.students.application.services.CreateCourseService;
import co.com.ias.learning.students.application.services.ListCourseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseApplicationConfiguration {

    @Bean
    public CreateCourseService CreateCourseServiceBean(CourseRepository courseRepository){
        return new CreateCourseService(courseRepository);
    }

    @Bean
    public ListCourseService ListCourseServiceBean(CourseRepository courseRepository){
        return new ListCourseService(courseRepository);
    }

}
