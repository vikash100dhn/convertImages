package com.comapany.imageconversion;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;

public class CreateThumbnail {
	
	 public InputStream createThumbnailandSave(String base64String) throws IOException {
	  
	  long startTime = System.currentTimeMillis();
	  BufferedImage img = base64StringToImg(base64String); // load image

	//fixed size like 50*50 then use FIT_EXACT
	//other modes like FIT_TO_WIDTH..etc also available.

	  BufferedImage thumbImg = Scalr.resize(img, Method.QUALITY,Mode.AUTOMATIC,50,50, Scalr.OP_ANTIALIAS);
	   //convert bufferedImage to outpurstream 
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  ImageIO.write(thumbImg,"jpg",os);
	  
	  InputStream is = new ByteArrayInputStream(os.toByteArray());
	  
	  //or wrtite to a file
	  
	 /* File f2 = new File("C:\\Users\\Vikash\\Desktop\\thumbnail.jpg");
	  
	  
	  ImageIO.write(thumbImg, "jpg", f2);
	  */
	  System.out.println("time is : " +(System.currentTimeMillis()-startTime));
	  return is;
	  
	 }
	 public static BufferedImage base64StringToImg(final String base64String) {
		    try { 
		        return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
		    } catch (final IOException ioe) {
		        throw new UncheckedIOException(ioe);
		    } 
		} 

}
