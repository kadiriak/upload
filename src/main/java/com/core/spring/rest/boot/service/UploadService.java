package com.core.spring.rest.boot.service;

import java.nio.file.Path;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Anil Kumar Kadiri
 * Desc :  Handles creating meta data file, moving uploaded file to configured 
 * location and read available files from uploaded folder.
 *
 */
public interface UploadService {
	
	/**
	 * Reads uploaded file paths from uploaded directory
	 * @return List<Path>
	 */
	List<Path> getFiles();

	/**
	 * Create meta data file if not exists and copy uploaded file to configured folder.
	 * @param file
	 */
	void uploadFile(MultipartFile file);
}
