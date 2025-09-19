package com.example.stdportal.Controller;

import com.example.stdportal.DTO.StudentDTO;
import com.example.stdportal.Model.Student;
import com.example.stdportal.Repository.StudentRepository;
import com.example.stdportal.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    StudentRepository studentRepository;
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
//    public Page<Student> studentList(
//            @RequestParam(required = false) String q,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "id,desc") String sort) {
//
//        String[] s = sort.split(",");
//        Sort.Direction dir = (s.length > 1 && s[1].equalsIgnoreCase("asc")) ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, s[0]));
//        return studentService.list(q, pageable);
//    }
    public String list(@RequestParam(required = false) String q,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(defaultValue = "id,desc") String sort,
                       Model model) {
        String[] s = sort.split(",");
        Sort.Direction dir = (s.length > 1 && s[1].equalsIgnoreCase("asc")) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, s[0]));
        var pageData = studentService.list(q, pageable);

        model.addAttribute("students", pageData.getContent());
        model.addAttribute("page", pageData);
        model.addAttribute("q", q);
        model.addAttribute("sort", sort);
        return "student";               // -> templates/student.html
    }
    @GetMapping("/{id}")
    public Student get(@PathVariable Long id) { return studentService.get(id); }

    @PostMapping
    public Student create(@Valid @RequestBody StudentDTO dto) { return studentService.create(dto); }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody StudentDTO dto) {
        return studentService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { studentService.delete(id); }
}
