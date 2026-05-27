package com.campus.event.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRGenerator {

    public static String generateQR(String text) throws Exception {

        BitMatrix matrix = new MultiFormatWriter()
                .encode(text, BarcodeFormat.QR_CODE, 200, 200);

        String fileName = text + ".png";

        Path path = FileSystems.getDefault()
                .getPath("src/main/resources/static/qr/" + fileName);

        MatrixToImageWriter.writeToPath(matrix, "PNG", path);

        return "/qr/" + fileName;
    }
}