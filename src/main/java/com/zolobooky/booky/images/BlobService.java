package com.zolobooky.booky.images;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class BlobService {

	@Autowired
	private BlobContainerClient blobContainerClient;

	public String uploadImage(MultipartFile multipartFile) throws IOException {

		BlobClient blob = blobContainerClient.getBlobClient(multipartFile.getOriginalFilename());

		blob.upload(multipartFile.getInputStream(), multipartFile.getSize(), true);

		final String provider = System.getenv("PROVIDER");
		final String version = System.getenv("VERSION");

		return String.format("%s/%s/images/%s", provider, version, blob.getBlobName());

	}

	public byte[] downloadImage(String fileName) {

		BlobClient blob = blobContainerClient.getBlobClient(fileName);

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		blob.downloadStream(outStream);

		return outStream.toByteArray();
	}

	public Boolean deleteImage(String fileName) {

		BlobClient blob = blobContainerClient.getBlobClient(fileName);

		return blob.deleteIfExists();
	}

}
