package com.diptrish.qr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diptrish.qr.dto.QRCodeDto;
import com.diptrish.qr.service.QrCodeGenerationService;
import com.diptrish.qr.util.AscUtil;

@RestController
@RequestMapping("/qr")
public class QrGenerationController {

	@Autowired
	private QrCodeGenerationService qrService;
	
	@Autowired
	private AscUtil AscUtil;

	@PostMapping("/generate-qr")
	public ResponseEntity<String> check(@RequestBody QRCodeDto inputData) {

		String qrText = qrService.createQr(inputData);
		String encText = AscUtil.getEncryptValue(qrText);
		return new ResponseEntity<>("https://www.google.com?key=yurgfyu?name=djksfhvbdfsvjkhg", HttpStatus.OK);
	}
}
