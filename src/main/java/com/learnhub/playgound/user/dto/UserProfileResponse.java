package com.learnhub.playgound.user.dto;

import com.learnhub.playgound.user.domain.UserProfile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserProfileResponse(
        Long id,
        String phone,
        String bio,
        LocalDate dateOfBirth,
        String country,
        String city,
        String address,
        String linkedinUrl,
        String websiteUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static UserProfileResponse fromEntity(UserProfile profile) {
        return new UserProfileResponse(
                profile.getId(),
                profile.getPhone(),
                profile.getBio(),
                profile.getDateOfBirth(),
                profile.getCountry(),
                profile.getCity(),
                profile.getAddress(),
                profile.getLinkedinUrl(),
                profile.getWebsiteUrl(),
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }
}
