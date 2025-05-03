package pixel.academy.rest_crud_app.rest;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pixel.academy.rest_crud_app.entity.Student;

import java.util.ArrayList;
import java.util.List;

@RestController  // marcheaza clasa ca fiind un controller RESTful
@RequestMapping("/api")   // pentru a defini un prefix de URL pentru toate metodele

public class StudentRestController {

    private List<Student> theStudents;
    // definim @PostConstructor ca sa incarcam studentii nostri o singura data
    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Munteanu", "Eugen"));
        theStudents.add(new Student("Ojog", "Maria"));
        theStudents.add(new Student("Gonzales", "Pedro"));
    }

    // definim endpoint-ul pentru /students
    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }

    // definim un endpoint ca sa afisam toti studentiinostri dupa index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // verificam din nou studentId si dimensiunea listei
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudents.get(studentId);
    }

    // exception handler functioneaza doar cu int
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex) {
//
//        // crearea StudentErrorResponse
//        StudentErrorResponse error = new StudentErrorResponse();
//
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(ex.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        // returnarea ResponseEntity
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex) {

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Your request is not accepted!");
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



}
