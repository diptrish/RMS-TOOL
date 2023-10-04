package com.diptrish.qr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diptrish.qr.dto.ApiResponse;
@CrossOrigin
@RestController
@RequestMapping("/qr")
public class HealthCheckController {

	@GetMapping("/health-check")
	public ResponseEntity<ApiResponse> check() {
		
		ApiResponse resp = ApiResponse.builder()
				.message("QR Generation Service is Up & Running......")
				.status(1)
				.build();
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
