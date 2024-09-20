package com.example.vitclubactivityhub.model

import jakarta.persistence.*

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    @Column(unique = true)
    val registrationNumber: String,

    @Column(unique = true)
    val email: String,

    val phoneNumber: String,

    val profilePicture: String, // URL or base64 for profile image

    @Enumerated(EnumType.STRING)
    val role: Role // STUDENT or CLUB
)

enum class Role {
    STUDENT, CLUB
}
