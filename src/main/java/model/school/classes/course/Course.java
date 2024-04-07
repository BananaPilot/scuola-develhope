package model.school.classes.course;

import jakarta.persistence.*;
import model.school.School;
import model.school.classes.Classes;

import java.util.Set;

@Entity
@Table(name = "course")
public enum Course {
    BACKEND,
    FULLSTACK,
    FRONTEND;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Integer id;
    @Column(
            name = "course_name",
            nullable = false
    )
    private String name;
    @Column(name = "course_description")
    private String description;
    @OneToMany(
            mappedBy = "course",
            fetch = FetchType.LAZY)
    private Set<School> schools;
    @OneToMany(
            mappedBy = "course",
            fetch = FetchType.LAZY)
    private Set<Classes> classes;

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Set<School> getSchools() {
        return schools;
    }
    public Set<Classes> getClasses() {
        return classes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id: " + id +
                ", name: '" + name + '\'' +
                ", description: '" + description + '\'' +
                '}';
    }
}
