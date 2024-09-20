package com.example.vitclubactivityhub.controller

import com.example.vitclubactivityhub.model.User
import com.example.vitclubactivityhub.model.Role
import com.example.vitclubactivityhub.repository.UserRepository
import org.springframework.web.bind.annotation.*
import java.util.Optional

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userRepository: UserRepository
) {

    @PostMapping("/register")
    fun register(@RequestBody user: User): User {
        return userRepository.save(user)
    }

    @PostMapping("/login")
    fun login(@RequestParam email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }
}
