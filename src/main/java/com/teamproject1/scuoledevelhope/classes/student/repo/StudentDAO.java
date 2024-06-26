package com.teamproject1.scuoledevelhope.classes.student.repo;

import com.teamproject1.scuoledevelhope.classes.student.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentDAO extends JpaRepository<Student, Long> {
    Page<Student> findAllByRegisterId(Long id, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update student s set s.id_class = :idClass, s.id_register = :idClass where user_id = :idStudent", nativeQuery = true)
    void updateStudentClass(@Param("idStudent") Long idStudent, @Param("idClass") Long idClass);


}
