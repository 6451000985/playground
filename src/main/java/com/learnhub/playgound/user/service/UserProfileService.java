package com.learnhub.playgound.user.service;

import com.learnhub.playgound.user.domain.User;
import com.learnhub.playgound.user.domain.UserProfile;
import com.learnhub.playgound.user.dto.CreateUserProfileRequest;
import com.learnhub.playgound.user.dto.UpdateUserProfileRequest;
import com.learnhub.playgound.user.dto.UserProfileResponse;
import com.learnhub.playgound.user.repository.UserProfileRepository;
import com.learnhub.playgound.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public UserProfileService(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserProfileResponse createProfile(Long userId, CreateUserProfileRequest request) {
        if (userProfileRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("Profile already exists for user id: " + userId);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        UserProfile profile = new UserProfile();
        profile.setUser(user);
        profile.setPhone(request.phone());
        profile.setBio(request.bio());
        profile.setDateOfBirth(request.dateOfBirth());
        profile.setCountry(request.country());
        profile.setCity(request.city());
        profile.setAddress(request.address());
        profile.setLinkedinUrl(request.linkedinUrl());
        profile.setWebsiteUrl(request.websiteUrl());

        UserProfile savedProfile = userProfileRepository.save(profile);
        return UserProfileResponse.fromEntity(savedProfile);
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getProfileByUserId(Long userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found for user id: " + userId));
        return UserProfileResponse.fromEntity(profile);
    }

    @Transactional
    public UserProfileResponse updateProfile(Long userId, UpdateUserProfileRequest request) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found for user id: " + userId));

        if (request.phone() != null) {
            profile.setPhone(request.phone());
        }
        if (request.bio() != null) {
            profile.setBio(request.bio());
        }
        if (request.dateOfBirth() != null) {
            profile.setDateOfBirth(request.dateOfBirth());
        }
        if (request.country() != null) {
            profile.setCountry(request.country());
        }
        if (request.city() != null) {
            profile.setCity(request.city());
        }
        if (request.address() != null) {
            profile.setAddress(request.address());
        }
        if (request.linkedinUrl() != null) {
            profile.setLinkedinUrl(request.linkedinUrl());
        }
        if (request.websiteUrl() != null) {
            profile.setWebsiteUrl(request.websiteUrl());
        }

        UserProfile savedProfile = userProfileRepository.save(profile);
        return UserProfileResponse.fromEntity(savedProfile);
    }

    @Transactional
    public void deleteProfile(Long userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found for user id: " + userId));
        userProfileRepository.delete(profile);
    }
}
