package com.example.vitclubactivityhub.service

import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import org.springframework.stereotype.Service
import java.awt.Color
import java.awt.image.BufferedImage

@Service
class QRCodeService {

    fun generateQRCode(userId: Long, eventId: Long): BufferedImage {
        val qrCodeContent = "User: $userId, Event: $eventId"
        val qrCodeWriter = QRCodeWriter()
        val bitMatrix = qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, 250, 250)

        val width = bitMatrix.width
        val height = bitMatrix.height
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

        for (x in 0 until width) {
            for (y in 0 until height) {
                image.setRGB(x, y, if (bitMatrix.get(x, y)) Color.BLACK.rgb else Color.WHITE.rgb)
            }
        }
        return image
    }

    fun validateQRCode(userId: Long, eventId: Long): Boolean {
        // Add actual validation logic here. For now, return true for simplicity.
        return true
    }
}
