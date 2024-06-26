package com.teamproject1.scuoledevelhope.classes.role.dto;

import com.teamproject1.scuoledevelhope.classes.role.Role;

import java.util.List;

public class RoleDashboard {

    List<Role.RoleEnum> roleEnum;

    public List<Role.RoleEnum> getRoleEnum() {
        return roleEnum;
    }

    public RoleDashboard(List<Role.RoleEnum> roleEnum) {
        this.roleEnum = roleEnum;
    }

    public RoleDashboard() {
    }

    public static final class RoleDashboardBuilder {
        private List<Role.RoleEnum> roleEnum;

        private RoleDashboardBuilder() {
        }

        public static RoleDashboardBuilder aRoleDashboard() {
            return new RoleDashboardBuilder();
        }

        public RoleDashboardBuilder withRoleEnum(List<Role.RoleEnum> roleEnum) {
            this.roleEnum = roleEnum;
            return this;
        }

        public RoleDashboard build() {
            RoleDashboard roleDashboard = new RoleDashboard();
            roleDashboard.roleEnum = this.roleEnum;
            return roleDashboard;
        }
    }
}
