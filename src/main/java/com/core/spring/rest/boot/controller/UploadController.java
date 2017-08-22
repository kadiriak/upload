package com.core.spring.rest.boot.controller;

import java.nio.file.Path;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.core.spring.rest.boot.service.UploadService;

/**
 * @author AnilKumar
 * Desc :  Handles creating meta data file, moving uploaded file to configured 
 * location and read available files from uploaded folder.
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

	public static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	
	UploadService uploadService;
	
	@Autowired
	public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

	/**
	 * @return
	 */
	@RequestMapping(value = "/files", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Path> getEmployeeNames() {
		return uploadService.getFiles();
	}

	/**
	 * @param uploadFile
	 * @return
	 */
	@PostMapping("/uploadfile")
	public String handleFileUpload(@RequestParam("file") MultipartFile uploadFile) {
		logger.info("Inside handleUpload......");
		uploadService.uploadFile(uploadFile);
		return "SUCCESS";
	}

}
