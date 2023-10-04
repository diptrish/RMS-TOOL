package com.diptrish.qr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diptrish.qr.dto.AscDto;
import com.diptrish.qr.service.QrCodeGenerationService;
import com.diptrish.qr.util.AscUtil;

@RestController
@RequestMapping("/qr")
public class AscController {

	@Autowired
	private QrCodeGenerationService qrService;

	@Autowired
	private AscUtil AscUtil;

	@GetMapping("/asc/generate-key")
	public ResponseEntity<String> generateKey() {

		int keySizeInBytes = 16; // 128 bits
		String generatedKey = AscUtil.generateHexKey(keySizeInBytes);
		System.out.println("Generated Hex Key: " + generatedKey);

		return new ResponseEntity<>(generatedKey, HttpStatus.OK);
	}

	@PostMapping("/asc/encrypt")
	public ResponseEntity<String> encryptText(@RequestBody AscDto inputData) throws Exception {

		return new ResponseEntity<>(AscUtil.getEncryptValue(inputData.getText().trim()), HttpStatus.OK);
	}

	@PostMapping("/asc/decrypt")
	public ResponseEntity<String> decryptText(@RequestBody AscDto inputData) throws Exception {

		return new ResponseEntity<>(AscUtil.getDecryptValue(inputData.getText()), HttpStatus.OK);
	}

}
