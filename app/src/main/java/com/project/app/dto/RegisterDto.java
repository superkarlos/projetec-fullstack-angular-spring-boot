package com.project.app.dto;

import com.project.app.enumerdor.UserRoles;

public record RegisterDto(String login, String password, UserRoles role) {
    
}
