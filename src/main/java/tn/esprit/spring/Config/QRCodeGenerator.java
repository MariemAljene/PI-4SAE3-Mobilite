package tn.esprit.spring.Config;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import tn.esprit.spring.entities.Opportunity;

public class QRCodeGenerator {
    public static byte[] generateQRCodeImage(Opportunity opportunity) throws WriterException, IOException {
        // Configure the ObjectMapper to handle Java 8 date/time types
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Convert the Opportunity object to a JSON string
        String opportunityJson = objectMapper.writeValueAsString(opportunity);

        // Generate the QR code
        int width = 350;
        int height = 350;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(opportunityJson, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
        byte[] qrCodeBytes = byteArrayOutputStream.toByteArray();

        return qrCodeBytes;
    }

}
