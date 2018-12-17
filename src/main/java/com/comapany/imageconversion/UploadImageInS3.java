package com.comapany.imageconversion;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class UploadImageInS3 {

	public static void uploadStringInS3(String token) throws IOException {

		String bucketName = "";
		String stringObjKeyName = "abc.json";
		boolean objectPresent = false;

		try {

			AmazonS3 s3Client = new AmazonS3Client();
			String  refreshToken =token;//this refresh token will be fetch from API query
			ObjectListing objectListing = s3Client.listObjects(new ListObjectsRequest().withBucketName(bucketName));
			for(S3ObjectSummary s3ObjectSummary: objectListing.getObjectSummaries())
			{
				if(s3ObjectSummary.getKey().equals("abc.json"))
				{
					objectPresent=true;
				}
			}
			System.out.println("Object Present::"+objectPresent);
			if(objectPresent)
			{
				s3Client.putObject(bucketName, stringObjKeyName, refreshToken);
			}
			else {
				
				InputStream is = new InputStream() {
					
					@Override
					public int read() throws IOException {
						return -1;
					}
				};
				
				 PutObjectRequest request = new PutObjectRequest(bucketName, "/", new File("abc.json"));
		         ObjectMetadata metadata = new ObjectMetadata();
		         metadata.setContentType("plain/text");
		         metadata.addUserMetadata("x-amz-meta-title", "someTitle");
		         request.setMetadata(metadata);
		         s3Client.putObject(bucketName, stringObjKeyName, is, metadata);
		         s3Client.putObject(bucketName, stringObjKeyName, refreshToken);
			}
		}
		catch(AmazonServiceException e) {
			// The call w as transmitted successfully, but Amazon S3 couldn't process 
			// it, so it returned an error response.
			e.printStackTrace();
		}
		catch(SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}
	}
	
	

}
