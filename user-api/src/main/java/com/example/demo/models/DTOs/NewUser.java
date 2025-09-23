package com.example.demo.models.DTOs;

import java.util.List;

import com.example.demo.models.Profile;
import com.example.demo.models.Profile.AccountType;

public record NewUser(
        String name,
        String handle,
        String email,
        String password,
        String company,
        Profile.AccountType type,
        List<String> roles
)  {

}
