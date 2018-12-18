package com.comapany.imageconversion;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.constraints.Pattern.Flag;

public class ImageConversionService {

	public ImageConversionOutput convertImageToThumbnail(ImageConversionInput input) {
		
		ImageConversionUtils utils = new ImageConversionUtils();
		
		ImageConversionOutput output = new ImageConversionOutput();
		
		if(input.getBarCodeFlag() == 'Y')
		{
			
		}
		else {
			
			//converting image to thumbnail
			CreateThumbnail thumbnail = new CreateThumbnail();
			try {
				InputStream inputStream = thumbnail.createThumbnailandSave(input.getImageData());
				System.out.println("Thumbnail Created");
				UploadImageInS3.uploadStringInS3(input.getFileName()+"_thumbnail"+"."+input.getFileType(), inputStream);
				System.out.println("Upload successful in S3");
				InputStream originalImage =  ImageConversionUtils.bufferedImageToInputstream(ImageConversionUtils.base64StringToImg(input.getImageData()),input.getFileType());
				UploadImageInS3.uploadStringInS3(input.getFileName()+"."+input.getFileType(), inputStream);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//utils.base64ToThumbnail(input.getImageData());
			output.setBarCode("2124");
			
		}
		return output;
	}

}
