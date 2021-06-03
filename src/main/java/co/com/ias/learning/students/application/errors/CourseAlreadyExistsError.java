package co.com.ias.learning.students.application.errors;

import co.com.ias.learning.students.application.commons.errors.ApplicationError;
import co.com.ias.learning.students.application.commons.errors.HttpStatusCode;
import co.com.ias.learning.students.application.domain.IdentificationNumber;
import java.util.Map;

public class CourseAlreadyExistsError extends ApplicationError {

    private final IdentificationNumber idNumber;

    public CourseAlreadyExistsError(IdentificationNumber idNumber) {
        this.idNumber = idNumber;
    }

    public IdentificationNumber getIdNumber() {
        return idNumber;
    }


    @Override
    public String getMessage() {
        return "The course with Id number: " + idNumber.getValue() + " Already exists.";
    }

    @Override
    public String errorCode() {
        return "COURSE_ALREADY_EXITS_ERROR";
    }

    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.BAD_REQUEST;
    }

    @Override
    public Map<String, Object> metadata() {
        return Map.of(
                "IdCourse:", idNumber
        );
    }
}
