
package com.zolobooky.booky.images;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlobConfig {

	@Value("${CONN_STR}")
	private String connectionStr;

	@Value("${IMG_CONT}")
	private String imgContainer;

	@Bean
	public BlobServiceClient blobServiceClient() {
		BlobServiceClient newBlobServiceClient;
		newBlobServiceClient = new BlobServiceClientBuilder().connectionString(connectionStr).buildClient();

		return newBlobServiceClient;
	}

	@Bean
	public BlobContainerClient blobContainerClient() {
		BlobContainerClient newBlobContainerClient;
		newBlobContainerClient = blobServiceClient().getBlobContainerClient(imgContainer);

		return newBlobContainerClient;

	}

}
