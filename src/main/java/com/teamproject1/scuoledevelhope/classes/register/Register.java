package com.teamproject1.scuoledevelhope.classes.register;

import com.teamproject1.scuoledevelhope.classes.clazz.Clazz;
import com.teamproject1.scuoledevelhope.classes.student.Student;
import com.teamproject1.scuoledevelhope.classes.tutor.Tutor;
import com.teamproject1.scuoledevelhope.classes.vote.Vote;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "register")
public class Register {

    @Id
    private Long id;

    @NotBlank(message = "school year can't be blank")
    @Column(
            name = "register_school_year",
            nullable = false
    )
    private String schoolYear;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_class")
    private Clazz schoolClass;

    @OnDelete(action = OnDeleteAction.SET_NULL)
    @ManyToOne
    @JoinColumn(name = "id_tutor")
    private Tutor tutor;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(
            mappedBy = "register",
            fetch = FetchType.LAZY
    )
    private List<Vote> votes;

    @OnDelete(action = OnDeleteAction.SET_NULL)
    @OneToMany(
            mappedBy = "register",
            fetch = FetchType.LAZY
    )
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolClass(Clazz schoolClass) {
        this.schoolClass = schoolClass;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Clazz getSchoolClass() {
        return schoolClass;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public static final class RegisterBuilder {
        private Long id;
        private @NotBlank(message = "school year can't be blank") String schoolYear;
        private Clazz schoolClass;
        private Tutor tutor;
        private List<Vote> votes;
        private List<Student> students;

        private RegisterBuilder() {
        }

        public static RegisterBuilder aRegister() {
            return new RegisterBuilder();
        }

        public RegisterBuilder withSchoolYear(String schoolYear) {
            this.schoolYear = schoolYear;
            return this;
        }

        public RegisterBuilder withSchoolClass(Clazz schoolClass) {
            this.schoolClass = schoolClass;
            return this;
        }

        public RegisterBuilder withTutor(Tutor tutor) {
            this.tutor = tutor;
            return this;
        }

        public RegisterBuilder withVotes(List<Vote> votes) {
            this.votes = votes;
            return this;
        }

        public RegisterBuilder withStudents(List<Student> students) {
            this.students = students;
            return this;
        }

        public Register build() {
            Register register = new Register();
            register.setSchoolYear(schoolYear);
            register.setSchoolClass(schoolClass);
            register.setTutor(tutor);
            register.setVotes(votes);
            register.setStudents(students);
            register.id = this.id;
            return register;
        }
    }
}
