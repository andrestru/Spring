CREATE TABLE Student(
    Id_number varchar primary key NOT NULL,
    Id_type varchar(2),
    Name varchar NOT NULL,
    Last_name varchar NOT NULL
);

CREATE TABLE Course(
    Id_Number varchar primary key NOT NULL,
    Name varchar NOT NULL,
    Location varchar NOT NULL
);
