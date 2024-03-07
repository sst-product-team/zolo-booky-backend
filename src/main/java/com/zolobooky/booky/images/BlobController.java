package com.zolobooky.booky.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v0/images")
public class BlobController {

	@Autowired
	private BlobService blobService;

	@PostMapping("")
	public ResponseEntity<String> uploadImage(@RequestParam MultipartFile multipartFile) throws IOException {
		String fileName = blobService.uploadImage(multipartFile);

		return ResponseEntity.ok(fileName);
	}

	@DeleteMapping("/{image}")
	public ResponseEntity<Boolean> deleteImage(@PathVariable("image") String imageName) {

		Boolean delStat = blobService.deleteImage(imageName);
		return ResponseEntity.ok(delStat);
	}

	@GetMapping("/{image}")
	public ResponseEntity<Resource> getFile(@PathVariable("image") String imageName) {

		ByteArrayResource resource = new ByteArrayResource(blobService.downloadImage(imageName));

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageName + "\"");

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).headers(headers).body(resource);
	}

}
