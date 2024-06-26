package com.teamproject1.scuoledevelhope.classes.role;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.teamproject1.scuoledevelhope.classes.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long id;

    @Column(name = "role_name", unique = true)
    @Enumerated(EnumType.STRING)
    RoleEnum roleEnum;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id")
    )
    private List<User> users;

    public enum RoleEnum {
        SUPER_ADMIN("SUPER_ADMIN"),
        ADMIN("ADMIN"),
        MODERATOR("MODERATOR"),
        USER("USER"),
        COORDINATOR("COORDINATOR"),
        TUTOR("TUTOR"),
        STUDENT("STUDENT");

        private final String roleString;

        RoleEnum(String roleString) {
            this.roleString = roleString;
        }

        public String getRoleString() {
            return roleString;
        }
    }

    public Long getId() {
        return id;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    @JsonBackReference
    public List<User> getUsers() {
        return users;
    }
}
