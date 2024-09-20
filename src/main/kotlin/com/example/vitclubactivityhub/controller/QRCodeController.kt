package com.example.vitclubactivityhub.controller

import com.example.vitclubactivityhub.service.QRCodeService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

@RestController
@RequestMapping("/qrcode")
class QRCodeController(private val qrCodeService: QRCodeService) {

    @GetMapping("/{userId}/{eventId}")
    fun generateQRCode(@PathVariable userId: Long, @PathVariable eventId: Long): ResponseEntity<ByteArray> {
        val qrImage: BufferedImage = qrCodeService.generateQRCode(userId, eventId)
        val outputStream = ByteArrayOutputStream()
        ImageIO.write(qrImage, "png", outputStream)
        val qrBytes = outputStream.toByteArray()

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"qrcode.png\"")
            .contentType(MediaType.IMAGE_PNG)
            .body(qrBytes)
    }
}
