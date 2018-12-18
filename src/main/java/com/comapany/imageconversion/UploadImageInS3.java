package com.comapany.imageconversion;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class UploadImageInS3 {

	public static void uploadStringInS3(String fileName, InputStream inputStream) throws IOException {

		String bucketName = "cl-img-dev-store";
		String stringObjKeyName = fileName;

		try {

			AWSCredentials credentials = new BasicAWSCredentials(
					  "", 
					  ""
					);
			AmazonS3 s3Client = AmazonS3ClientBuilder
					  .standard()
					  .withCredentials(new AWSStaticCredentialsProvider(credentials))
					  .withRegion(Regions.US_EAST_1)
					  .build();
				
			if(s3Client.doesBucketExist(bucketName)) {
			    System.out.println("Bucket name is not available."
			      + " Try again with a different Bucket name.");
			    return;
			}
			else
			{
				PutObjectRequest request = null;
				if(fileName.contains("thumbnail"))
				{
					 request = new PutObjectRequest(bucketName, "/", new File(stringObjKeyName));
				}
				else
				{
					request = new PutObjectRequest(bucketName, "/", new File(stringObjKeyName));
				}
		         ObjectMetadata metadata = new ObjectMetadata();
		         metadata.setContentType("image/png");
		         metadata.addUserMetadata("x-amz-meta-title", "someTitle");
		         request.setMetadata(metadata);
		         s3Client.putObject(bucketName, stringObjKeyName, inputStream, metadata);
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
