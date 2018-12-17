package com.comapany.imageconversion;

import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") 
public class ImageController {



	@RequestMapping("/welcome") 
	public String welcome()
	{ 
		return "Welcome to boot!"; 
	} 
	
	@RequestMapping("/saveThumbnail")
	public ImageConversionOutput convertImageIntoThumbnail(@RequestBody ImageConversionInput input)
	{
		ImageConversionService service = new ImageConversionService();
		ImageConversionOutput  output = null;
		try {
			 output = service.convertImageToThumbnail(input);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return output;
		
	}
}