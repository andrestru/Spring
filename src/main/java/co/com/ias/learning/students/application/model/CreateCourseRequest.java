package co.com.ias.learning.students.application.model;

import co.com.ias.learning.students.application.commons.errors.ApplicationError;
import co.com.ias.learning.students.application.commons.operation.ApplicationRequest;

import java.util.Objects;

public class CreateCourseRequest implements ApplicationRequest {
    private String Name;
    private String IdentificationCourse;
    private String Location;

    public CreateCourseRequest() {
    }

    public CreateCourseRequest(String name, String identificationCourse, String location) {
        Name = name;
        IdentificationCourse = identificationCourse;
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public String getIdentificationCourse() {
        return IdentificationCourse;
    }

    public String getLocation() {
        return Location;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setIdentificationCourse(String identificationCourse) {
        IdentificationCourse = identificationCourse;
    }

    public void setLocation(String location) {
        Location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCourseRequest that = (CreateCourseRequest) o;
        return Objects.equals(Name, that.Name) && Objects.equals(IdentificationCourse, that.IdentificationCourse) && Objects.equals(Location, that.Location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, IdentificationCourse, Location);
    }

    @Override
    public String toString() {
        return "CreateCourseRequest{" +
                "Name='" + Name + '\'' +
                ", IdentificationCurse='" + IdentificationCourse + '\'' +
                ", Location='" + Location + '\'' +
                '}';
    }
}
