package com.learnhub.playgound.user.dto;

import com.learnhub.playgound.user.domain.User;

import java.time.LocalDateTime;

public record UserWithProfileResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String avatarUrl,
        Boolean isVerified,
        Boolean isActive,
        LocalDateTime lastLogin,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UserProfileResponse profile
) {
    public static UserWithProfileResponse fromEntity(User user) {
        UserProfileResponse profileResponse = null;
        if (user.getProfile() != null) {
            profileResponse = UserProfileResponse.fromEntity(user.getProfile());
        }

        return new UserWithProfileResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getAvatarUrl(),
                user.getVerified(),
                user.getActive(),
                user.getLastLogin(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                profileResponse
        );
    }
}
