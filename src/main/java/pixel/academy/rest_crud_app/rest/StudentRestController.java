package pixel.academy.rest_crud_app.rest;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        return theStudents.get(studentId);
    }

}
