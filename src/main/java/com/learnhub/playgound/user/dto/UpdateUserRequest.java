package com.learnhub.playgound.user.dto;

import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @Size(max = 100, message = "First name must not exceed 100 characters")
        String firstName,

        @Size(max = 100, message = "Last name must not exceed 100 characters")
        String lastName,

        @Size(max = 500, message = "Avatar URL must not exceed 500 characters")
        String avatarUrl
) {
}
