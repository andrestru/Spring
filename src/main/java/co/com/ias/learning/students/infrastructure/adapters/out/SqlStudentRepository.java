package co.com.ias.learning.students.infrastructure.adapters.out;


import co.com.ias.learning.students.application.commons.NonEmptyString;
import co.com.ias.learning.students.application.domain.IdentificationNumber;
import co.com.ias.learning.students.application.domain.IdentificationType;
import co.com.ias.learning.students.application.domain.Student;
import co.com.ias.learning.students.application.ports.out.StudentsRepository;
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

@Repository("sql")
public class SqlStudentRepository implements StudentsRepository {

    private final JdbcTemplate jdbcTemplate;

    public SqlStudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Student> studentRowMapper = (rs, rowNum) -> fromResultSet(rs);  //rs y rowNum son variables que se crean ahi mismo, donde se llama al metodo Result.

    @Override
    public Optional<Student> getStudentById(IdentificationNumber identificationNumber) {
        final String sql = "SELECT * FROM Student WHERE Id_number = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, identificationNumber.getValue());
        };
        final ResultSetExtractor<Optional<Student>> resultSetExtractor = resultSet -> {
            if (resultSet.next()) {
                final Student student = fromResultSet(resultSet);
                return Optional.of(student);
            } else {
                return Optional.empty();
            }
        };
        return jdbcTemplate.query(sql, preparedStatementSetter, resultSetExtractor);
    }

    @Override
    public void storeStudent(Student student) {
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Student(Id_number, Id_type, Name, Last_name) VALUES (?,?,?,?)");
            preparedStatement.setString(1, student.getIdentificationNumber().getValue());
            preparedStatement.setString(2, student.getIdentificationType().name());
            preparedStatement.setString(3, student.getName().getValue());
            preparedStatement.setString(4, student.getLastName().getValue());

            return preparedStatement;
        });
    }

    @Override
    public Collection<Student> listStudents() {
        final String sql = "SELECT * FROM Student";
        return jdbcTemplate.query(sql, studentRowMapper);
    }

    private static Student fromResultSet(ResultSet rs) throws SQLException {
        return new Student(
                new NonEmptyString(rs.getString("Name")),
                new NonEmptyString(rs.getString("Last_name")),
                IdentificationType.valueOf(rs.getString("Id_type")),
                new IdentificationNumber(rs.getString("Id_number"))
        );
    }
}
