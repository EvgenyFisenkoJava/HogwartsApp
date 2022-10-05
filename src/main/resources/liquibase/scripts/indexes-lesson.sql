-- liquibase formatted sql

-- changeset Evgeny:1
  CREATE INDEX student_name_index on student (name);


-- changeset Evgeny:2
  CREATE INDEX faculty_nc_index on faculty (name, color);