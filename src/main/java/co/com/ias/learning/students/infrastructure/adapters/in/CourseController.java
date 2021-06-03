package co.com.ias.learning.students.infrastructure.adapters.in;


import co.com.ias.learning.students.application.model.CreateCourseRequest;
import co.com.ias.learning.students.application.model.ListCourseRequest;
import co.com.ias.learning.students.application.ports.in.CreateCourseUseCase;
import co.com.ias.learning.students.application.ports.in.ListCourseUseCase;
import co.com.ias.learning.students.infrastructure.commons.UseCaseHttpExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Course")
public class CourseController {

    private final UseCaseHttpExecutor useCaseHttpExecutor;
    private final CreateCourseUseCase createCourseUseCase;
    private final ListCourseUseCase listCourseUseCase;


    public CourseController(UseCaseHttpExecutor useCaseHttpExecutor, CreateCourseUseCase createCourseUseCase, ListCourseUseCase listCourseUseCase) {
        this.useCaseHttpExecutor = useCaseHttpExecutor;
        this.createCourseUseCase = createCourseUseCase;
        this.listCourseUseCase = listCourseUseCase;
    }

    @PostMapping
    public ResponseEntity Create(
            @RequestBody CreateCourseRequest request
            ){
        return useCaseHttpExecutor.executeUseCase(
                createCourseUseCase,
                request
        );
    }

    @GetMapping
    public ResponseEntity ListCourseHandler() {
        return  useCaseHttpExecutor.executeUseCase(
                listCourseUseCase,
                new ListCourseRequest()
        );
    }
}
