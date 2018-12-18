package com.comapany.imageconversion;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.imageio.ImageIO;


import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

public class ImageConversionUtils {
	
	public static String imgToBase64String(final BufferedImage img, final String formatName) {
	    final ByteArrayOutputStream os = new ByteArrayOutputStream();
	    try { 
	        ImageIO.write(img, formatName, Base64.getEncoder().wrap(os));
	        return os.toString(StandardCharsets.ISO_8859_1.name());
	    } catch (final IOException ioe) {
	        throw new UncheckedIOException(ioe);
	    } 
	} 
	 
	public static BufferedImage base64StringToImg(final String base64String) {
	    try { 
	        return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
	    } catch (final IOException ioe) {
	        throw new UncheckedIOException(ioe);
	    } 
	} 
	public static InputStream bufferedImageToInputstream(BufferedImage bufferedImage, String fileType)
	{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		  try {
			ImageIO.write(bufferedImage,fileType,os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  InputStream is = new ByteArrayInputStream(os.toByteArray());
		  return is;
	}
		
	
	public static void main(String args[])
	{
		try {
			BufferedImage bif = ImageIO.read(new File("C:\\Users\\Vikash\\Desktop\\testpng.png"));
			String base64String = imgToBase64String(bif, "png");
			System.out.println(base64String);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
