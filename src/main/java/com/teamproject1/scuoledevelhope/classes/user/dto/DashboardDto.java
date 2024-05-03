package com.teamproject1.scuoledevelhope.classes.user.dto;

import com.teamproject1.scuoledevelhope.classes.role.dto.RoleDashboard;
import com.teamproject1.scuoledevelhope.classes.user.User;
import com.teamproject1.scuoledevelhope.classes.userRegistry.UserRegistry;

public class DashboardDto {

    private String username;
    private RoleDashboard role;
    private UserRegistry userRegistry;

    public String getUsername() {
        return username;
    }

    public RoleDashboard getRole() {
        return role;
    }

    public UserRegistry getUserRegistry() {
        return userRegistry;
    }

    public DashboardDto(String username, String password, RoleDashboard role, UserRegistry userRegistry) {
        this.username = username;
        this.role = role;
        this.userRegistry = userRegistry;
    }

    public DashboardDto() {
    }

    public static final class DashboardDtoBuilder {
        private String username;
        private RoleDashboard role;
        private UserRegistry userRegistry;

        private DashboardDtoBuilder() {
        }

        public static DashboardDtoBuilder map(User user) {
            return DashboardDtoBuilder.aDashboardDto()
                    .withUsername(user.getUsername())
                    .withUserRegistry(user.getUserRegistry());
        }

        public static DashboardDtoBuilder aDashboardDto() {
            return new DashboardDtoBuilder();
        }

        public DashboardDtoBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public DashboardDtoBuilder withRole(RoleDashboard role) {
            this.role = role;
            return this;
        }

        public DashboardDtoBuilder withUserRegistry(UserRegistry userRegistry) {
            this.userRegistry = userRegistry;
            return this;
        }

        public DashboardDto build() {
            DashboardDto dashboardDto = new DashboardDto();
            dashboardDto.userRegistry = this.userRegistry;
            dashboardDto.role = this.role;
            dashboardDto.username = this.username;
            return dashboardDto;
        }
    }
}
