package com.example.stdportal.Service;

import com.example.stdportal.DTO.StudentDTO;
import com.example.stdportal.Model.Student;
import com.example.stdportal.Repository.ClassroomRepository;
import com.example.stdportal.Repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class StudentService{
//    @Autowired
    private StudentRepository studentRepository;
    private ClassroomRepository classroomRepository;


    public Page<Student> list(String keyword, Pageable pageable){
        if (keyword == null || keyword.isBlank()) return studentRepository.findAll(pageable);
        return studentRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }
    public Student get(Long id){
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }
    public Student create(StudentDTO dto){
//        if (studentRepository.existsByEmail(dto.email()))
//            throw new DataIntegrityViolationException("Email already exists");
        var student = dto.toStudent();
        var classroom = classroomRepository.findById(dto.getClassroom().getName())
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        student.setClassroom(classroom);
        return studentRepository.save(student);
    }
    public Student update(Long id, StudentDTO dto){
        var student = get(id);
        dto.updateToStudent(student);
        var classroom = classroomRepository.findById(dto.getClassroom().getName())
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        student.setClassroom(classroom);
        return studentRepository.save(student);
    }
    public void delete(Long id){
        var student = get(id);
        studentRepository.delete(student);
    }
}
