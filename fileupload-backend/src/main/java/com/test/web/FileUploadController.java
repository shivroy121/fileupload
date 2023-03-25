package com.test.web;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.dto.Response;
import com.test.service.CsvHelperService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@SuppressWarnings("unused")
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

	@Autowired
	private CsvHelperService csvHelperService;

	@PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Response>> handleFileUploadForm(@RequestPart("file") MultipartFile file) {

		log.info("handling request parts: {}", file);

		try {
			List<Response> responseData = csvHelperService.csvToTutorials(file.getInputStream());

			return ok().body(responseData);
		} catch (IOException e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
