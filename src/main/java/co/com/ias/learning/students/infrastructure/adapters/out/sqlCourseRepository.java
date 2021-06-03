package co.com.ias.learning.students.infrastructure.adapters.out;

import co.com.ias.learning.students.application.commons.NonEmptyString;
import co.com.ias.learning.students.application.domain.Course;
import co.com.ias.learning.students.application.domain.IdentificationNumber;
import co.com.ias.learning.students.application.ports.out.CourseRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository
public class sqlCourseRepository implements CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    public sqlCourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Course> rowMapper = (resultSet, i) -> ResultSet(resultSet);

    private static Course ResultSet(ResultSet rs) throws SQLException {
        return new Course(
                new NonEmptyString(rs.getString("Name")),
                new NonEmptyString(rs.getString("Location")),
                new IdentificationNumber(rs.getString("Id_Number"))
        );
    }


    @Override
    public Optional<Course> GetCourseById(IdentificationNumber IdentificationNumber) {
        final String sql = "SELECT * FROM Course where Id_Number = ?";
        final PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, IdentificationNumber.getValue());
        };
        ResultSetExtractor<Optional<Course>> resultSetExtractor = resultSet -> {
            if (resultSet.next()){
                final Course course = ResultSet(resultSet);
                return Optional.of(course);
            }else{
                return Optional.empty();
            }
        };
        return jdbcTemplate.query(sql, preparedStatementSetter, resultSetExtractor);
    }

    @Override
    public void StoreCourse(Course course) {
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Course(Id_Number, Name, Location)VALUES(?,?,?)"
            );
            preparedStatement.setString(1, course.getIdentificationCourse().getValue());
            preparedStatement.setString(2, course.getName().getValue());
            preparedStatement.setString(3, course.getLocation().getValue());

            return preparedStatement;
        });
    }

    @Override
    public Collection<Course> ListCourse() {
        final String sql = "SELECT * FROM Course";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
