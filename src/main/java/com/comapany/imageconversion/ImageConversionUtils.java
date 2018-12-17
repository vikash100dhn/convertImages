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
	public void base64ToThumbnail(String base64String)
	{
		BufferedImage bout= base64StringToImg(base64String);
		BufferedImage thumbnail = scale(bout, 0.4);
		try {
			ImageIO.write(thumbnail, "jpg", new File("C:\\Users\\Vikash\\Desktop\\pari.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private BufferedImage scale(BufferedImage source,double ratio) {
		  int w = (int) (source.getWidth() * ratio);
		  int h = (int) (source.getHeight() * ratio);
		  BufferedImage bi = getCompatibleImage(w, h);
		  Graphics2D g2d = bi.createGraphics();
		  double xScale = (double) w / source.getWidth();
		  double yScale = (double) h / source.getHeight();
		  AffineTransform at = AffineTransform.getScaleInstance(xScale,yScale);
		  g2d.drawRenderedImage(source, at);
		  g2d.dispose();
		  return bi;
		}

		private BufferedImage getCompatibleImage(int w, int h) {
		  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		  GraphicsDevice gd = ge.getDefaultScreenDevice();
		  GraphicsConfiguration gc = gd.getDefaultConfiguration();
		  BufferedImage image = gc.createCompatibleImage(w, h);
		  return image;
		}
	public static void getQRCode(String base64String)
	{
		
	}
	
	public static void main(String args[])
	{
		try {
			BufferedImage bif = ImageIO.read(new File("C:\\Users\\Vikash\\Desktop\\pari.jpg"));
			String base64String = imgToBase64String(bif, "png");
			System.out.println(imgToBase64String(bif, "png"));
			
			BufferedImage bout= base64StringToImg(base64String);
			
			ImageConversionUtils util = new ImageConversionUtils();
			BufferedImage thumbnail = util.scale(bout, 0.4);
			ImageIO.write(thumbnail, "jpg", new File("C:\\Users\\Vikash\\Desktop\\pari.jpg"));
			
			//ImageIO.write(bout, "jpg", new File("C:\\Users\\Vikash\\Desktop\\pariw.jpg"));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
