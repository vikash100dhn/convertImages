package com.comapany.imageconversion;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class WriteQRCode {
	public static void main(String[] args) throws WriterException, IOException {
		 
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(
				"JavaSampleApproach\nJava Technology, Spring Framework",
				BarcodeFormat.QR_CODE,
				350, 350); // width x height
 
		Path path = FileSystems.getDefault().getPath("C:\\Users\\Vikash\\Desktop\\JSA-QRCode.png");
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	}
}
