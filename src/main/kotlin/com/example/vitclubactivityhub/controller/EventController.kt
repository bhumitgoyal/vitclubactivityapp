package com.example.vitclubactivityhub.controller

import com.example.vitclubactivityhub.model.Event
import com.example.vitclubactivityhub.repository.EventRepository
import com.example.vitclubactivityhub.repository.UserRepository
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.client.j2se.MatrixToImageWriter
import org.springframework.web.bind.annotation.*
import java.nio.file.FileSystems
import java.nio.file.Path
import java.time.LocalDateTime

@RestController
@RequestMapping("/events")
class EventController(
    private val eventRepository: EventRepository,
    private val userRepository: UserRepository
) {

    @PostMapping("/register/{eventId}")
    fun registerForEvent(
        @PathVariable eventId: Long,
        @RequestParam studentId: Long
    ): String {
        val student = userRepository.findById(studentId).orElseThrow()
        val event = eventRepository.findById(eventId).orElseThrow()

        // Generate QR Code
        val qrCodeData = "${student.id}-${event.id}"
        val qrPath = generateQRCode(qrCodeData)

        return "Registered! QR Code saved at: $qrPath"
    }

    private fun generateQRCode(data: String): String {
        val width = 300
        val height = 300
        val qrCodePath: Path = FileSystems.getDefault().getPath("qrcodes/$data.png")
        val bitMatrix = MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height)
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrCodePath)
        return qrCodePath.toString()
    }

    @GetMapping
    fun getAllEvents(): List<Event> {
        return eventRepository.findAll()
    }

    @PostMapping("/create")
    fun createEvent(
        @RequestParam clubId: Long,
        @RequestBody event: Event
    ): Event {
        val club = userRepository.findById(clubId).orElseThrow()
        val newEvent = event.copy(createdBy = club)
        return eventRepository.save(newEvent)
    }
}
