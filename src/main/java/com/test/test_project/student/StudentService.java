package com.test.test_project.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class StudentService {
	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
    public List<Student> getStudents(){
		
		return studentRepository.findAll();
	}
	public Student getStudent(Long studentId){
		boolean exists = studentRepository.existsById(studentId);
		if(!exists){
			throw new IllegalStateException("student with id "+studentId+" does not exist");
		}
		return studentRepository.findById(studentId).get();
	}
	
	public void addStudent(Student student){ // mapping the request body into a student object
		System.out.println(student);

		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent()){
			throw new IllegalStateException("email taken");
		}
       studentRepository.save(student);
    }
	public void deleteStudent(Long studentId){
		boolean exists = studentRepository.existsById(studentId);
		if(!exists){
			throw new IllegalStateException("student with id "+studentId+" does not exist");
		}
		studentRepository.deleteById(studentId);
	}
	@Transactional  //managed state :updating  the associated table using class setters  
	public void updateStudent(Long studentId,String name,String email){
		boolean exists = studentRepository.existsById(studentId);
		if(!exists){
			throw new IllegalStateException("student with id "+studentId+" does not exist");
		}
		Optional<Student> s = studentRepository.findById(studentId);

		if(name!=null && name.length()>0 && !Objects.equals(s.get().getName(), name)){
			s.get().setName(name);
		}
		
		if(email!=null && email.length()>0 && !Objects.equals(s.get().getEmail(), email)){
			s.get().setEmail(email);
		}

	}

    
}
