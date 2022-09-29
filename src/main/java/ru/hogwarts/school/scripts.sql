select name
from student;

select *
from student
where  age between 10 and 25;

select *
from student
where name like '%a%';

select *
from student
where age < student.id;

select * from student
order by age;