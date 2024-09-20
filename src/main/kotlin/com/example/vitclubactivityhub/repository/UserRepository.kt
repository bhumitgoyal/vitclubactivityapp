package com.example.vitclubactivityhub.repository

import com.example.vitclubactivityhub.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
    fun findByRegistrationNumber(registrationNumber: String): Optional<User>
}
