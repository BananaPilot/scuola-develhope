package com.teamproject1.scuoledevelhope.classes.user;

import com.teamproject1.scuoledevelhope.classes.coordinator.Coordinator;
import com.teamproject1.scuoledevelhope.classes.role.Role;
import com.teamproject1.scuoledevelhope.classes.school.School;
import com.teamproject1.scuoledevelhope.classes.student.Student;
import com.teamproject1.scuoledevelhope.classes.tutor.Tutor;
import com.teamproject1.scuoledevelhope.classes.userRegistry.UserRegistry;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(unique = true)
    private String username;

    private String password;
    @OneToOne()
    @JoinColumn(
            name = "user_registry_id",
            referencedColumnName = "id"
    )
    private UserRegistry userRegistry;

    @ManyToOne
    @JoinColumn(name = "id_school")
    private School school;

    @ManyToMany
    private Set<Role> roles;
    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<Student> students;
    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<Tutor> tutors;
    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<Coordinator> coordinators;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistry getUserRegistry() {
        return userRegistry;
    }

    public School getSchool() {
        return school;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRegistry(UserRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public static final class UserBuilder {

        private UUID id;
        private String username;
        private String password;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public UserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            User user = new User(username, password);
            user.id = this.id;
            return user;
        }
    }
}
