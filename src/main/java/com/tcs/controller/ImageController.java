package com.tcs.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.service.ImageServiceImpl;


@RestController
public class ImageController {

	@Autowired
	private ImageServiceImpl s3Service;

	@PostMapping("upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		return s3Service.saveFile(file);
	}
/*
	@GetMapping("download/{filename}")
	public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", MediaType.ALL_VALUE);
		headers.add("Content-Disposition", "attachment; filename=" + filename);
		byte[] bytes = s3Service.downloadFile(filename);
		return ResponseEntity.status(HTTP_OK).headers(headers).body(bytes);
	}
*/
	@GetMapping("download/{filename}")
	public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-type", "image/jpeg"); // Set the appropriate content type for your image type
	    byte[] bytes = s3Service.downloadFile(filename);
	    return ResponseEntity.ok().headers(headers).body(bytes);
	}


	@DeleteMapping("{filename}")
	public String deleteFile(@PathVariable("filename") String filename) {
		return s3Service.deleteFile(filename);
	}
	  
	
	@GetMapping("list")
	public List<String> getAllFiles() {

		return s3Service.listAllFiles();
	}
	
	
}