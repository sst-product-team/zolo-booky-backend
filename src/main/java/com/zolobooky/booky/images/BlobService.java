package com.zolobooky.booky.images;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;

import com.azure.storage.blob.models.BlobStorageException;
import com.zolobooky.booky.images.ImageExceptions.ImageNotFoundExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
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

		try {
			blob.downloadStream(outStream);

		}
		catch (BlobStorageException be) {
			log.info(String.format("Image with name: %s not found to download", fileName));
			throw new ImageNotFoundExceptions(String.format("Image with name: %s not found to download", fileName));
		}
		log.info(String.format("Image with name: %s downloaded", fileName));
		return outStream.toByteArray();
	}

	public Boolean deleteImage(String fileName) {

		BlobClient blob = blobContainerClient.getBlobClient(fileName);

		if (!blob.exists()) {
			log.info(String.format("Image with name: %s not found to delete", fileName));
			throw new ImageNotFoundExceptions(String.format("Image with name: %s not found to delete", fileName));
		}
		;
		log.info(String.format("Image with name: %s deleted", fileName));
		return blob.deleteIfExists();
	}

}
