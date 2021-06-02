package com.course.mongo;

import com.course.mongo.entity.Address;
import com.course.mongo.entity.Gender;
import com.course.mongo.entity.Student;
import com.course.mongo.repository.StudentRepository;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class MongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }

    //@Bean
    CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate){
        return  args -> {
            Address address = new Address(
                    "England",
                    "London",
                    "NE9"
            );
            var email = "jahmed@gmail.com";
            Student student = new Student(
                    "Jamila",
                    "Ahmed",
                    email,
                    Gender.FEMALE,
                    address,
                    List.of("Computer science", "Maths"),
                    10.0,
                    LocalDateTime.now()

            );

            //usingMongoTemplateAndQuery(studentRepository, mongoTemplate, email, student);
            studentRepository.findStudentByEmail(email).ifPresentOrElse(s -> {
                System.out.println(s + " already exists");
            }, () -> {
                System.out.println("Inserting student: " + student);
                studentRepository.save(student);
            });
        };
    }

    private void usingMongoTemplateAndQuery(StudentRepository studentRepository, MongoTemplate mongoTemplate, String email, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        var students = mongoTemplate.find(query, Student.class);

        if (students.size() > 1) {
            throw new IllegalStateException("Found many students with email: " + email);
        }
        if (students.isEmpty()) {
            System.out.println("Inserting student: " + student);
            studentRepository.save(student);
        } else System.out.println(student + " already exists");
    }

}
