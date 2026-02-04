package com.learnhub.playgound.user.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateUserProfileRequest(
        @Size(max = 20, message = "Phone must not exceed 20 characters")
        String phone,

        String bio,

        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth,

        @Size(max = 100, message = "Country must not exceed 100 characters")
        String country,

        @Size(max = 100, message = "City must not exceed 100 characters")
        String city,

        String address,

        @Size(max = 255, message = "LinkedIn URL must not exceed 255 characters")
        String linkedinUrl,

        @Size(max = 255, message = "Website URL must not exceed 255 characters")
        String websiteUrl
) {
}
