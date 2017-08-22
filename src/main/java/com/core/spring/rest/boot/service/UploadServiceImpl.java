package com.core.spring.rest.boot.service;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.core.spring.rest.boot.configuration.AppProperties;
import com.core.spring.rest.boot.exception.ApplicationException;

/**
 * @author AnilKumar
 *
 */
@Service
public class UploadServiceImpl implements UploadService {

	public static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

	private AppProperties app;

	
	@Autowired
	public void setApp(AppProperties app) {
		this.app = app;
	}

	/* (non-Javadoc)
	 * @see com.core.spring.rest.boot.service.UploadService#getFiles()
	 */
	@Override
	public List<Path> getFiles() {
		logger.info("Configured upload folder :" + app.getUpload().getLocation());
		List<Path> fileNames = new ArrayList<>();
		try {
			Files.list(Paths.get(app.getUpload().getLocation())).filter(Files::isRegularFile)
					.forEach(a -> fileNames.add(a));
		} catch (IOException e) {

		}
		return fileNames;
	}

	/* (non-Javadoc)
	 * @see com.core.spring.rest.boot.service.UploadService#uploadFile(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public void uploadFile(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			Files.createFile(Paths.get(app.getUpload().getDataFile()));
		} catch (FileAlreadyExistsException ignored) {
			logger.info("Meta data file exists");
		} catch (IOException e) {
			logger.error("IO Exception in create meta data file", e);
			throw new ApplicationException("IO Exception in create meta data file", e);
		}
		List<String> lines = new ArrayList<>();
		lines.add("\n------------------------------");
		lines.add("File Name  : " + file.getOriginalFilename());
		lines.add("Size : " + file.getSize());
		lines.add("\n------------------------------\n");
		try {
			Files.write(Paths.get(app.getUpload().getDataFile()), lines, UTF_8, APPEND, CREATE);
		} catch (IOException e) {
			logger.error("IO Exception in Writing meta data", e);
			throw new ApplicationException("IO Exception in Writing meta data", e);
		}

		try {

			Files.copy(file.getInputStream(), Paths.get(app.getUpload().getLocation()).resolve(filename));
		} catch (IOException e) {
			logger.error("IO Exception in uploading file", e);
			throw new ApplicationException("IO Exception in uploading file", e);

		}

	}

}
