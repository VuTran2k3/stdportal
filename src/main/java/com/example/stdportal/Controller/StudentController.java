package com.example.stdportal.Controller;

import com.example.stdportal.DTO.StudentDTO;
import com.example.stdportal.Model.Classroom;
import com.example.stdportal.Model.Student;
import com.example.stdportal.Repository.ClassroomRepository;
import com.example.stdportal.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final ClassroomRepository classroomRepository;

    public StudentController(StudentService studentService, ClassroomRepository classroomRepository) {
        this.studentService = studentService;
        this.classroomRepository = classroomRepository;
    }

    @ModelAttribute("classrooms")
    public List<Classroom> classrooms() {
        return classroomRepository.findAll(Sort.by("name").ascending());
    }

    @GetMapping("")
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
        return "Student/student-list";
    }
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Student s = studentService.get(id);
        model.addAttribute("s", s);
        return "Student/student-view"; // templates/student-view.html
    }

    // CREATE - form
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("dto", new StudentDTO());
        model.addAttribute("mode", "create");
        return "Student/student-form"; // templates/student-form.html
    }

    // CREATE - submit
    @PostMapping
    public String create(@Valid @ModelAttribute("dto") StudentDTO dto,
                         BindingResult binding,
                         RedirectAttributes ra,
                         Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("mode", "create");
            return "Student/student-form";
        }
        Student created = studentService.create(dto);
        ra.addFlashAttribute("msg", "Tạo sinh viên thành công (ID: " + created.getId() + ")");
        return "redirect:/students";
    }

    // UPDATE - form
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Student s = studentService.get(id);
        StudentDTO dto = new StudentDTO(s.getName(),
                s.getClassroom() != null ? s.getClassroom() : null,
                s.getDob(), s.getGpa());
        model.addAttribute("dto", dto);
        model.addAttribute("id", id);
        model.addAttribute("mode", "edit");
        return "Student/student-form";
    }

    // UPDATE - submit (dùng POST cho đơn giản)
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("dto") StudentDTO dto,
                         BindingResult binding,
                         RedirectAttributes ra,
                         Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("id", id);
            return "Student/student-form";
        }
        studentService.update(id, dto);
        ra.addFlashAttribute("msg", "Cập nhật sinh viên #" + id + " thành công");
        return "redirect:/students";
    }

    // DELETE (dùng POST để submit từ form)
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        studentService.delete(id);
        ra.addFlashAttribute("msg", "Đã xoá sinh viên #" + id);
        return "redirect:/students";
    }
}
