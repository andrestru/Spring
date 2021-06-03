package co.com.ias.learning.students.application.domain;

import co.com.ias.learning.students.application.commons.NonEmptyString;
import co.com.ias.learning.students.application.commons.Validate;

public class Course {
    private final NonEmptyString Name;
    private final NonEmptyString Location;
    private final IdentificationNumber IdentificationCourse;

    public Course(NonEmptyString name, NonEmptyString location, IdentificationNumber identificationCourse) {
        Validate.notNull(name, "Course Name can not be null");
        Validate.notNull(location, "Course Location can not be null");
        Validate.notNull(identificationCourse, "Course Identification can not be null");
        Name = name;
        Location = location;
        IdentificationCourse = identificationCourse;
    }

    public NonEmptyString getName() {
        return Name;
    }

    public NonEmptyString getLocation() {
        return Location;
    }

    public IdentificationNumber getIdentificationCourse() {
        return IdentificationCourse;
    }
}
