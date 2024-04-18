package com.teamproject1.scuoledevelhope.classes.student;

import com.teamproject1.scuoledevelhope.classes.calendar.Meeting;
import com.teamproject1.scuoledevelhope.classes.classP.Classes;
import com.teamproject1.scuoledevelhope.classes.register.Register;
import com.teamproject1.scuoledevelhope.classes.user.User;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_student")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_class")
    private Classes schoolClass;
    @ManyToOne
    @JoinColumn(name = "id_register")
    private Register register;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "meeting_student",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_meeting")
    )
    private List<Meeting> meetings;


    public Student() {
    }

    public Student(User user, Classes schoolClass, Register register) {
        this.user = user;
        this.schoolClass = schoolClass;
        this.register = register;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Classes getSchoolClass() {
        return schoolClass;
    }

    public Register getRegister() {
        return register;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSchoolClass(Classes schoolClass) {
        this.schoolClass = schoolClass;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id: " + id +
                ", id_user: " + user +
                ", id_class: " + schoolClass +
                ", id_register: " + register +
                '}';
    }
}
